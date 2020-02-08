package com.sudoku.user;

public interface Gameable {
    String SUDOKU = "sudoku";
    String EXIT = "exit";

    void resolveSudoku();
    void startGame();
}
