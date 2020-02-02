package com.sudoku.field;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.UtfData;
import com.sudoku.constant.Data;

public class PrintUtf implements Printable{
    private static final String FRAME_COLOR = ConsoleColors.RESET.getCode();
    private static final String NORMAL_COLOR = ConsoleColors.RESET.getCode();
    private final SudokuBoard sudokuBoard;

    public PrintUtf(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    @Override
    public void print() {
        System.out.println(UtfData.UTF_TOP_COUNT);
        System.out.print(FRAME_COLOR);
        System.out.println(" " + UtfData.UTF_LINE_TOP);
        for (int row = 0; row < Data.DIMENSION; row++) {
            System.out.print(row + 1);
            printRow(row);
        }
        System.out.println(FRAME_COLOR);
        System.out.println(" " + UtfData.UTF_LINE_BOTTOM);
        System.out.print(NORMAL_COLOR);
    }

    private void printRow(int row) {
        StringBuilder line = new StringBuilder();
        int number;
        String fontColor;

        for (int col = 0; col < Data.DIMENSION; col++) {
            number = sudokuBoard.getNumber(row, col);
            fontColor = sudokuBoard.getFontColor(row, col);
            line.append(FRAME_COLOR);
            line.append((col ==3 || col == 6) ? "\u2551  " : "|  ");
            line.append(number != Data.EMPTY ? fontColor + number + FRAME_COLOR : " ");
            line.append("  ");
        }
        line.append("|");
        System.out.println(line.toString());
        printSeparator(row);
    }

    private void printSeparator(int row) {
        if (row == 2 || row == 5) {
            System.out.println(FRAME_COLOR + " " + UtfData.UTF_DOUBLE_SEPARATOR);
        } else if (row < Data.DIMENSION - 1) {
            System.out.println(FRAME_COLOR + " " + UtfData.UTF_SINGLE_SEPARATOR);
        }
    }
}
