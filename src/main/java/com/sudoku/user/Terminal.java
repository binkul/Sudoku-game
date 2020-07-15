package com.sudoku.user;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.field.PrintFrameBoard;
import com.sudoku.field.PrintStandardBoard;
import com.sudoku.field.Printable;

import java.util.Scanner;

public class Terminal {
    private static final Scanner SCANNER = new Scanner(System.in);

    public boolean startNewRound() {
        do {
            printInvitation();
            String answer = SCANNER.nextLine();
            if (answer.toLowerCase().equals("y")) {
                return true;
            } else if (answer.toLowerCase().equals("n")) {
                return false;
            } else {
                printError("[y]/[n]");
            }
        } while (true);
    }

    public Printable setPrintType() {
        do {
            printChosePrintType();
            String answer = SCANNER.nextLine();
            if (answer.equals("1")) {
                return new PrintStandardBoard();
            } else if (answer.equals("2")) {
                return new PrintFrameBoard();
            } else {
                printError("[1]/[2]");
            }
        } while (true);
    }

    private void printInvitation() {
        System.out.println("\nStart new Game [y], or Exit [n]");
    }

    private void printError(String allowedType) {
        System.out.println(String.format("Only %s are allowed!", allowedType));
    }

    private void printChosePrintType() {
        System.out.println("\nChose print type:");
        System.out.println("[1] - Standard print");
        System.out.println("[2] - Pretty print");
    }

    String getData() {
        printMenuInvitation();
        String answer = SCANNER.nextLine();
        return answer.toLowerCase();
    }

    private void printMenuInvitation() {
        System.out.println("\nMenu:");
        System.out.println("- Numbers in format: [column,row,value] e.g. 1,2,3");
        System.out.println("- [SUDOKU] start the solver.");
        System.out.println("- [Exit] stop the game.");
    }

    void printInputSudokuError() {
        System.out.print(ConsoleColors.RED_BOLD_BRIGHT.getCode());
        System.out.println("\nThe sudoku is incorrect. The same number in row/column/section!");
        System.out.print(ConsoleColors.RESET.getCode());
    }

    void printNoSolution() {
        System.out.print(ConsoleColors.RED_BOLD_BRIGHT.getCode());
        System.out.println("\nThere is no solution for this Sudoku.");
        System.out.print(ConsoleColors.RESET.getCode());
    }

    void printInputDataError(String value) {
        System.out.print(ConsoleColors.RED_BOLD_BRIGHT.getCode());
        System.out.println("\nThe data are incorrect or wrong range [1-9]: '" + value + "'");
        System.out.print(ConsoleColors.RESET.getCode());
    }

    void printSolution() {
        System.out.println("\nSolution of the Sudoku:");
    }
}
