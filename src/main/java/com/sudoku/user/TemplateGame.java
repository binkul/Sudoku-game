package com.sudoku.user;

import com.sudoku.constant.TemplateBoards;
import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.solver.Solver;
import com.sudoku.solver.algorithm.Validator;

public class TemplateGame implements Gameable {
    private final Terminal terminal;
    private final TemplateBoards templateBoards;

    public TemplateGame() {
        terminal = new Terminal();
        templateBoards = new TemplateBoards();
    }

    @Override
    public void resolveSudoku() {
        boolean newGame = terminal.startNewGame();
        if (newGame) {
            startGame();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void startGame() {
        SudokuBoard sudokuBoard = templateBoards.getSudokuTemplate();
        System.out.println("Sudoku before resolve:");
        sudokuBoard.print();
        if (Validator.checkSudoku(sudokuBoard)) {
            run(sudokuBoard);
        } else {
            terminal.printInputSudokuError();
        }
    }

    private void run(SudokuBoard sudokuBoard) {
        Solver solver = new Solver(sudokuBoard);

        if (solver.process()) {
            terminal.printSolution();
            sudokuBoard = solver.getSudokuBoard();
            sudokuBoard.print();
            System.out.println(Data.LEGEND);
        } else {
            terminal.printNoSolution();
        }
    }
}
