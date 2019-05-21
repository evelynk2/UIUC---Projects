//
//  classifying.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/19/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#include "classifying.hpp"
#include <fstream>
#include <sstream>
#include <math.h>

using namespace std;


void Classifying::RunClassifier() {
    ReadTestImages(1);
    ReadTestLabels(1);
    ReadModel();
    DecideClass(images[1]);
    cout << "Evaluation Matrix: " << endl;
    EvaluationMatrix();
    
}


string Classifying::ReadTestImages(int image_number) {
    
    string user_file = naivebayes.classified_data_file;
    
    if (naivebayes.classified_data_file == "") {
        
        user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/testimages";
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

int Classifying::ReadTestLabels(int label_number) {
    
    string user_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/testlabels";
    
    
    ifstream file(user_file);
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            char c = line.at(0);
            actual_labels.push_back(c - '0');
            
        }
        
        file.close();
    }
    
    return actual_labels[label_number];
}

string Classifying::ReadModel() {
    
    string default_file = naivebayes.load_model_file;
    
    if (naivebayes.load_model_file == "") {
       
        default_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model_example";
    }

    
    ifstream file(default_file);
    string temp;
    
    string line;
    
    if (file.is_open()) {
        
        while (getline(file, line))
        {
            
            temp += line + "\n";
            probabilities.push_back(temp);
            temp.clear();
         
        }
        
        file.close();
    }
    
    return default_file;
}


// log(P (class)) + log(P (f1,1|class)) + log(P (f1,2|class)) + ... + log(P (f28,28|class))
double Classifying::PosteriarProbability(string image, int number) {
    
    double probability = 0.0;
    
    vector<string> specific_probabilities = GetNumberProbabilities(number);

    for (int k = 0; k < kSpotsInImage; k++) {
        
        // http://www.cplusplus.com/reference/string/stod/
        string::size_type sz = 0;
        
        int spot = k;
        
        if (image[k] == '+' || image[k] == '#') {
            spot = k + (kSpotsInImage);
        }
        
       
        stringstream ss(specific_probabilities[spot]);
        vector<string> result;
        
        while( ss.good() )
        {
            string substr;
            getline( ss, substr, ',' );
            result.push_back( substr );
        }
        
        
        if (k == 0) {
            double priors = stod(result[1].substr(sz));
            probability += log (priors);
        }
        
        
        double prob_of_color = stod(result[4].substr(sz));

        probability += log (prob_of_color);

    }
    
    return probability;
}


vector<string> Classifying::GetNumberProbabilities(int number) {
    vector<string> specific_probabilities;
    
    for (int k = number * (kSpotsInImage * 2);
         k < (number + 1) * (kSpotsInImage * 2);
         k++) {
        
        specific_probabilities.push_back(probabilities[k]);
        
    }
    
    return specific_probabilities;
}



int Classifying::DecideClass(string i) {
    
    int number = 0;
    int final_number = 0;
    
    double probability_of_number;
    double next_probability;
    
    for (string image : images) {
        probability_of_number = PosteriarProbability(image, 0);
        
        for (int num = 1; num < (kNumbers + 1); num++) {
            
            next_probability = PosteriarProbability(image, num);

            if (next_probability > probability_of_number) {

                final_number = num;
                probability_of_number = next_probability;
                
            }
            
        }
        
        if (image == i) {
            number = final_number;
        }
        
        
        predicted_labels.push_back(final_number);
    }
    
    return number;
}


vector<int> Classifying::GetPredictedLabels() {
    
    return predicted_labels;
}


double Classifying::CalculatePercentage(double r, double c) {
    // the percentage of test images from class r that are classified as class c.
    ReadTestLabels(2);
    double percent = 0.0;
    double class_r = 0.0;
    double class_c = 0.0;
    
    for (int k = 0; k < actual_labels.size(); k++) {
        
        if (actual_labels[k] == r) {
            
            class_r++;
            
            if (predicted_labels[k] == c) {
                
                class_c++;
            }
        }
        
    }
    
    
    percent = (class_c / class_r) * 100;
    
    return percent;
}

void Classifying::EvaluationMatrix() {
    
    for (int i = 0; i < (kNumbers + 1); i++) {
        for (int j = 0; j < (kNumbers + 1); j++) {
            
            matrix[i][j] = CalculatePercentage(i, j);
            
            cout << matrix[i][j] << " , ";
            if (j == kNumbers) {
                cout << "\n";
            }
        }
    }
    
}
