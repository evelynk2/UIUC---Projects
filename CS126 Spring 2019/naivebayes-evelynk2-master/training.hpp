//
//  training.hpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/19/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#ifndef training_hpp
#define training_hpp

#include <stdio.h>
#include <string>
#include <vector>
#include <map>

#include "naivebayes.h"
#include "images.cpp"

using namespace std;


class Training {
    
    friend class NaiveBayes;
    NaiveBayes naivebayes;
    
private:
    const int kSizeOfImage = 28;
    const int kNumbers = 9;
    const double kLaplace = 10;
    vector<string> images;
    vector<int> labels;
    
    // (vector<string> images, image), key
    map<Images, int> images_map;
    
    
public:
    void RunTraining();
    string ReadTrainingImages(int image_number);
    int ReadTrainingLabels(int label_number);
    string MatchImageToNumber(int label_number);

    // color = 0 for white, 1 for grey/black
    double ProbabilityOfColor(int color, int number, int spot_in_image);
    double ProbabilityOfPriors(int number);
    
    // number, priors, spot in image, white or black, prob of white or black
    void SaveModel();

};




#endif /* training_hpp */
