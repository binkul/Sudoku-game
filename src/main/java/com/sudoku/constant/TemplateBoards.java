package com.sudoku.constant;

import com.sudoku.field.SudokuBoard;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateBoards {
    private static final String path = "template/boards.txt";
    private static final Random RANDOM = new Random();

    private List<SudokuBoard> sudokuBoards = new ArrayList<>();

    public TemplateBoards() {
        generateTemplates();
    }

    public SudokuBoard getSudokuTemplate() {
        int nr = RANDOM.nextInt(sudokuBoards.size());
        return sudokuBoards.get(nr).deepCopy();
    }

    private void generateTemplates() {
        SudokuBoard sudokuBoard = new SudokuBoard();
        List<String> templates = readTemplates();
        int rowIndex = 0;
        String row;
        if (templates == null) return;

        for (int i = 0; i < templates.size(); i++) {
            row = templates.get(i);
            if(row.toLowerCase().equals("start")) {
                sudokuBoard = new SudokuBoard();
                sudokuBoards.add(sudokuBoard);
                rowIndex = 0;
            } else if (row.length() == 17) {
                setRow(sudokuBoard, row, rowIndex);
                rowIndex++;
            } else {
                printLogError(row);
            }
        }
    }

    private void setRow(SudokuBoard sudokuBoard, String row, int rowIndex) {
        if (rowIndex > Data.DIMENSION) return;

        String[] values = row.split("\\|");
        for (int columnIndex = 0; columnIndex < Data.DIMENSION; columnIndex++) {
            if (!values[columnIndex].equals("0")) {
                sudokuBoard.setNumber(rowIndex, columnIndex, Integer.parseInt(values[columnIndex]));
            }
        }
    }

    private void printLogError(String row) {
        System.out.println("Error in line: '" + row + "'");
    }

    private boolean isFileExist(String fullPath) {
        return new File(fullPath).exists();
    }

    private List<String> readTemplates() {
        String fullPath = getPath(path).replace("file:/", "");
        try {
            if (isFileExist(fullPath)) {
                return Files.readAllLines(Paths.get(fullPath));
            }
        } catch (IOException ex) {
            System.out.println("File '" + fullPath + "' is empty!");
        }
        return null;
    }

    private String getPath(String path) {
        ClassLoader loader = getClass().getClassLoader();
        Object object = loader.getResource(path);
        if (object != null) {
            return object.toString().replaceAll("%20", " ");
        } else {
            return "";
        }
    }
}
