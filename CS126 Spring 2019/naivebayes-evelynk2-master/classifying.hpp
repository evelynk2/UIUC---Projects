//
//  classifying.hpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/19/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#ifndef classifying_hpp
#define classifying_hpp

#include <stdio.h>
#include <string>
#include <vector>
#include <iostream>

#include "naivebayes.h"


using namespace std;


class Classifying {
    
    friend class NaiveBayes;
    NaiveBayes naivebayes;
    
private:
    const int kSizeOfImage = 28;
    const int kSpotsInImage = kSizeOfImage * (kSizeOfImage + 1);
    const int kNumbers = 9;
    string model;
    vector<string> probabilities;
    vector<string> images;
    vector<int> actual_labels;
    vector<int> predicted_labels;



public:
    int matrix[10][10];

    void RunClassifier();
    string ReadTestImages(int image_number);
    int ReadTestLabels(int label_number);
    string ReadModel();
    double PosteriarProbability(string image, int number);
    vector<string> GetNumberProbabilities(int number);
    int DecideClass(string image);
    vector<int> GetPredictedLabels();
    
//    This is a 10 × 10 matrix whose entry in row r and column c is the percentage of test images from class r that are classified as class c.
    void EvaluationMatrix();
    double CalculatePercentage(double r, double c);
};


#endif /* classifying_hpp */
