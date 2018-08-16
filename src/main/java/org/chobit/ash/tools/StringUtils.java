package org.chobit.ash.tools;

public final class StringUtils {


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }


    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }

}
