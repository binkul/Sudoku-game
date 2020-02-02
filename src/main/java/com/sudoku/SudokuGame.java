package com.sudoku;

import com.sudoku.user.Gameable;
import com.sudoku.user.TemplateGame;
import com.sudoku.user.TerminalGame;

public class SudokuGame {

    public static void main(String[] args) {
        boolean gameFinished = false;

        while(!gameFinished) {
            Gameable theGame = new TerminalGame();
            gameFinished = theGame.resolveSudoku();
        }
    }
}
