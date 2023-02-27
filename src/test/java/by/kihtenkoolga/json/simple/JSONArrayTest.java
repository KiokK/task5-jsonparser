package by.kihtenkoolga.json.simple;

import by.kihtenkoolga.utill.Flat;
import by.kihtenkoolga.utill.Home;
import by.kihtenkoolga.utill.CollectionsTest;
import by.kihtenkoolga.utill.CoordinateTest;
import by.kihtenkoolga.utill.DifferentTest;
import by.kihtenkoolga.utill.PersonTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class JSONArrayTest {

    @ParameterizedTest
    @MethodSource("argsProviderFactory")
    void toJSONString(Object[] argument, String expected) {
        assertThat(JSONArray.toJSONString(argument))
                .isEqualTo(expected);
    }

    static Stream<Arguments> argsProviderFactory() {
        Gson gson = new GsonBuilder().serializeNulls().create();

        Boolean[] boolArr = new Boolean[]{true, false, true, false};
        Character[] characterArr = new Character[]{'a', 'b', '2'};
        Double[] doubleArr = new Double[]{1.34, 3.13, 2321.3};
        Integer[] integerArr = new Integer[]{1, 2, 4, 5};
        String[] stringArr = new String[]{"Ivan", "Inga", null};
        String[] emptyArr = new String[]{};
        Object[] nullArr = null;

        return Stream.of(
                Arguments.of(boolArr, gson.toJson(boolArr)),
                Arguments.of(characterArr, gson.toJson(characterArr)),
                Arguments.of(doubleArr, gson.toJson(doubleArr)),
                Arguments.of(stringArr, gson.toJson(stringArr)),
                Arguments.of(integerArr, gson.toJson(integerArr)),
                Arguments.of(emptyArr, gson.toJson(emptyArr)),
                Arguments.of(nullArr, gson.toJson(nullArr))
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderForObjectArr")
    void objectArrayToJSONString(Object[] argument, String expected) {
        assertThat(JSONArray.objectArrayToJSONString(argument))
                .isEqualTo(expected);
    }

    static Stream<Arguments> argsProviderForObjectArr() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Flat[] arrFlats = new Flat[]{new Flat("fA1"), new Flat("fA2"), null};
        Home[] homeArr = new Home[]{new Home(1, "2", new Flat("123"))};
        DifferentTest[] homeTesrArr = new DifferentTest[]{new DifferentTest(1, "2", new Flat("123"))};
        CoordinateTest[] coordinateTests = new CoordinateTest[]{new CoordinateTest()};
        PersonTest[] personTests = new PersonTest[]{new PersonTest()};
        CollectionsTest[] collectionsTests = new CollectionsTest[]{new CollectionsTest()};
        String[] emptyArr = new String[]{};
        Object[] nullArr = null;

        return Stream.of(
                Arguments.of(arrFlats, gson.toJson(arrFlats)),
                Arguments.of(homeArr, gson.toJson(homeArr)),
                Arguments.of(homeTesrArr, gson.toJson(homeTesrArr)),
                Arguments.of(coordinateTests, gson.toJson(coordinateTests)),
                Arguments.of(personTests, gson.toJson(personTests)),
                Arguments.of(collectionsTests, gson.toJson(collectionsTests)),
                Arguments.of(emptyArr, gson.toJson(emptyArr)),
                Arguments.of(nullArr, gson.toJson(nullArr))
        );
    }
}
