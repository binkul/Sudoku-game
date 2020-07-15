package com.sudoku;

import com.sudoku.user.Gameable;
import com.sudoku.user.GameFlow;
import com.sudoku.user.TemplateGame;
import com.sudoku.user.TerminalGame;

public class SudokuGame {

    public static void main(String[] args) {

        GameFlow gameFlow = new GameFlow();
        gameFlow.setPrintType();

        while(true) {
            Gameable theGame = new TemplateGame(gameFlow);
            theGame.resolveSudoku();
        }
    }
}
