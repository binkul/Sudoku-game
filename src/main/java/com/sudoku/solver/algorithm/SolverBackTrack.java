package com.sudoku.solver.algorithm;

import com.sudoku.constant.ConsoleColors;
import com.sudoku.constant.Data;
import com.sudoku.field.SudokuBoard;
import com.sudoku.field.SudokuElement;
import com.sudoku.solver.Solver;

import java.util.ArrayDeque;
import java.util.Deque;

public class SolverBackTrack {
    private final static String FONT_COLOR = ConsoleColors.BLUE_BOLD_BRIGHT.getCode();

    private final Solver solver;
    private SudokuBoard sudokuBoardCopy;
    private SudokuElement sudokuElement;

    public SolverBackTrack(Solver solver) {
        this.solver = solver;
    }

    public boolean process(SudokuBoard sudokuBoard) {
        this.sudokuBoardCopy = sudokuBoard;
        Deque<StackElement> stack = new ArrayDeque<>();
        StackElement stackElement;
        Result result = Result.NONE;
        int index;

        while(true) {

            switch (result) {
                case FULL_FILLED:
                    solver.setSudokuBoard(sudokuBoardCopy);
                    return true;
                case ERROR:
                    boolean loop;
                    do {
                        if (stack.size() == 0) return false;
                        stackElement = stack.pop();
                        index = (stackElement.getNumberIndex()) + 1;
                        if (index < stackElement.getCandidatesSize()) {
                            // recover present state
                            sudokuBoardCopy = stackElement.getSudokuBoard();
                            sudokuElement = stackElement.getSudokuElement();
                            putOnStack(stack, index);
                            loop = false;
                        } else {
                            loop = true;
                        }
                    } while (loop);
                    break;
                default:
                    sudokuElement = findFirstEmpty(sudokuBoardCopy);
                    putOnStack(stack, 0);
            }
            result = solver.runStandard(sudokuBoardCopy);
        }
    }

    private void putOnStack(Deque<StackElement> stack, int index) {

        StackElement stackElement = new StackElement(sudokuBoardCopy, sudokuElement, index);
        stack.push(stackElement);

        sudokuBoardCopy = sudokuBoardCopy.deepCopy();
        sudokuElement = findFirstEmpty(sudokuBoardCopy);

        int number = sudokuElement.getCandidate(index);
        sudokuElement.setNumber(number, FONT_COLOR);
    }

    private SudokuElement findFirstEmpty(SudokuBoard sudokuBoard) {
        for (int i = 0; i < Data.DIMENSION; i++) {
            for (int j = 0; j < Data.DIMENSION; j++) {
                if (sudokuBoard.getNumber(i, j) == Data.EMPTY) return sudokuBoard.getElement(i, j);
            }
        }
        return null;
    }
}
