package com.sudoku.solver.algorithm;

import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.field.SudokuElement;

import java.util.List;
import java.util.stream.Collectors;

public class Validator {

    public static boolean checkSudoku(SudokuBoard sudokuBoard) {
        boolean result;
        for (int row = 0; row < Data.DIMENSION; row++) {
            for (int column = 0; column < Data.DIMENSION; column++) {
                result = checkUnique(sudokuBoard.getOneRowElements(row)) &
                        checkUnique(sudokuBoard.getOneColumnElements(column)) &
                        checkUnique(sudokuBoard.getOneSectionElements(row, column));
                if (!result) return false;
            }
        }
        return true;
    }

    private static boolean checkUnique(List<SudokuElement> elements) {
        return getAll(elements) == getUnique(elements);
    }

    private static long getAll(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .count();
    }

    private static long getUnique(List<SudokuElement> elements) {
        return elements.stream()
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.toSet())
                .size();
    }

}
