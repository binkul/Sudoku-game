package com.sudoku.field;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.Data;

public class PrintNormal implements Printable {
    private static final String NORMAL_COLOR = ConsoleColors.RESET.getCode();
    private final SudokuBoard sudokuBoard;

    public PrintNormal(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }

    @Override
    public void print() {
        System.out.println(Data.TOP_COUNT);
        System.out.println(" " + Data.MINUS_LINE);
        for (int row = 0; row < Data.DIMENSION; row++) {
            System.out.print(row + 1);
            printRow(row);
        }
    }

    private void printRow(int row) {
        StringBuilder line = new StringBuilder();
        String fontColor;
        int number;

        for (int col = 0; col < Data.DIMENSION; col++) {
            number = sudokuBoard.getNumber(row, col);
            fontColor = sudokuBoard.getFontColor(row, col);
            line.append((col ==3 || col == 6) ? "||  " : "|  ");
            line.append(number != Data.EMPTY ? fontColor + number + NORMAL_COLOR : " ");
            line.append("  ");
        }
        line.append("|");
        System.out.println(line.toString());
        printSeparator(row);
    }

    private static void printSeparator(int row) {
        if (row == 2 || row == 5) {
            System.out.println(" " + Data.EQUAL_LINE);
        } else {
            System.out.println(" " + Data.MINUS_LINE);
        }
    }


}
