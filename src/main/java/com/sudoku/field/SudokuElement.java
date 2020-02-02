package com.sudoku.field;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuElement {
    private static final String FONT_COLOR = ConsoleColors.WHITE_BOLD.getCode();
    private int number;
    private List<Integer> candidates;
    private String fontColor;

    public SudokuElement() {
        number = Data.EMPTY;
        candidates = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        fontColor = FONT_COLOR;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setNumber(int number, String fontColor) {
        this.number = number;
        this.fontColor = fontColor;
    }

    public List<Integer> getCandidates() {
        return candidates;
    }

    void setCandidates(List<Integer> candidates) {
        this.candidates = candidates;
    }

    String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public void removeCandidate(Integer number) {
        candidates.remove(number);
    }

    public int getCandidatesSize() {
        return candidates.size();
    }

    public Integer getCandidate(int index) {
        return candidates.get(index);
    }

    public boolean containCandidate(Integer number) {
        return candidates.contains(number);
    }

    @Override
    public String toString() {
        return "SudokuElement{" + "Number = " + number +
                ", Possible Numbers = " +
                candidates.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(", ", "[", "]"))+
                '}';
    }
}
