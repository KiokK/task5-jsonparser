package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JSONArray {

    public static String toJSONString(Collection collection) {
        if (collection == null) return "null";

        boolean first = true;
        Iterator iter = collection.iterator();

        String ans = "[";
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                ans += ',';

            Object value = iter.next();
            if (value.equals(null))
                ans += "null";
            else {
                try {
                    ans += MyJSONParser.writeJSONString((Object) value);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ans += ']';
        return ans;
    }


    public static String toJSONString(boolean[] array) {
        if (array == null) return "null";
        if (array.length == 0)
            return "[]";
        String ans = "[";
        ans += String.valueOf(array[0]);
        for (int i = 1; i < array.length; i++)
            ans += ',' + String.valueOf(array[i]);

        ans += "]";
        return ans;
    }


    public static <T> String toJSONString(T[] array) {
        if (array == null)
            return "null";
        if (array.length == 0)
            return "[]";
        String ans = "[";
        String simb = "";
        if (array[0] instanceof Character || array[0] instanceof String)
            simb = "\"";

        final String strPart = simb;
        ans += Stream.of(array)
                .map(o -> (o == null) ? null : strPart + o.toString() + strPart)
                .collect(Collectors.joining(","));

        ans += "]";
        return ans;
    }


    public static String objectArrayToJSONString(Object[] array) {
        if (array == null)
            return "null";
        if (array.length == 0)
            return "[]";
        String ans = "[";
        ans += Stream.of(array)
                .map(o -> {
                    try {
                        return (o == null) ? null : MyJSONParser.parse(o);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining(","));

        ans += "]";
        return ans;
    }
}
