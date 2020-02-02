package com.sudoku.user;

import com.sudoku.constant.ConsoleColors;

import java.util.Scanner;

public class Terminal {
    private static final Scanner SCANNER = new Scanner(System.in);

    public boolean startNewGame() {
        do {
            printInvitation();
            String answer = SCANNER.nextLine();
            if (answer.toLowerCase().equals("y")) {
                return true;
            } else if (answer.toLowerCase().equals("n")) {
                return false;
            } else {
                printInvitationError();
            }
        } while (true);
    }

    private void printInvitation() {
        System.out.println("\nStart new Game [y], or Exit [n]");
    }

    private void printInvitationError() {
        System.out.println("Only [y]/[n] are allowed!");
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
