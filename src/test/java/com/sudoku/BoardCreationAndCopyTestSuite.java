package com.sudoku;

import com.sudoku.field.*;
import com.sudoku.user.GameFlow;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BoardCreationAndCopyTestSuite {

    @Before
    public void printBefore() {
        System.out.println("Test starts:");
    }

    @After
    public void printAfter() {
        System.out.println("Test finished");
    }

    @Test
    public void createTest() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.setNumber(0,1,9);
        sudokuBoard.setNumber(5,5,7);

        //When
        System.out.println("SudokuBoard creation ...");
        int number1 = sudokuBoard.getNumber(0,1);
        int number2 = sudokuBoard.getNumber(5,5);

        //Then
        Assert.assertEquals(9, number1);
        Assert.assertEquals(7, number2);
    }

    @Test
    public void deepCopyTest() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.setNumber(0,1,9);
        sudokuBoard.setNumber(5,5,7);

        //When
        System.out.println("Simple Deep Copy test ...");
        SudokuBoard deepCopy = sudokuBoard.deepCopy();
        int number1 = deepCopy.getNumber(0,1);
        int number2 = deepCopy.getNumber(5,5);

        //Then
        Assert.assertEquals(9, number1);
        Assert.assertEquals(7, number2);
    }

    @Test
    public void deepCopyWithChangeTest() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.setNumber(0,1,9);
        sudokuBoard.setNumber(7,4,7);

        //When
        System.out.println("DeepCopy with change values ...");
        SudokuBoard deepCopy = sudokuBoard.deepCopy();
        deepCopy.setNumber(8, 8, 6);
        int number1 = deepCopy.getNumber(8,8);
        int number2 = sudokuBoard.getNumber(8,8);

        //Then
        Assert.assertEquals(6, number1);
        Assert.assertEquals(-1, number2);
    }

    @Test
    public void deepCopyWithRemoveTest() {
        //Given
        SudokuBoard sudokuBoard = new SudokuBoard();

        //When
        System.out.println("DeepCopy with remove possible numbers test ...");
        SudokuBoard deepCopy = sudokuBoard.deepCopy();
        deepCopy.getElement(8,8).removeCandidate(2);
        deepCopy.getElement(8,8).removeCandidate(2);
        deepCopy.getElement(8,8).removeCandidate(3);
        boolean isNotPresent = deepCopy.getElement(8,8).containCandidate(3);
        boolean isPresent = deepCopy.getElement(8,8).containCandidate(4);
        int number1 = deepCopy.getElement(8, 8).getCandidatesSize();
        int number2 = sudokuBoard.getElement(8,8).getCandidatesSize();

        //Then
        Assert.assertEquals(7, number1);
        Assert.assertEquals(9, number2);
        Assert.assertFalse(isNotPresent);
        Assert.assertTrue(isPresent);
    }

    @Test
    public void takeOneSectionTest() {
        //given
        SudokuBoard sudokuBoard = new SudokuBoard();
        sudokuBoard.setNumber(3,6,9);
        sudokuBoard.setNumber(3,7,8);
        sudokuBoard.setNumber(3,8,6);
        sudokuBoard.setNumber(4,6,5);
        sudokuBoard.setNumber(4,7,7);
        sudokuBoard.setNumber(4,8,4);
        sudokuBoard.setNumber(5,6,1);
        sudokuBoard.setNumber(5,7,2);
        sudokuBoard.setNumber(5,8,3);

        //When
        System.out.println("Take one section ...");
        List<SudokuElement> elements = sudokuBoard.getOneSectionElements(5, 8);
        SudokuElement element1 = elements.get(0);
        SudokuElement element2 = elements.get(2);
        SudokuElement element3 = elements.get(3);
        SudokuElement element4 = elements.get(7);
        SudokuElement element5 = elements.get(8);
        System.out.println(element2);
        System.out.println(element4);
        System.out.println(element5);

        //Then
        Assert.assertEquals(9, elements.size());
        Assert.assertEquals(9, element1.getNumber());
        Assert.assertEquals(6, element2.getNumber());
        Assert.assertEquals(5, element3.getNumber());
        Assert.assertEquals(2, element4.getNumber());
        Assert.assertEquals(3, element5.getNumber());
    }
}
