package by.kihtenkoolga.json.simple;

import by.kihtenkoolga.Flat;
import by.kihtenkoolga.Home;
import by.kihtenkoolga.utill.CoordinateTest;
import by.kihtenkoolga.utill.DifferentTest;
import by.kihtenkoolga.utill.PersonTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JSONArrayTest {

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void toJSONString(Object[] argument, String expected) {
        Assertions.assertThat(JSONArray.toJSONString(argument))
                .isEqualTo(expected);
    }

    static Stream<Arguments> argsProviderFactory() {
        return Stream.of(
                Arguments.of(new Boolean[]{true, false, true, false}, "[true,false,true,false]"),
                Arguments.of(new Character[]{'a', 'b', '2'}, "[a,b,2]"),
                Arguments.of(new Double[]{1.34, 3.13, 2321.3}, "[1.34,3.13,2321.3]"),
                Arguments.of(new Integer[]{1, 2, 4, 5}, "[1,2,4,5]"));
    }

    @ParameterizedTest
    @MethodSource("argsProviderForObjectArr")
    void objectArrayToJSONString(Object[] argument, String expected) {
        Assertions.assertThat(JSONArray.objectArrayToJSONString(argument))
                .isEqualTo(expected);
    }

    static Stream<Arguments> argsProviderForObjectArr() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Flat[] arrFlats = new Flat[]{new Flat("fA1"), new Flat("fA2"), null};
        Home[] homeArr = new Home[]{new Home(1, "2", new Flat("123"))};
        DifferentTest[] homeTesrArr = new DifferentTest[]{new DifferentTest(1, "2", new Flat("123"))};
        CoordinateTest[] coordinateTests = new CoordinateTest[]{new CoordinateTest()};
        PersonTest[] personTests = new PersonTest[]{new PersonTest()};

        return Stream.of(
                Arguments.of(arrFlats, gson.toJson(arrFlats)),
                Arguments.of(homeArr, gson.toJson(homeArr)),
                Arguments.of(homeTesrArr, gson.toJson(homeTesrArr)),
                Arguments.of(coordinateTests, gson.toJson(coordinateTests)),
                Arguments.of(personTests, gson.toJson(personTests))
        );
    }
}
