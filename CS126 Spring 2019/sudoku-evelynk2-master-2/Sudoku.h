//
//  Sudoku.h
//  Sudoku
//
//  Created by Evelyn Krasnik on 3/6/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#ifndef main_h
#define main_h

#pragma once
#include <string>
#include <vector>

using namespace std;

class Sudoku {
private:
    const int kSizeOfBoard = 81;
    const int kSizeOfRow = 9;
    const int kSizeOfBox = 3;
    
    vector<int> sudoku_board;
    int spotInBoard = 1;
    string user_input = "";
    string file_user_input= "";
    string solution_user_input = "";
    string solution = "___8_5____3__6___7_9___38___4795_3______71_9____2__5__1____248___9____5______6___";
    int line_number = 0;
    bool solutions_to_file = false;
    int move = 0;
    bool SudokuSolved = false;
    
    
public:
    
    void RunPuzzle();
    string InitializePuzzleFile();
    bool InitializeSolutionFile(string userinput);
    vector<int> InitializeSudokuBoard(string puzzle);
    string PrintSolutions(string solution);
    bool RecursiveSolve(vector<int> &vect, int spot, int endspot);
    
    bool IsSolved(vector<int> vect);
    int FindZeros(vector<int> vect, int spot, int endspot);
    bool canSolve(vector<int> vect, int new_number, int spot);
    bool CheckRow(vector<int> vect, int new_number, int spot);
    bool CheckColumn(vector<int> vect, int new_number, int spot);
    int GetBox(int spot);
    int GetRow(int spot);
    int GetColumn(int spot);
    bool CheckBox(vector<int> vect, int new_number, int box);
    string reformat(vector<int> s);
    
    friend ostream & operator << (ostream &out, const Sudoku &c);
    friend istream & operator >> (istream &in,  Sudoku &c);
    
    int factorial(int a);
    
};


#endif /* main_h */
