package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JSONObject {
    public static String toJSONString(Map map) {
        if (map == null)
            return "null";

        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        String ans = "{";
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                ans += ',';
            Map.Entry entry = (Map.Entry) iter.next();
            ans += '\"';
            ans += MyJSONParser.escape(String.valueOf(entry.getKey()));
            ans += '\"';
            ans += ':';
            try {
                ans += MyJSONParser.writeJSONString(entry.getValue());
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return ans + '}';
    }


    public static String toString(String key, Object value) {
        StringBuffer sb = new StringBuffer();
        sb.append('\"');
        if (key == null)
            sb.append("null");
        else
            MyJSONParser.escape(key, sb);
        sb.append('\"').append(':');

        try {
            sb.append(MyJSONParser.writeJSONString(value));
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }

}
