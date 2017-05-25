package com.source.it.utils;

public class GetClassUtil {
    private GetClassUtil() {}

    public static String getClassName() {
        try {
            throw new Exception();
        } catch (Exception e) {
            return e.getStackTrace()[1].getClassName();
        }
    }
}
