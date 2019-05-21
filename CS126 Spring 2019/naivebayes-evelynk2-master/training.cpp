//
//  training.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/19/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#include "training.hpp"
#include <vector>
#include <fstream>
#include <iostream>
#include <map>
#include <utility>

using namespace std;

void Training::RunTraining() {
    ReadTrainingImages(1);
    ReadTrainingLabels(1);
    SaveModel();
}

string Training::ReadTrainingImages(int image_number) {
    

    string user_file = naivebayes.training_images_file;
    
    if (naivebayes.training_images_file == "") {
        
        user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/trainingimages";
    }
    
    

    
    ifstream file(user_file);
    string temp;
    
    string line;
    
    if (file.is_open()) {
        
        int linenum = 1;
        
        while (getline(file, line))
        {
            
            temp += line + "\n";

            
            if (linenum % kSizeOfImage == 0) {
                
                images.push_back(temp);
                temp.clear();
            }
            
            
            linenum++;
        }
        
        file.close();
    }
    
    return images[image_number];
}

int Training::ReadTrainingLabels(int label_number) {
    
    
    string user_file = naivebayes.training_labels_file;

    if (naivebayes.training_labels_file == "") {
        
        user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/traininglabels";
    }
    
    
    ifstream file(user_file);
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            char c = line.at(0);
            labels.push_back(c - '0');
         
        }
        
        file.close();
    }
    
    return labels[label_number];
}


string Training::MatchImageToNumber(int label_number) {
    
    vector<string> new_images;
    string image;
    
    for (int k = 0; k < images.size(); k++) {
        
        if (labels[k] == label_number && image == "") {
            
            image = images[k];
        }

        images_map.insert(make_pair<Images, int>(Images(new_images, images[k]), (int) labels[k]) );
        
    }
    
    return image;
    
}


double Training::ProbabilityOfColor(int color, int number, int spot_in_image) {

    string image;
    double count = kLaplace;
    double training_examples = 0;
    
    
    map<Images, int>::iterator it = images_map.begin();
    
    for(; it != images_map.end(); it++) {
        
        if (it->second == number) {
            
            image = it->first.GetImage();
            char c = image[spot_in_image];
                       
            if (color == 0 && c == ' ') {
                
                count++;
                
            } else if ((color == 1 && (c == '+' || c == '#'))) {
                
                count++;
            }
            
           
            training_examples++;
        }
        
    }
    
    return (kLaplace + count) / ((2 * kLaplace) + training_examples);
}


double Training::ProbabilityOfPriors(int number) {
    double priors = 0;
    
    for (int k = 0; k < images.size(); k++) {
        
        if (ReadTrainingLabels(k) == number) {

            priors++;
            
        }
        
    }
    
    return (priors / images.size());
}

void Training::SaveModel() {

    string model_file = naivebayes.save_model_file;
    
    if (naivebayes.save_model_file == "") {
        
        model_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model_example";
    }
    
    
    
    ofstream file;
    file.open(model_file);
    
    MatchImageToNumber(5);
    
    if (file.is_open()) {
        
        for (int i = 0; i < (kNumbers + 1); i++) {
            
            double prob_of_priors = ProbabilityOfPriors(i);
                        
            
            // white
            for (int j = 0; j < kSizeOfImage * (kSizeOfImage + 1); j++) {
                
                file << i << " , " << prob_of_priors << " , ";
                
                file << j << " , " << "0" << " , ";
                
                file << ProbabilityOfColor(0, i, j) << endl;
                
            }
            
            // black
            for (int j = 0; j < kSizeOfImage * (kSizeOfImage + 1); j++) {
                
                file << i << " , " << prob_of_priors << " , ";
                
                file << j << " , " << "1" << " , ";
                
                file << ProbabilityOfColor(1, i, j) << endl;          }
            
        }
        
        file.close();
        
    }
}


