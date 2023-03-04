package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class JSONMap {
    public static String toJSONString(Map map) {
        if (map == null)
            return "null";

        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        StringBuffer ans = new StringBuffer("{");
        while (iter.hasNext()) {
            if (first)
                first = false;
            else
                ans.append(',');
            Map.Entry entry = (Map.Entry) iter.next();

            if (entry.getKey() == null)
                ans.append("null");
            else
                ans.append('\"').append(MyJSONParser.escape(String.valueOf(entry.getKey()))).append('\"');
            ans.append(':');
            try {
                if (entry.getValue() == null)
                    ans.append("null");
                else
                    ans.append(MyJSONParser.writeJSONString(entry.getValue()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        ans.append('}');
        return String.valueOf(ans);
    }

}
