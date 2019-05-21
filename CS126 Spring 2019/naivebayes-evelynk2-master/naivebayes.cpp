//
//  naivebayes.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/15/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#define CATCH_CONFIG_RUNNER
#include "catch.hpp"
#include "naivebayes.h"
#include "training.hpp"
#include "classifying.hpp"

#include <string>
#include <fstream>
#include <iostream>

using namespace std;


string NaiveBayes::SaveModelFile(string user_input) {
    
    string user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model";
    
    if (user_input != "model" && user_input != "default") {
        
        user_file = user_input;
    }
    save_model_file = user_file;
    return user_file;
}

string NaiveBayes::LoadModelFromFile(string user_input) {
    
    string user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model";
    
    if (user_input != "model" && user_input != "default") {
        
        user_file = user_input;
    }
    
    ifstream file(user_file);
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            model = line + "\n";
        }
        file.close();
    }
    load_model_file = user_file;
    return user_file;
}

string NaiveBayes::InitializeClassifiedFile(string user_input) {
    
    string user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/testimages";
    
    if (user_input != "testimages" && user_input != "default") {
        
        user_file = user_input;
    }
    
    classified_data_file = user_file;
    return user_file;
    
}

string NaiveBayes::InitializeTrainingData(string labels, string images)
{
    
    string user_file_images = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/trainingimages";
    
    string user_file_labels = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/traininglabels";
    
    if (images != "trainingimages" && images != "default") {
        
        user_file_images = images;
    }
    
    if (labels != "traininglabels" && labels != "default") {
        
        user_file_labels = labels;
    }
    training_images_file = user_file_images;
    training_labels_file = user_file_labels;
    return user_file_images;
    
}

vector<int> TestLabels() {
    vector<int> testlabels;
    string user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/testlabels";
    
    
    ifstream file(user_file);
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            char c = line.at(0);
            testlabels.push_back(c - '0');
            
        }
        
        file.close();
    }
    
    return testlabels;
}

int main(int argc, const char * argv[]) {
    NaiveBayes naivebayes;

    cout << "Type a file to read training images from or type 'default'\n";
    cin >> naivebayes.training_images_file;
    
    cout << "Type a file to read training labels from or type 'default'\n";
    cin >> naivebayes.training_labels_file;
    
    naivebayes.InitializeTrainingData(naivebayes.training_labels_file, naivebayes.training_images_file);
    
    cout << "Type a file to save the training model to or type 'default'\n";
    cin >> naivebayes.save_model_file;
    
    naivebayes.SaveModelFile(naivebayes.save_model_file);
    
    cout << "Type a file to load a training model from or type 'default'\n";
    cin >> naivebayes.load_model_file;
    
    naivebayes.LoadModelFromFile(naivebayes.load_model_file);
    
    cout << "Type a file to classify images from or type 'default'\n";
    cin >> naivebayes.classified_data_file;
    
    naivebayes.InitializeClassifiedFile(naivebayes.classified_data_file);
    
    if (naivebayes.training_labels_file != "default" || naivebayes.training_images_file != "default") {

        Training training;
        training.RunTraining();
    }
    
    Classifying classifying;
    classifying.RunClassifier();
    
    
    vector<int> modellabels = classifying.GetPredictedLabels();
    vector<int> testlabels = TestLabels();
    
    double count = 0.0;
    
    for (int k = 0; k < testlabels.size(); k++) {
        if (testlabels[k] == modellabels[k]) {
            count++;
        }
    }
    
    double accuracy = (count / testlabels.size()) * 100;
    
    cout << "\nAccuracy Percentage: " << accuracy << "%" << endl;
    
    
    int result = Catch::Session().run(argc, argv);
    return result;
}
