package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class JSONArray {

    public static String toJSONString(Collection collection) {
        if (collection == null) return "null";

        boolean first = true;
        Iterator iter = collection.iterator();

        StringBuffer ans = new StringBuffer("[");
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                ans.append(',');

            Object value = iter.next();
            if (value.equals(null))
                ans.append("null");
            else {
                try {
                    ans.append(MyJSONParser.writeJSONString(value));
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ans.append(']');
        return String.valueOf(ans);
    }


    public static String toJSONString(Object array)  {
        if (array == null || array.toString().equals("null"))
            return "null";
        if (Array.getLength(array) == 0) return "[]";
        StringBuffer ans = new StringBuffer("[");
        try {
            ans.append(MyJSONParser.writeJSONString(Array.get(array, 0)));
            for (int i = 1; i < Array.getLength(array); i++)
                ans.append("," + MyJSONParser.writeJSONString(Array.get(array, i)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        ans.append("]");
        return String.valueOf(ans);
    }
}
