package com.sudoku.user;

import com.sudoku.field.Printable;
import com.sudoku.field.SudokuBoard;

public class GameFlow {
    private final Terminal terminal;
    private Printable printType;

    public GameFlow() {
        this.terminal = new Terminal();
    }

    Terminal getTerminal() {
        return terminal;
    }

    void printBoard(SudokuBoard sudokuBoard) {
        printType.print(sudokuBoard);
    }

    boolean startNewRound() {
        return terminal.startNewRound();
    }

    public void setPrintType() {
        this.printType = terminal.setPrintType();
    }
}
