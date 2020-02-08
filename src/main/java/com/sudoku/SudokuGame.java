package com.sudoku;

import com.sudoku.user.Gameable;
import com.sudoku.user.TemplateGame;
import com.sudoku.user.TerminalGame;

public class SudokuGame {

    public static void main(String[] args) {
        while(true) {
            Gameable theGame = new TerminalGame();
            theGame.resolveSudoku();
        }
    }
}
