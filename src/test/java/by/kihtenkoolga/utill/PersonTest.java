package by.kihtenkoolga.utill;

import java.util.Map;

public class PersonTest {
    private String name = "Ivan";
    public Map agePairs = Map.of(
            "Angel", "12",
            "Kirill", 13);

    public PersonTest() {

    }

    public PersonTest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
