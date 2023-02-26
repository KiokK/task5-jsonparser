package by.kihtenkoolga.utill;

import by.kihtenkoolga.Flat;

import java.util.List;
import java.util.Map;

public class DifferentTest {
    private int a;
    private String str;
    private int[] mass = new int[]{1, 3, 5};
    private boolean[] arr = new boolean[]{true, false, true};
    private long[][] arrlong = new long[][]{{1}, {1}};
    private Flat flat;

    private Map mp = Map.of("me", "be", "be", "me");

    private List<Flat> list = List.of(new Flat("f1"), new Flat("f2"));

    private Flat[] flatArr = new Flat[]{new Flat("fA1"), new Flat("fA2")};

    public DifferentTest(int a, String str, Flat flat) {
        this.a = a;
        this.str = str;
        this.flat = flat;
    }
}
