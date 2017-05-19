package com.source.it.jdbc.utils;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.BaseEntityInterface;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlGeneratorUtils {
    public static String generateSelectSql(BaseEntityInterface entity) {
        Pair<String, String> pair = getTableNameFromClassName(entity.getClass().getSimpleName());

        String sql = "SELECT " + pair.getValue() + ".ID";
        Map<String, List<String>> namesMap = new LinkedHashMap<>();
        getDeclaredFields(entity, namesMap);
        for (String name : namesMap.get(entity.getClass().getSimpleName())) {
            sql += ", " + pair.getValue() + "." + name.toUpperCase();
        }
        String additionalFields = "";
        String joins = "";
        namesMap.remove(entity.getClass().getSimpleName());
        for (Map.Entry<String, List<String>> entry : namesMap.entrySet()) {
            Pair<String, String> localPair = getTableNameFromClassName(entry.getKey());
            for (String name : entry.getValue()) {
                additionalFields += ", " + localPair.getValue() + "." + name.toUpperCase();
            }

            joins += "JOIN " + localPair.getKey() + " " + localPair.getValue() + " ON "
                    + pair.getValue() + "." + getForeignKeyFieldName(entry.getKey())+ "="
                    + localPair.getValue() + ".ID ";
        }

        sql += additionalFields + " FROM " + pair.getKey() + " " + pair.getValue() + " " + joins;

        sql += "WHERE " + pair.getValue() + ".ID=?";

        return sql;
    }

    public static String generateSelectByFieldSql(BaseEntityInterface entity, String field) {
        Pair<String, String> pair = getTableNameFromClassName(entity.getClass().getSimpleName());
        String sql = "SELECT " + pair.getValue() + ".ID";
        Map<String, List<String>> namesMap = new LinkedHashMap<>();
        getDeclaredFields(entity, namesMap);
        for (String name : namesMap.get(entity.getClass().getSimpleName())) {
            sql += ", " + pair.getValue() + "." + name.toUpperCase();
        }
        String additionalFields = "";
        String joins = "";
        namesMap.remove(entity.getClass().getSimpleName());
        for (Map.Entry<String, List<String>> entry : namesMap.entrySet()) {
            Pair<String, String> localPair = getTableNameFromClassName(entry.getKey());
            for (String name : entry.getValue()) {
                additionalFields += ", " + localPair.getValue() + "." + name.toUpperCase();
            }

            joins += "JOIN " + localPair.getKey() + " " + localPair.getValue() + " ON "
                    + pair.getValue() + "." + getForeignKeyFieldName(entry.getKey())+ "="
                    + localPair.getValue() + ".ID ";
        }

        sql += additionalFields + " FROM " + pair.getKey() + " " + pair.getValue() + " " + joins;

        sql += "WHERE " + pair.getValue() + "." + field + "=?";

        return sql;
    }

    private static String getForeignKeyFieldName(String tableName) {
        String fieldName = "";
        String[] words = tableName.split("(?=[A-Z])");
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            fieldName += word.toUpperCase();
            fieldName += "_";
        }
        return fieldName + "ID";
    }

    private static Pair<String, String> getTableNameFromClassName(String className) {
        String tableShortName= "";
        String[] words = className.split("(?=[A-Z])");
        String tableName = "";
        for (int index = (words.length == 1 ? 0 : 1); index < words.length; index++) {
            tableShortName += words[index].charAt(0);
            tableName += words[index].toUpperCase();
            if (index < words.length - 1) {
                tableName += "_";
            } else {
                tableName += "S";
            }
        }

        return new ImmutablePair<>(tableName, tableShortName);
    }

    private static void getDeclaredFields(BaseEntityInterface<Long> entity, Map<String, List<String>> namesMap) {
        List<String> names = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object o = (field.get(entity) == null)
                        ? field.getType().newInstance()
                        : field.get(entity);
                if (o instanceof BaseEntityInterface) {
                    names.add(getForeignKeyFieldName(o.getClass().getSimpleName()));
                    getDeclaredFields((BaseEntityInterface<Long>) o, namesMap);
                } else {
                    names.add(field.getName());
                }
            } catch (IllegalAccessException|InstantiationException e) {
                throw new GenericDaoException("cannot create sql query for " + entity.getClass().getSimpleName()
                        + "exception in field '" + field.getName() + "'. Root cause is ", e);
            }
        }
        namesMap.put(entity.getClass().getSimpleName(), names);
    }
}
