package by.kihtenkoolga.json.simple;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


class JSONMapTest {

    @ParameterizedTest
    @MethodSource("argsForMapTests")
    void toJSONString(Map argument, String expected) {
        assertThat(JSONMap.toJSONString(argument))
                .isEqualTo(expected);
    }
    static Stream<Arguments> argsForMapTests() throws JsonProcessingException {
        Gson gson = new GsonBuilder().serializeNulls().create();
        ObjectMapper mapper = new ObjectMapper();

        Map<Integer, String> map = Map.of(1, "", 2, "3sd");
        HashMap<String, Character> hashMap = new HashMap<>(){{
            put("", 'a');
            put("qadw", 'b');
            put("-", 'f');
            put("wesf", null);
        }};
        String expectedHashMap = mapper.writeValueAsString(hashMap);

        TreeMap<String, Double> treeMap = new TreeMap<>(){{
            put("", 12.0);
            put("-", 12312.);
            put("wesf", null);
        }};
        String expectedTreeMap = mapper.writeValueAsString(treeMap);

        return Stream.of(
                Arguments.of(null, gson.toJson(null)),
                Arguments.of(map, gson.toJson(map)),
                Arguments.of(hashMap, expectedHashMap),
                Arguments.of(treeMap, expectedTreeMap)
        );
    }
}
