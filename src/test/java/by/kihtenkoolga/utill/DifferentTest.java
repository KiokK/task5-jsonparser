package by.kihtenkoolga.utill;

import java.util.List;
import java.util.Map;

public class DifferentTest {
    private int a;
    private String str;

    private Flat flat;

    private Map mp = Map.of("me", "be", "be", "me");

    private Flat[] flatArr = new Flat[]{new Flat("fA1"), new Flat("fA2")};

    public DifferentTest(int a, String str, Flat flat) {
        this.a = a;
        this.str = str;
        this.flat = flat;
    }
}
