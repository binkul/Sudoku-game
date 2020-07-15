package com.sudoku.user;

import com.sudoku.constant.Data;
import com.sudoku.constant.TemplateBoards;
import com.sudoku.field.SudokuBoard;
import com.sudoku.solver.Solver;
import com.sudoku.solver.algorithm.Validator;

public class TemplateGame implements Gameable {
    private final GameFlow gameFlow;
    private final TemplateBoards templateBoards;

    public TemplateGame(GameFlow gameFlow) {
        this.gameFlow = gameFlow;
        templateBoards = new TemplateBoards();
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
        SudokuBoard sudokuBoard = templateBoards.getSudokuTemplate();
        System.out.println("Sudoku before resolve:");
        gameFlow.printBoard(sudokuBoard);
        if (Validator.checkSudoku(sudokuBoard)) {
            run(sudokuBoard);
        } else {
            gameFlow.getTerminal().printInputSudokuError();
        }
    }

    private void run(SudokuBoard sudokuBoard) {
        Solver solver = new Solver(sudokuBoard);

        if (solver.process()) {
            gameFlow.getTerminal().printSolution();
            sudokuBoard = solver.getSudokuBoard();
            gameFlow.printBoard(sudokuBoard);
            System.out.println(Data.LEGEND);
        } else {
            gameFlow.getTerminal().printNoSolution();
        }
    }
}
