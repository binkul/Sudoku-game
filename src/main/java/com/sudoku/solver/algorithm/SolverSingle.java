package com.sudoku.solver.algorithm;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.field.SudokuElement;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolverSingle {
    private final static String FONT_COLOR = ConsoleColors.RED_BOLD_BRIGHT.getCode();


    protected Result process(SudokuBoard sudokuBoard, int row, int column) {
        SudokuElement element = sudokuBoard.getElement(row, column);

        Set<Integer> existingNumbers = extractAllNumbers(sudokuBoard, row, column);
        for (Integer number : existingNumbers) {
            element.removeCandidate(number);
        }
        return addNumber(element);
    }

    Set<Integer> extractAllNumbers(SudokuBoard sudokuBoard, int row, int column) {
        List<SudokuElement> rowElements = sudokuBoard.getOneRowElements(row);
        List<SudokuElement> columnElements = sudokuBoard.getOneColumnElements(column);
        List<SudokuElement> sectionElements = sudokuBoard.getOneSectionElements(row,column);

        return Stream.of(rowElements, columnElements, sectionElements)
                .flatMap(Collection::stream)
                .map(SudokuElement::getNumber)
                .filter(i -> i != Data.EMPTY)
                .collect(Collectors.toSet());
    }

    private Result addNumber(SudokuElement element) {
        if (element.getCandidatesSize() == 0) {
            return Result.ERROR;
        } else if (element.getCandidatesSize() == 1) {
            element.setNumber(element.getCandidates().get(0), FONT_COLOR);
            return Result.ADDED;
        } else {
            return Result.NONE;
        }
    }

}