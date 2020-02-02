package com.sudoku.constant;

public class Data {
    public static final int EMPTY = -1;
    public static final int DIMENSION = 9;
    public static final int SECTION_LENGTH = 3;

    public static final String MINUS_LINE = "---------------------------------------------------------";
    public static final String EQUAL_LINE = "=========================================================";
    public static final String TOP_COUNT = "    1     2     3      4     5     6      7     8     9";

    public static final String LEGEND = "-- Legend --\n" +
            ConsoleColors.WHITE_BOLD_BRIGHT.getCode() + "Filled by user.\n" +
            ConsoleColors.RED_BOLD_BRIGHT.getCode() + "Filled with 'SolverSingle' algorithm numbers.\n" +
            ConsoleColors.GREEN_BOLD_BRIGHT.getCode() + "Filled with 'SolverStandard' algorithm numbers.\n" +
            ConsoleColors.BLUE_BOLD_BRIGHT.getCode() + "Filled with 'SolverBackTrack' algorithm numbers.\n" +
            ConsoleColors.RESET.getCode() + "\n";
}
