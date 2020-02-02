package com.sudoku.solver.algorithm;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.field.SudokuElement;

import java.util.*;
import java.util.stream.Collectors;

public class SolverStandard extends SolverSingle {
    private final static String FONT_COLOR = ConsoleColors.GREEN_BOLD_BRIGHT.getCode();

    public Result process(SudokuBoard sudokuBoard, int row, int column) {
        SudokuElement sudokuElement = sudokuBoard.getElement(row, column);
        if (sudokuElement.getNumber() != Data.EMPTY) return Result.NONE;

        Result result = super.process(sudokuBoard, row, column);
        if (result != Result.NONE) return result;

        Set<Integer> existingNumbers = super.extractAllNumbers(sudokuBoard, row, column);
        if (addUniqueFromExisting(sudokuBoard.getOneRowElements(row), sudokuElement, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudokuBoard.getOneColumnElements(column), sudokuElement, existingNumbers)) return Result.ADDED;
        if (addUniqueFromExisting(sudokuBoard.getOneSectionElements(row, column), sudokuElement, existingNumbers)) return Result.ADDED;

        return Result.NONE;
    }

    private boolean addUniqueFromExisting(List<SudokuElement> elements, SudokuElement element, Set<Integer> existingNumbers) {
        List<Integer> unique = findUniqueCandidates(elements);
        for (Integer number : unique) {
            if (element.containCandidate(number) && !existingNumbers.contains(number)) {
                element.setNumber(number, FONT_COLOR);
                return true;
            }
        }
        return false;
    }

    private List<Integer> findUniqueCandidates(List<SudokuElement> elements) {
        List<Integer> allNumbers = elements.stream()
                .filter(i -> i.getNumber() == Data.EMPTY)
                .map(SudokuElement::getCandidates)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return getUnique(allNumbers);
    }

    private List<Integer> getUnique(List<Integer> numbers) {
        List<Integer> tmp = new ArrayList<>();
        List<Integer> unique = new LinkedList<>();

        for (Integer number : numbers) {
            if (!tmp.contains(number)) {
                tmp.add(number);
                unique.add(number);
            } else {
                unique.remove(number);
            }
        }
        return unique;
    }
}
