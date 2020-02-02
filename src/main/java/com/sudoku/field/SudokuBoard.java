package com.sudoku.field;

import com.sudoku.constant.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SudokuBoard {
    private final Printable printBoard;
    private List<SudokuRow> rows;

    public SudokuBoard() {
        this.printBoard = new PrintNormal(this);
        rows = createRows();
    }

    private List<SudokuRow> createRows() {
        return IntStream.range(0, Data.DIMENSION)
                .mapToObj(i -> new SudokuRow())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<SudokuRow> getRows() {
        return rows;
    }

    public SudokuRow getRow(int rowIndex) {
        return rows.get(rowIndex);
    }

    public SudokuElement getElement(int rowIndex, int columnIndex) {
        return getRow(rowIndex).getSudokuElement(columnIndex);
    }

    /**
     * set number via row, column and number
     */
    public void setNumber(int rowIndex, int columnIndex, int number) {
        getElement(rowIndex, columnIndex).setNumber(number);
    }

    /**
     * set number via row, column, number and additional console font color
     */
    public void setNumber(int rowIndex, int columnIndex, int number, String fontColor) {
        getElement(rowIndex, columnIndex).setNumber(number, fontColor);
    }

    public int getNumber(int rowIndex, int columnIndex) {
        return getElement(rowIndex, columnIndex).getNumber();
    }

    public void setCandidates(int rowIndex, int columnIndex, List<Integer> numbers) {
        getElement(rowIndex, columnIndex).setCandidates(numbers);
    }

    public String getFontColor(int rowIndex, int columnIndex) {
        return getElement(rowIndex, columnIndex).getFontColor();
    }

    public void setFontColor(int rowIndex, int columnIndex, String fontColor) {
        getElement(rowIndex, columnIndex).setFontColor(fontColor);
    }

    public List<SudokuElement> getOneRowElements(int rowIndex) {
        return getRow(rowIndex).getSudokuRow();
    }

    public List<SudokuElement> getOneColumnElements(int columnIndex) {
        return rows.stream()
                .map(i -> i.getSudokuElement(columnIndex))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<SudokuElement> getOneSectionElements(int rowIndex, int columnIndex) {
        List<SudokuElement> result = new ArrayList<>();
        int rowStart = (rowIndex / Data.SECTION_LENGTH) * Data.SECTION_LENGTH;
        int columnStart = (columnIndex / Data.SECTION_LENGTH) * Data.SECTION_LENGTH;

        for (int i = rowStart; i < rowStart + Data.SECTION_LENGTH; i++) {
            for (int j = columnStart; j < columnStart + Data.SECTION_LENGTH; j++) {
                SudokuElement s = getRow(i).getSudokuElement(j);
                result.add(s);
            }
        }

        return result;
    }

    public SudokuBoard deepCopy() {
        SudokuBoard clonedBoard = new SudokuBoard();

        for (int i = 0; i < Data.DIMENSION; i++) {
            for (int j = 0; j < Data.DIMENSION; j++) {
                SudokuElement oldElement = getRow(i).getSudokuElement(j);
                List<Integer> possibleNumbers = new ArrayList<>(oldElement.getCandidates());
                clonedBoard.setCandidates(i, j, possibleNumbers);
                clonedBoard.setNumber(i, j, oldElement.getNumber());
                clonedBoard.setFontColor(i, j, oldElement.getFontColor());
            }
        }

        return clonedBoard;
    }

    public void print() {
        printBoard.print();
    }
}
