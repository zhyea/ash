package org.chobit.ash.tools;

/**
 * @author robin
 */
public abstract class StringUtils {


    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }


    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }


}
