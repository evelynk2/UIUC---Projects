//
//  Sudoku.cpp
//  Sudoku
//
//  Created by Evelyn Krasnik on 3/6/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#define CATCH_CONFIG_RUNNER
#include "catch.hpp"

#include <iostream>
#include <fstream>
#include "sudoku.h"

#include <string>
#include <vector>


using namespace std;
vector<int> public_board;


string Sudoku::InitializePuzzleFile()
{
    
    string user_file = "/Users/evelynkrasnik/Documents/sudoku-evelynk2/Sudoku/Sudoku/sudokufile.spf";
    
    if (file_user_input_ != "sudokufile.spf") {
        
        std::ifstream file(file_user_input_);
        std::string temp;
        
        file.open(file_user_input_);
        if (file.is_open()) {
            
            std::getline(file, temp);
            

            if (temp == "#spf1.0") {

                user_file = file_user_input_;

            }
            file.close();
        }
    }
    
    ifstream file(user_file);
    string temp;
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            temp = line + "\n";
        }
        file.close();
    }
    
    string puzzle = temp.substr(0, 81);
    
    InitializeSudokuBoard(puzzle);

    return user_file;
    
}


vector<int> Sudoku::InitializeSudokuBoard(string puzzle) {
    int spot = 0;
    vector<int> board;
    
    while (spot < kSizeOfBoard) {
        
        if (puzzle.at(spot) == '_') {
            
            board.push_back(0);
            
        } else {
            
            board.push_back(puzzle.at(spot) - 48);
        }
        
        spot++;
    }

    public_board = board;
    return board;
}

bool Sudoku::InitializeSolutionFile(string userinput) {
    
    solution_user_input_ = userinput;

    if (solution_user_input_ == "console") {
        
        solutions_to_file_ = false;
        return false;
        
    } else {
        
        ofstream solutionfile;
        solutionfile.open(solution_user_input_);
        
        if (solutionfile.is_open()) {
            
            solutions_to_file_ = true;
            return true;
        }
        
        return false;
    }
   
    
}


void Sudoku::RunPuzzle() {
    
    InitializePuzzleFile();
    InitializeSolutionFile(solution_user_input_);
}



bool Sudoku::RecursiveSolve(vector<int> &vect, int spot, int endspot) {
    

    if (IsSolved(vect) && (spot + 1) == endspot) {
        
        solution_ = Reformat(vect);
        return true;
    }
    

    spot = FindZeros(vect, spot, kSizeOfBoard);
    

    
    if (spot != -1 && spot < kSizeOfBoard) {
        
        solution_ = Reformat(vect);
        
        for (int k = 1; k <= 9; k++)
        {
            
            if (CanSolve(vect, k, spot)) {

                vect[spot] = k;
                
                if (RecursiveSolve(vect, spot, endspot)) {
                    
                    solution_ = Reformat(vect);
                    return true;
                    
                }
                    
                vect[spot] = 0;
                
            }
 
        }
        
    }
    
    
    return false;
    
}

int Sudoku::FindZeros(vector<int> vect, int spot, int endspot) {

    for (int k = spot; k <= endspot; k++) {
        
        if (vect[k] == 0) {
            
            return (k);
        }
    }
    return -1;
}

bool Sudoku::IsSolved(vector<int> vect) {
    
    for (int k = 0; k < kSizeOfBoard; k++) {
        
        if (vect[k] == 0) {
            
            return false;
        }
    }
    
    return true;
}



bool Sudoku::CanSolve(vector<int> vect, int new_number, int spot)
{
    
    if (vect[spot] != 0) {
        return false;
    }
    
    spot++;
    
    // check horizontal
    if (CheckRow(vect, new_number, GetRow(spot))) {
        return false;
    }
    
    // check vertical
    if (CheckColumn(vect, new_number, GetColumn(spot))) {
        return false;
    }
    
    
    // checks box
    if (CheckBox(vect, new_number, GetBox(spot))) {
        return false;
    }
    
    
    return true;
}

bool Sudoku::CheckRow(vector<int> vect, int new_number, int row) {
    
    row--;
    for (int k = (row * (kSizeOfRow + 1)) - row; k < ((row * (kSizeOfRow + 1)) - row) + kSizeOfRow; k++) {
        
        if (new_number == vect[k]) {
            
            return true;
        }
    }
    
    return false;
}




bool Sudoku::CheckColumn(vector<int> vect, int new_number, int column) {
    
    column--;
    
    for (int k = column; k <= kSizeOfBoard - (kSizeOfRow - column); k += kSizeOfRow) {
        
        if (new_number == vect[k]) {
            return true;
        }
        
    }
    
    return false;
}

int Sudoku::GetRow(int spot) {
    spot--;
    int row = (spot / kSizeOfRow) + 1;
    return row;
}

int Sudoku::GetColumn(int spot) {
    int column;
    
    if (spot % kSizeOfRow == 0) {
        
        column = kSizeOfRow;
    } else {
        
        column = spot % kSizeOfRow;
    }
    
    return column;
}

int Sudoku::GetBox(int spot) {
    
    for (int i = 1; i <= kSizeOfBox; i++) {
        
        for (int j = 1; j <= kSizeOfBox; j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j) ||
                (GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 1;
            }
        }
        
    }
    
    
    
    for (int i = kSizeOfBox + 1; i <= kSizeOfBox * 2; i++) {
        
        for (int j = kSizeOfBox + 1; j <= kSizeOfBox * 2; j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j) ||
                (GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 5;
            }
        }
        
    }
    
    for (int i = (kSizeOfBox * 2) + 1; i <= kSizeOfRow; i++) {
        
        for (int j = (kSizeOfBox * 2) + 1; j <= kSizeOfRow; j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j) ||
                (GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 9;
            }
        }
        
    }
    
    for (int i = 1; i <= kSizeOfBox; i++) {
        
        for (int j = (kSizeOfBox + 1); j <= (kSizeOfBox * 2); j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j)) {
                
                return 2;
                
            } else if ((GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 4;
            }
        }
        
    }
    
    for (int i = 1; i <= kSizeOfBox; i++) {
        
        for (int j = (kSizeOfBox * 2) + 1; j <= kSizeOfRow; j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j)) {
                
                return 3;
                
            } else if ((GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 7;
            }
        }
        
    }
    
    for (int i = (kSizeOfBox + 1); i <= (kSizeOfBox * 2); i++) {
        
        for (int j = (kSizeOfBox * 2) + 1; j <= kSizeOfRow; j++) {
            
            if ((GetRow(spot) == i && GetColumn(spot) == j)) {
                
                return 6;
                
            } else if ((GetRow(spot) == j && GetColumn(spot) == i)) {
                
                return 8;
            }
        }
        
    }

    // should never return
    return 0;
}

bool Sudoku::CheckBox(vector<int> vect, int new_number, int box) {
    
    int i;
    
    if (box <= kSizeOfBox) {
        
        i = 0;
        while (i < kSizeOfBox) {
            
            for (int k = (i * kSizeOfRow) + (box - 1) * kSizeOfBox; k < ((i * kSizeOfRow) + (box - 1) * (kSizeOfBox)) + kSizeOfBox; k++) {
                
                if (new_number == vect[k]) {

                    return true;
                }
                
            }
            
            i++;
        }
        
    } else if (box == 4 || box == 7) {
        
        i = 0;
        
        while (i < kSizeOfBox) {
            
            for (int k = (i * kSizeOfRow) + (box - 1) * (kSizeOfRow); k < ((i * kSizeOfRow) + (box - 1) * (kSizeOfRow)) + kSizeOfBox; k++) {
                
                if (new_number == vect[k]) {
                    
                    return true;
                }
                
            }
            
            i++;
        }
        
    } else if (box == 5) {
        
        i = 0;
        
        while (i < kSizeOfBox) {
            
            for (int k = ((i * kSizeOfRow) + (box - 1) * (kSizeOfRow)) + kSizeOfBox; k < ((i * kSizeOfRow) + (box - 1) * (kSizeOfRow)) + (kSizeOfBox * 2); k++) {
                
                if (new_number == vect[k]) {

                    return true;
                }
                
            }
            
            i++;
        }
        
    } else if (box == 6) {
        
        i = 0;
        
        while (i < kSizeOfBox) {
            
            for (int k = ((i * kSizeOfRow) + (box - 1) * (kSizeOfBox * 2)) + kSizeOfBox; k < ((i * kSizeOfRow) + (box - 1) * (kSizeOfBox * 2)) + (kSizeOfBox * 2); k++) {

                if (new_number == vect[k]) {
                    
                    return true;
                }
                
            }
            
            i++;
        }
        
    } else if (box == 8) {
        
        i = 0;
        
        while (i < kSizeOfBox) {
            
            for (int k = ((i * kSizeOfRow) + (box - 1) * (kSizeOfRow)) - (kSizeOfBox * 2); k < ((i * kSizeOfRow) + (box - 1) * (kSizeOfRow)) - kSizeOfBox; k++) {
                
                if (new_number == vect[k]) {
                    
                    return true;
                }
                
            }
            
            i++;
        }
        
    } else if (box == 9) {
        
        i = 0;
        
        while (i < kSizeOfBox) {
            
            for (int k = ((i * kSizeOfRow) + (box) * (kSizeOfBox * 2)) + (kSizeOfBox * 2); k < ((i * kSizeOfRow) + (box) * (kSizeOfBox * 2)) + kSizeOfRow; k++) {
                
                if (new_number == vect[k]) {
                    
                    return true;
                }
                
            }
            
            i++;
        }
    }
    
    return false;
}



string Sudoku::Reformat(vector<int> vect) {
    string board;
    
    for (int k = 0; k < vect.size(); k++)
    {
        if (vect[k] == 0) {
            
            board += '_';
            
        } else {
            board += vect[k] + 48;
        }
        
    }
    
    solution_ = board;
    return solution_;
}


ostream & operator << (ostream &out, const Sudoku &c)
{
    if (c.solutions_to_file_ == false) {
        
        for (int k = 1; k <= c.kSizeOfBoard; k++) {
            
            out << " {" << c.solution_.at(k - 1) << "} ";

            if (k % c.kSizeOfRow == 0) {
                
                out << "\n";
                
            }
        }

    } else {
        
        ofstream solutionfile;
        
        solutionfile.open(c.solution_user_input_);
        
        if (solutionfile.is_open()) {
            
            solutionfile<<c.solution_;
            
            out<< "Writing the solutions to file '" << c.solution_user_input_ << "'\n";
            
            solutionfile.close();
            
        }
    }
    return out;
}

istream & operator >> (istream &in,  Sudoku &c)
{
    string choose_puzzle = "Choose a file from which to get the Sudoku puzzle\n";
    c.PassString(choose_puzzle);
  
    in >> c.file_user_input_;
    
    string choose_solution = "\nChoose a file to print solutions to or enter console\n";
    c.PassString(choose_solution);
   
    in >> c.solution_user_input_;
    
    return in;
}


void Sudoku::PassString(string s, std::ostream& os) {
    
    os << s;
}


int main(int argc, char* argv[]) {

    int result = Catch::Session().run(argc, argv);

    Sudoku sudoku;
    cin >> sudoku;
    

    sudoku.PassString("\nThe Sudoku puzzle will be retrieved from '" + sudoku.InitializePuzzleFile() + "'\n");
    
    sudoku.RunPuzzle();

    string s;
  
    if (sudoku.RecursiveSolve(public_board, 0, 81)) {
        s = "\nThe solutions are...\n";
        sudoku.PassString(s);
        
        cout << sudoku;

    } else {
        s = "This puzzle is unsolvable.";
        sudoku.PassString(s);
        
    }
    

    return result;
}




