//
//  SudokuTesting.cpp
//  Sudoku
//
//  Created by Evelyn Krasnik on 3/6/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//


#include <iostream>
#include <sstream> // std::ostringstream
#include "sudoku.h"
#include "catch.hpp"
#include <string>
#include <vector>


using namespace std;

Sudoku* sudoku = new Sudoku();
vector<int> board;
string puzzle = "___8_5____3__6___7_9___38___4795_3______71_9____2__5__1____248___9____5______6___";

vector<int> InitializeVector() {
    string puzzle = "___8_5____3__6___7_9___38___4795_3______71_9____2__5__1____248___9____5______6___";
    vector<int> correct_board;
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(8);
    correct_board.push_back(0);
    correct_board.push_back(5);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(3);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(6);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(7);
    correct_board.push_back(0);
    correct_board.push_back(9);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(3);
    correct_board.push_back(8);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(4);
    correct_board.push_back(7);
    correct_board.push_back(9);
    correct_board.push_back(5);
    correct_board.push_back(0);
    correct_board.push_back(3);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(7);
    correct_board.push_back(1);
    correct_board.push_back(0);
    correct_board.push_back(9);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(2);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(5);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(1);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(2);
    correct_board.push_back(4);
    correct_board.push_back(8);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(9);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(5);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(6);
    correct_board.push_back(0);
    correct_board.push_back(0);
    correct_board.push_back(0);
    board = correct_board;
    return correct_board;
}


TEST_CASE("output stream") {
    std::ostringstream oss;
    
    sudoku->PassString("The solutions are...\n", oss);
    REQUIRE(oss.str() == "The solutions are...\n");
}


TEST_CASE("reads from the correct file")
{
    string file = sudoku->InitializePuzzleFile();
    REQUIRE(file =="/Users/evelynkrasnik/Documents/sudoku-evelynk2/Sudoku/Sudoku/sudokufile.spf");
}


TEST_CASE("solutions will be written to console")
{
    REQUIRE(false == sudoku->InitializeSolutionFile("console"));
}

TEST_CASE("solutions will be written to a file")
{
    REQUIRE(true == sudoku->InitializeSolutionFile("example.txt"));
}

TEST_CASE("correct sudoku board")
{
    vector<int> board = sudoku->InitializeSudokuBoard(puzzle);
    
    REQUIRE(board == InitializeVector());
}

TEST_CASE("reformat sudoku board to string")
{
    InitializeVector();
    
    REQUIRE(puzzle == sudoku->Reformat(board));
}

TEST_CASE("gets correct box")
{
    REQUIRE(2 == sudoku->GetBox(15));
}

TEST_CASE("gets correct last box")
{
    REQUIRE(9 == sudoku->GetBox(80));
}


TEST_CASE("gets first and last row") {
    
    CHECK(9 == sudoku->GetRow(80));
    CHECK(1 == sudoku->GetRow(9));
   
}

TEST_CASE("get first and last column") {
    
    CHECK(9 == sudoku->GetColumn(9));
    CHECK(1 == sudoku->GetColumn(37));

}


TEST_CASE("checks that row does NOT have an equal number") {
    
    InitializeVector();
    CHECK(false == sudoku->CheckRow(board, 4, 3));
    CHECK(false == sudoku->CheckRow(board, 2, 8));

}

TEST_CASE("checks that row does have an equal number") {
    
    InitializeVector();
    CHECK(true == sudoku->CheckRow(board, 2, 6));
    CHECK(true == sudoku->CheckRow(board, 5, 8));


}

TEST_CASE("checks that column does NOT have an equal number") {
    
    InitializeVector();
    CHECK(false == sudoku->CheckColumn(board, 3, 4));
    CHECK(false == sudoku->CheckColumn(board, 9, 1));

}

TEST_CASE("checks that column does have an equal number") {
    
    InitializeVector();
    CHECK(true == sudoku->CheckColumn(board, 6, 5));
    CHECK(true == sudoku->CheckColumn(board, 5, 6));
}

TEST_CASE("checks that box does NOT have an equal number") {
    
    InitializeVector();
    CHECK(false == sudoku->CheckBox(board, 3, 8));
    CHECK(false == sudoku->CheckBox(board, 2, 9));


}

TEST_CASE("checks that box does have an equal number") {
    
    InitializeVector();
    CHECK(true == sudoku->CheckBox(board, 3, 1));
    CHECK(true == sudoku->CheckBox(board, 4, 9));
}


TEST_CASE("finds unassigned values")
{
    InitializeVector();
    CHECK(0 == sudoku->FindZeros(board, 0, 81));
    CHECK(32 == sudoku->FindZeros(board, 28, 81));
}

TEST_CASE("returns that there are no unassigned values")
{
    InitializeVector();
    REQUIRE(-1 == sudoku->FindZeros(board, 28, 31));
}

TEST_CASE("puzzle is not solved")
{
    InitializeVector();
    REQUIRE(false == sudoku->IsSolved(board));
}

TEST_CASE("can insert a value into the puzzle")
{
    InitializeVector();
    REQUIRE(true == sudoku->CanSolve(board, 7, 1));
}



