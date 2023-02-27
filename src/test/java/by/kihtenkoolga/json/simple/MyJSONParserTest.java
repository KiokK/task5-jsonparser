package by.kihtenkoolga.json.simple;

import by.kihtenkoolga.utill.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class MyJSONParserTest {

    @ParameterizedTest
    @MethodSource("argsForParseTest")
    void checkParseWithDifferentObjects(Object argument, String expected) throws IOException, NoSuchFieldException, IllegalAccessException {
        assertThat(MyJSONParser.parse(argument))
                .isEqualTo(expected);
    }

    static Stream<Arguments> argsForParseTest() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        Home home = new Home(1, "2", new Flat("123"));
        DifferentTest differentValues = new DifferentTest(1, "2", new Flat("123"));
        CoordinateTest coordinateTestValue = new CoordinateTest();
        Double doub = null;
        PersonTest personTest = null;

        return Stream.of(
                Arguments.of(home, gson.toJson(home)),
                Arguments.of(differentValues, gson.toJson(differentValues)),
                Arguments.of(coordinateTestValue, gson.toJson(coordinateTestValue)),
                Arguments.of(doub, gson.toJson(doub)),
                Arguments.of(personTest, gson.toJson(personTest))
        );
    }

    @Test
    void escapeParamString() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        String specSombols = "f\\A\\d\"d1\t\n\r\f\bc\"f ";

        assertThat("\""+MyJSONParser.escape(specSombols)+"\"")
                .isEqualTo(gson.toJson(specSombols));
    }

}
