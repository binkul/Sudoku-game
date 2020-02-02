package com.sudoku.field;

import com.sudoku.constant.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuRow {
    private final List<SudokuElement> row;

    public SudokuRow() {
        row = createRow();
    }

    private List<SudokuElement> createRow() {
        return IntStream.range(0, Data.DIMENSION)
                .mapToObj(i -> new SudokuElement())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    SudokuElement getSudokuElement(int columnNr) {
        return row.get(columnNr);
    }

    public List<SudokuElement> getSudokuRow() {
        return row;
    }
}
