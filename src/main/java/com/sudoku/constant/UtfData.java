package com.sudoku.constant;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UtfData {
    private static final String UTF_EQUALS = "\u2550";
    private static final String UTF_MINUS = "\u2500";
    private static final String UTF_MID_DOUBLE_DOWN = "\u2565";
    private static final String UTF_MID_SINGLE_DOWN = "\u252C";
    private static final String UTF_LEFT_CORNER_DOWN = "\u250C";
    private static final String UTF_RIGHT_CORNER_DOWN = "\u2510";
    private static final String UTF_MID_DOUBLE_UP = "\u2568";
    private static final String UTF_MID_SINGLE_UP = "\u2534";
    private static final String UTF_LEFT_CORNER_UP = "\u2514";
    private static final String UTF_RIGHT_CORNER_UP = "\u2518";
    private static final String UTF_LEFT_MID_SINGLE = "\u251C";
    private static final String UTF_RIGHT_MID_SINGLE = "\u2524";
    private static final String UTF_LEFT_MID_DOUBLE = "\u255E";
    private static final String UTF_RIGHT_MID_DOUBLE = "\u2561";
    private static final String UTF_CROSS_SINGLE = "\u253C";
    private static final String UTF_CROSS_SINGLE_DOUBLE = "\u256B";
    private static final String UTF_CROSS_DOUBLE = "\u256C";
    private static final String UTF_CROSS_DOUBLE_SINGLE = "\u256A";

    public static final String UTF_TOP_COUNT = "    1     2     3     4     5     6     7     8     9";

    public static final String UTF_LINE_TOP =
            UTF_LEFT_CORNER_DOWN +
                    getMinusLineSingleDown() + UTF_MID_DOUBLE_DOWN +
                    getMinusLineSingleDown() + UTF_MID_DOUBLE_DOWN +
                    getMinusLineSingleDown() + UTF_RIGHT_CORNER_DOWN;
    public static final String  UTF_LINE_BOTTOM =
            UTF_LEFT_CORNER_UP +
                    getMinusLineSingleUp() + UTF_MID_DOUBLE_UP +
                    getMinusLineSingleUp() + UTF_MID_DOUBLE_UP +
                    getMinusLineSingleUp() + UTF_RIGHT_CORNER_UP;
    public static final String UTF_SINGLE_SEPARATOR =
            UTF_LEFT_MID_SINGLE +
                    getMinusLineSingleCross() + UTF_CROSS_SINGLE_DOUBLE +
                    getMinusLineSingleCross() + UTF_CROSS_SINGLE_DOUBLE +
                    getMinusLineSingleCross() + UTF_RIGHT_MID_SINGLE;
    public static final String UTF_DOUBLE_SEPARATOR =
            UTF_LEFT_MID_DOUBLE +
                    getEqualsLineCross() + UTF_CROSS_DOUBLE +
                    getEqualsLineCross() + UTF_CROSS_DOUBLE +
                    getEqualsLineCross() + UTF_RIGHT_MID_DOUBLE;

    private static String getMinusLine() {
        return IntStream.range(0, 5).mapToObj(i -> UTF_MINUS).collect(Collectors.joining());
    }

    private static String getMinusLineSingleCross() {
        return getMinusLine() + UTF_CROSS_SINGLE + getMinusLine() + UTF_CROSS_SINGLE + getMinusLine();
    }

    private static String getMinusLineSingleDown() {
        return getMinusLine() + UTF_MID_SINGLE_DOWN + getMinusLine() + UTF_MID_SINGLE_DOWN + getMinusLine();
    }

    private static String getMinusLineSingleUp() {
        return getMinusLine() + UTF_MID_SINGLE_UP + getMinusLine() + UTF_MID_SINGLE_UP + getMinusLine();
    }

    private static String getEqualsLine() {
        return IntStream.range(0, 5).mapToObj(i -> UTF_EQUALS).collect(Collectors.joining());
    }

    private static String getEqualsLineCross() {
        return getEqualsLine() + UTF_CROSS_DOUBLE_SINGLE + getEqualsLine() + UTF_CROSS_DOUBLE_SINGLE + getEqualsLine();
    }
}
