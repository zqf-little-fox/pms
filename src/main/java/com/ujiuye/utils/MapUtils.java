package com.ujiuye.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapUtils {

    public static Map<String, String> parseParameterMapToMyBatisMap(Map<String, Object> parameterMap) {
        Map<String,String> resultMap = new HashMap<>();
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            if (key.contains("like")){
                key = key.substring(key.indexOf("_")+1);
            }
            if (key.contains("sort")){
                key = key.substring(key.indexOf("_")+1);
            }
            resultMap.put(key,value);
        }
        return resultMap;
    }

    public static String parseParameterMapToString(Map<String, Object> parameterMap) {
        Set<Map.Entry<String, Object>> entries = parameterMap.entrySet();
        String result = "";
        for (Map.Entry<String, Object> entry : entries) {
            String key = entry.getKey();
            String value = (String)entry.getValue();
            result = result + "&search_" + key + "=" + value;
        }
        return result;
    }
}
