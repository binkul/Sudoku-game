package com.sudoku.user;

import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.solver.Solver;
import com.sudoku.solver.algorithm.Validator;

public class TerminalGame implements Gameable {
    private final Terminal terminal;
    private final Solver solver;
    private SudokuBoard sudokuBoard;
    private int row, column, number;

    public TerminalGame() {
        terminal = new Terminal();
        sudokuBoard = new SudokuBoard();
        solver = new Solver(sudokuBoard);
    }

    @Override
    public boolean resolveSudoku() {
        boolean newGame = terminal.startNewGame();
        if (newGame) {
            startGame();
        }
        return !newGame;
    }

    @Override
    public void startGame() {
        Command command = Command.WAIT;
        while (command != Command.START) {
            sudokuBoard.print();
            command = inputData(terminal.getData());
            if (command == Command.EXIT) return;
        }
        if (Validator.checkSudoku(sudokuBoard)) {
            run(solver);
        } else {
            terminal.printInputSudokuError();
        }
    }

    private Command inputData(String value) {
        if (value.equals(SUDOKU)) {
            return Command.START;
        } else if (value.equals(EXIT)) {
            return Command.EXIT;
        } else {
            if (validate(value)) {
                sudokuBoard.setNumber(row - 1, column - 1, number);
            } else {
                terminal.printInputDataError(value);
            }
        }
        return Command.WAIT;
    }

    private void run(Solver solver) {
        if (solver.process()) {
            terminal.printSolution();
            sudokuBoard = solver.getSudokuBoard();
            sudokuBoard.print();
            System.out.println(Data.LEGEND);
        } else {
            terminal.printNoSolution();
        }
    }

    private boolean validate(String value) {
        if (value.length() != 5) return false;

        String[] values = value.split(",");
        if (values.length != 3) return false;

        try {
            column = Integer.parseInt(values[0]);
            row = Integer.parseInt(values[1]);
            number = Integer.parseInt(values[2]);
            return checkNumberRange(row, column, number);
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private boolean checkNumberRange(int row, int column, int number) {
        return (row > 0 && row <= Data.DIMENSION) && (column > 0 && column <= Data.DIMENSION) && (number > 0 && number <= Data.DIMENSION);
    }
}
