package com.example.common;

import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;

/**
 * com.example.common
 *
 * @author mioto-qinxj
 * @date 2019/9/17
 */
public class StringUtil {

    public static JSONObject parse(JSONObject jsonObject) {
        JSONObject jo = new JSONObject();
        Iterator<String> iterator = jsonObject.keySet().iterator();
        String key;
        Object value;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = jsonObject.get(key);
            if (key.indexOf("_") > 0) {
                key = upperCaseKey(key);
            }
            jo.put(key, value);
        }
        return jo;
    }

    public static String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    public static String[] upperCaseKey(String[] strings) {
        String[] newStrings = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            newStrings[i] = upperCaseKey(strings[i]);
        }
        return newStrings;
    }

    public static String upperCaseKey(String key) {
        String[] keySplits = key.split("_");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(keySplits[0]);
        for (int i = 1; i < keySplits.length; i++) {
            stringBuilder.append(upperCase(keySplits[i]));
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(parse(JSONObject.parseObject("{'reg_date':'text'}")));
    }
}
