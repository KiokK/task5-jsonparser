package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.util.*;

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
//		ans += Stream.of(array)
//				.map(Object::toString)
//				.collect(Collectors.joining(","));
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
        ans += String.valueOf(array[0]);
        for (int i = 1; i < array.length; i++)
            ans += ',' + String.valueOf(array[i]);

        ans += "]";
        return ans;
    }


    public static String objectArrayToJSONString(Object[] array) {
        if (array == null)
            return "null";
        if (array.length == 0)
            return "[]";
        String ans = "[";
        try {
            ans += MyJSONParser.parse(array[0]);
            for (int i = 1; i < array.length; i++) {
                ans += ",";
                ans += MyJSONParser.parse(array[i]);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ans += "]";
        return ans;
    }
}
