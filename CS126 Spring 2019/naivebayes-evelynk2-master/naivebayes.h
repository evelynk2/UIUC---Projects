//
//  naivebayes.h
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/15/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#ifndef naivebayes_h
#define naivebayes_h

#include <string>

using namespace std;


class NaiveBayes {

    friend class Training;
    friend class Classifying;
    
private:
    string model;
    
    
public:
    string training_images_file;
    string training_labels_file;
    string save_model_file;
    string load_model_file;
    string classified_data_file;
    
    string InitializeTrainingData(string labels, string images);
    string SaveModelFile(string user_input);
    string LoadModelFromFile(string user_input);
    string InitializeClassifiedFile(string user_input);
    
};

#endif /* naivebayes_h */
