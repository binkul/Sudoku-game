package com.sudoku.user;

import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.solver.Solver;
import com.sudoku.solver.algorithm.Validator;

public class TerminalGame implements Gameable {
    private final GameFlow gameFlow;
    private final Solver solver;
    private SudokuBoard sudokuBoard;
    private int row, column, number;

    public TerminalGame(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        sudokuBoard = new SudokuBoard();
        solver = new Solver(sudokuBoard);
    }

    @Override
    public void resolveSudoku() {
        boolean newGame = gameFlow.startNewRound();
        if (newGame) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void startGame() {
        Command command = Command.WAIT;
        while (command != Command.START) {
            gameFlow.printBoard(sudokuBoard);
            command = inputData(gameFlow.getTerminal().getData());
            if (command == Command.EXIT) return;
        }
        if (Validator.checkSudoku(sudokuBoard)) {
            run(solver);
        } else {
            gameFlow.getTerminal().printInputSudokuError();
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
                gameFlow.getTerminal().printInputDataError(value);
            }
        }
        return Command.WAIT;
    }

    private void run(Solver solver) {
        if (solver.process()) {
            gameFlow.getTerminal().printSolution();
            sudokuBoard = solver.getSudokuBoard();
            gameFlow.printBoard(sudokuBoard);
            System.out.println(Data.LEGEND);
        } else {
            gameFlow.getTerminal().printNoSolution();
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
