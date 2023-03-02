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

            if (entry.getKey() == null)
                ans += "null";
            else
                ans += '\"' + MyJSONParser.escape(String.valueOf(entry.getKey())) + '\"';
            ans += ':';
            try {
                if (entry.getValue() == null)
                    ans += "null";
                else
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

}
