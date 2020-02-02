package com.sudoku.solver;

import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.field.SudokuElement;
import com.sudoku.field.SudokuRow;
import com.sudoku.solver.algorithm.Result;
import com.sudoku.solver.algorithm.SolverBackTrack;
import com.sudoku.solver.algorithm.SolverStandard;

import java.util.Collection;

public class Solver {
    private SudokuBoard sudokuBoard;
    private final SolverStandard solverStandard;
    private final SolverBackTrack solverBackTrack;

    public Solver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        solverStandard = new SolverStandard();
        solverBackTrack = new SolverBackTrack(this);
    }

    public boolean process() {
        runStandard(sudokuBoard);
        // run twice, because after start List<Integer> candidates of SudokuElement is full filled,
        // first run removes repeated candidates, second check unique values in candidates
        runStandard(sudokuBoard);
        if (runStandard(sudokuBoard) == Result.FULL_FILLED) {
            return true;
        } else {
            return solverBackTrack.process(sudokuBoard);
        }
    }

    public Result runStandard(SudokuBoard sudokuBoard) {
        int count;
        Result result;

        do {
            count = 0;
            for (int row = 0; row < Data.DIMENSION; row++) {
                for (int column = 0; column < Data.DIMENSION; column++) {
                    result = solverStandard.process(sudokuBoard, row, column);
                    if (result == Result.ERROR) return result;
                    if (result == Result.ADDED) count++;
                }
            }
        } while (count != 0);
        return isBoardFilled(sudokuBoard) ? Result.FULL_FILLED : Result.NONE;
    }

    private boolean isBoardFilled(SudokuBoard sudokuBoard) {
        return sudokuBoard.getRows().stream()
                .map(SudokuRow::getSudokuRow)
                .flatMap(Collection::stream)
                .map(SudokuElement::getNumber)
                .noneMatch(i -> i == Data.EMPTY);
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    public void setSudokuBoard(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
    }
}
