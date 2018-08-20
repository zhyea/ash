package org.chobit.ash.tools;

import java.util.HashMap;
import java.util.Map;

/**
 * @author robin
 */
public class UrlUtils {


    public static Map<String, String> parse(String url) {
        String[] arr = url.split("[?]]");
        if (arr.length > 1) {
            return parseParams(arr[1]);
        }
        return new HashMap<>(0);
    }


    private static Map<String, String> parseParams(String params) {
        Map<String, String> requests = new HashMap<>(2);
        String[] arrSplit = params.split("[&]");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = strSplit.split("[=]");
            if (arrSplitEqual.length > 1) {
                requests.put(arrSplitEqual[0], arrSplitEqual[1]);
            } else {
                if (arrSplitEqual[0] != "") {
                    requests.put(arrSplitEqual[0], "");
                }
            }
        }
        return requests;
    }


}
