package com.source.it.jdbc.utils;

import com.source.it.jdbc.exceptions.GenericDaoException;
import com.source.it.jdbc.model.BaseEntityInterface;
import com.source.it.jdbc.model.Status;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.lang.String.format;

public class SqlGeneratorUtils {
    private static final String SELECT_SQL_STRING   = "SELECT %s.ID %s %sFROM %s%s WHERE %s=?";
    private static final String CREATE_SQL_STRING   = "INSERT INTO %s (%s) VALUES (%s)";
    private static final String DELETE_SQL_STRING   = "DELETE FROM %s WHERE ID=?";
    private static final String UPDATE_SQL_STRING   = "UPDATE %s SET %s WHERE ID=%s";
    private static final String ID_FIELD            = "ID";
    private static final String SPLIT_BY_UPPER_CASE = "(?=[A-Z])";

    public static String generateSelectSql(BaseEntityInterface entity) {
        return generateSelectByFieldSql(entity, ID_FIELD);
    }

    public static String generateSelectByFieldSql(BaseEntityInterface entity, String field) {
        Pair<String, String> pair = getTableNameFromClassName(entity.getClass().getSimpleName());
        String additionalFromPart = "";
        String joins = "";

        Map<String, Pair<String, List<String>>>  namesMap = new LinkedHashMap<>();
        getDeclaredFields(entity, entity.getClass().getSimpleName(), namesMap);
        String fromPart = getNamesString(entity, pair, namesMap, true);

        namesMap.remove(entity.getClass().getSimpleName());
        for (Map.Entry<String, Pair<String, List<String>>> entry : namesMap.entrySet()) {
            Pair<String, String> localPair = getTableNameFromClassName(entry.getKey());
            for (String name : entry.getValue().getRight()) {
                additionalFromPart += ", " + localPair.getValue() + "." + name.toUpperCase();
            }
            additionalFromPart += " ";
            joins += " JOIN " + localPair.getKey() + " " + localPair.getValue() + " ON "
                    + getTableNameFromClassName(entry.getValue().getLeft()).getRight() + "." + getForeignKeyFieldName(entry.getKey()) + "="
                    + localPair.getValue() + ".ID";
        }

        /*Fix Exception in GenericDaoImplOrderTest.testUpdateOrder -
        * org.h2.jdbc.JdbcSQLException: Column "O.WARRANTY_PERIOD_ID" not found; SQL statement:
        SELECT O.ID, O.USER_ID, O.ITEM_ID, O.DATE, O.WARRANTY, O.STATUS, UR.ROLE, U.NAME, U.LASTNAME,
        U.LOGIN, U.PASSWORD, U.EMAIL, U.USER_ROLE_ID, M.MANUFACTURENAME, IT.ITEMTYPENAME, WP.DAYS,
        WP.WPNAME, I.MANUFACTURE_ID, I.ITEM_TYPE_ID, I.WARRANTY_PERIOD_ID, I.DATEOFSALE, I.SERIALNUMBER
        FROM ORDERS O JOIN USER_ROLES UR ON O.USER_ROLE_ID=UR.ID JOIN USERS U ON O.USER_ID=U.ID
        JOIN MANUFACTURES M ON O.MANUFACTURE_ID=M.ID JOIN ITEM_TYPES IT ON O.ITEM_TYPE_ID=IT.ID
        JOIN WARRANTY_PERIODS WP ON O.WARRANTY_PERIOD_ID=WP.ID JOIN ITEMS I ON O.ITEM_ID=I.ID WHERE O.ID=? [42122-195]
        * */

        return format(SELECT_SQL_STRING, pair.getValue(), fromPart,
                additionalFromPart, pair.getKey() + " " + pair.getValue(),
                joins, pair.getValue() + "." + field).replaceAll(" ,", ",");
    }

    public static String generateCreateSql(BaseEntityInterface entity) {
        String classname = entity.getClass().getSimpleName();
        Pair<String, String> pair = getTableNameFromClassName(classname);
        Map<String, Pair<String, List<String>>> namesMap = new LinkedHashMap<>();
        getDeclaredFields(entity, classname, namesMap);
        String namesString = getNamesString(entity, pair, namesMap, false).replaceFirst(", ", "");
        String valuesString = "";
        int index = 0;

        while (index < namesMap.get(classname).getRight().size() - 1) {
            valuesString += "?, ";
            ++index;
        }
        valuesString += "?";
        return format(CREATE_SQL_STRING, pair.getKey(), namesString, valuesString);
    }

    public static String generateDeleteSql(BaseEntityInterface entity) {
        String classname = entity.getClass().getSimpleName();
        Pair<String, String> pair = getTableNameFromClassName(classname);
        return format(DELETE_SQL_STRING, pair.getKey());
    }

    public static String generateUpdateSql(BaseEntityInterface entity) {
        String classname = entity.getClass().getSimpleName();
        Pair<String, String> pair = getTableNameFromClassName(classname);
        Map<String, Pair<String, List<String>>> namesMap = new LinkedHashMap<>();
        getDeclaredFields(entity, classname, namesMap);
        String valuesString = "";

        for (Field field : entity.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object o = field.get(entity);
                if (!( o instanceof BaseEntityInterface)) {
                    if (o instanceof Number) {
                        valuesString += ", " + field.getName().toUpperCase() + "=" + o;
                    } else {
                        valuesString += ", " + field.getName().toUpperCase() + "='" + o + "'";
                    }
                } else {
                    valuesString += ", " + getForeignKeyFieldName(o.getClass().getSimpleName())
                            + "=" + ((BaseEntityInterface) o).getId();
                }

            } catch (IllegalAccessException e) {
                throw new GenericDaoException("cannot create sql query for " + entity.getClass().getSimpleName()
                        + "exception in field '" + field.getName() + "'. Root cause is ", e);
            }
        }

        return format(UPDATE_SQL_STRING, pair.getKey(), valuesString.replaceFirst(", ", ""), entity.getId());

    }

    public static void fillObjectFromResultSet(ResultSet rs, BaseEntityInterface entity) throws SQLException {
        fillOneEntityFromResultSet(rs, entity);
        if (!rs.isLast()) {
            throw new GenericDaoException("More than 1" + entity.getClass().getSimpleName() + "was found with unique field ");
        }
    }

    private static String getNamesString(BaseEntityInterface entity, Pair<String, String> pair,
                                         Map<String, Pair<String, List<String>>>  namesMap, boolean addTableShorName) {
        String namesString = "";
        for (String name : namesMap.get(entity.getClass().getSimpleName()).getRight()) {
            namesString += ", " + (addTableShorName ?  pair.getValue() + "." : "") + name.toUpperCase();
        }
        return namesString;
    }

    private static void fillOneEntityFromResultSet(ResultSet rs, BaseEntityInterface entity) throws SQLException {
        entity.setId(rs.getLong(ID_FIELD));
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object inner;
                if (field.getType().equals(long.class) || field.getType().equals(Long.class)) {
                    field.setLong(entity, rs.getLong(field.getName().toUpperCase()));
                } else if (field.getType().equals(int.class) || field.getType().equals(Integer.class)) {
                    field.setInt(entity, rs.getInt(field.getName().toUpperCase()));
                } else if (field.getType().equals(String.class)) {
                    field.set(entity, rs.getString(field.getName().toUpperCase()));
                } else if (field.getType().equals(Date.class)) {
                    field.set(entity, rs.getDate(field.getName().toUpperCase()));
                } else if (field.getType().equals(Status.class)) {
                    field.set(entity, Status.valueOf(rs.getString(field.getName().toUpperCase())));
                } else if ((inner = getFieldIfNotNull(field, entity)) instanceof BaseEntityInterface) {
                    fillOneEntityFromResultSet(rs, (BaseEntityInterface) inner);
                    field.set(entity, inner);
                }
            } catch (Exception e) {
                throw new GenericDaoException("cannot parse sql response for " + entity.getClass().getSimpleName()
                        + "exception in field '" + field.getName() + "'. Root cause is ", e);
            }
        }
    }

    private static String getForeignKeyFieldName(String tableName) {
        String fieldName = "";
        String[] words = tableName.split(SPLIT_BY_UPPER_CASE);
        for (String word : words) {
            if ("".equals(word)) {
                continue;
            }
            fieldName += word.toUpperCase();
            fieldName += "_";
        }
        return fieldName + ID_FIELD;
    }

    private static Pair<String, String> getTableNameFromClassName(String className) {
        String tableShortName = "";
        String[] words = className.split(SPLIT_BY_UPPER_CASE);
        String tableName = "";
        for (int index = 0; index < words.length; index++) {
            if ("".equals(words[index])) {
                continue;
            }
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

    private static void getDeclaredFields(BaseEntityInterface<Long> entity, String ownerEntity, Map<String, Pair<String, List<String>>> namesMap) {
        List<String> names = new ArrayList<>();
        for (Field field : entity.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object o = getFieldIfNotNull(field, entity);
                if (o instanceof BaseEntityInterface) {
                    names.add(getForeignKeyFieldName(o.getClass().getSimpleName()));
                    namesMap.put(entity.getClass().getSimpleName(), new ImmutablePair<>(ownerEntity, names));
                    getDeclaredFields((BaseEntityInterface) o, entity.getClass().getSimpleName(), namesMap);
                } else {
                    names.add(field.getName());
                }
            } catch (Exception e) {
                throw new GenericDaoException("cannot create sql query for " + entity.getClass().getSimpleName()
                        + "exception in field '" + field.getName() + "'. Root cause is ", e);
            }
        }
        namesMap.put(entity.getClass().getSimpleName(), new ImmutablePair<>(ownerEntity, names));
    }

    private static Object getFieldIfNotNull(Field field, BaseEntityInterface entity) throws Exception {
        if (field == null) {
            return null;
        }
        if (field.get(entity) != null) {
            return field.get(entity);
        } else if ("java.sql.Date".equals(field.getType().getName())) {
            return new Date(0L);
        } else if (field.getType().isEnum()){
            return Status.NEW;
        } else {
            return field.getType().newInstance();
        }
    }
}