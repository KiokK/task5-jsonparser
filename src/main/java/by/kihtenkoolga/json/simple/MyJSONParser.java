package by.kihtenkoolga.json.simple;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class MyJSONParser {

    public static String parse(Object val) throws NoSuchFieldException, IllegalAccessException, IOException {
        if (val == null) return "null";
        String ans = "{";
        Class<?> cls = val.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            ans += "\"" + fields[i].getName() + "\":";
            fields[i].setAccessible(true);
            ans += writeJSONString(fields[i].get(val));
            if (fields.length > i + 1)
                ans += ",";
        }
        ans += "}";
        return ans;
    }

    public static String writeJSONString(Object value) throws IOException, NoSuchFieldException, IllegalAccessException {
        if (value == null)
            return "null";

        if (value instanceof String)
            return '\"' + escape((String) value) + '\"';

        if (value instanceof Character)
            return '\"' + value.toString() + '\"';

        if (value instanceof Double)
            if (((Double) value).isInfinite() || ((Double) value).isNaN())
                return "null";
            else
                return value.toString();


        if (value instanceof Float)
            if (((Float) value).isInfinite() || ((Float) value).isNaN())
                return "null";
            else
                return value.toString();

        if (value instanceof Number || value instanceof Boolean)
            return value.toString();

        if (value instanceof Map)
            return JSONObject.toJSONString((Map) value);

        if (value instanceof Collection)
            return JSONArray.toJSONString((Collection) value);

        if (value instanceof byte[])
            return JSONArray.<Byte>toJSONString(Arrays.stream((int[]) value)
                    .boxed().toArray(Byte[]::new));

        if (value instanceof short[])
            return JSONArray.<Short>toJSONString(Arrays.stream((int[]) value)
                    .boxed().toArray(Short[]::new));

        if (value instanceof int[])
            return JSONArray.<Integer>toJSONString(Arrays.stream((int[]) value)
                    .boxed().toArray(Integer[]::new));

        if (value instanceof long[])
            return JSONArray.<Long>toJSONString(Arrays.stream((long[]) value)
                    .boxed().toArray(Long[]::new));

        if (value instanceof float[])
            return JSONArray.<Float>toJSONString(Arrays.stream((double[]) value)
                    .boxed().toArray(Float[]::new));

        if (value instanceof double[])
            return JSONArray.<Double>toJSONString(Arrays.stream((double[]) value)
                    .boxed().toArray(Double[]::new));

        if (value instanceof boolean[])
            return JSONArray.toJSONString((boolean[]) value);


        if (value instanceof char[])
            return JSONArray.<Character>toJSONString(Arrays.stream((int[]) value)
                    .boxed().toArray(Character[]::new));

        //Для парсинга любых массивов: [], [][], [][][]...
        if (value.getClass().isArray()) {
            String ans = "[";
            ans += writeJSONString(Array.get(value, 0));
            for (int i = 1; i < Array.getLength(value); i++) {
                ans += "," + writeJSONString(Array.get(value, i));
            }
            return ans + "]";
        }

        if (value instanceof Object[]) {
            return JSONArray.objectArrayToJSONString((Object[]) value);
        }

        if (value instanceof Object)
            return parse(value);
        return value.toString();
    }

    public static String escape(String s) {
        if (s == null)
            return null;
        StringBuffer sb = new StringBuffer();
        escape(s, sb);
        return sb.toString();
    }

    public static void escape(String s, StringBuffer sb) {
        final int len = s.length();
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                case '\b':
                    sb.append("\\b");
                    break;
                case '\f':
                    sb.append("\\f");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                default:
                    sb.append(ch);
            }
        }
    }

}
