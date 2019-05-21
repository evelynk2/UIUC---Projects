//
//  naivebayes_testing.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/15/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#include <stdio.h>
#include <fstream>
#include <string>
#include <vector>
#include "catch.hpp"
#include "training.hpp"
#include "classifying.hpp"

using namespace std;

NaiveBayes *naivebayes = new NaiveBayes();
Training *training = new Training();
Classifying *classifying = new Classifying();

string first_training_num;
string second_testing_num;
vector<int> testlabels;
vector<string> partial_model;

void SetFirstTrainingNum() {
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    first_training_num += "                +++++##+    \n";
    first_training_num += "        +++++######+###+    \n";
    first_training_num += "       +##########+++++     \n";
    first_training_num += "        #######+##          \n";
    first_training_num += "        +++###  ++          \n";
    first_training_num += "           +#+              \n";
    first_training_num += "           +#+              \n";
    first_training_num += "            +#+             \n";
    first_training_num += "            +##++           \n";
    first_training_num += "             +###++         \n";
    first_training_num += "              ++##++        \n";
    first_training_num += "                +##+        \n";
    first_training_num += "                 ###+       \n";
    first_training_num += "              +++###        \n";
    first_training_num += "            ++#####+        \n";
    first_training_num += "          ++######+         \n";
    first_training_num += "        ++######+           \n";
    first_training_num += "       +######+             \n";
    first_training_num += "    ++######+               \n";
    first_training_num += "    +####++                 \n";
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    first_training_num += "                            \n";
    
}

void SetSecondTestingNum() {
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";
    second_testing_num += "              +#++          \n";
    second_testing_num += "             +####+         \n";
    second_testing_num += "           ++######         \n";
    second_testing_num += "           +###+++#         \n";
    second_testing_num += "          +###+  +#+        \n";
    second_testing_num += "          +###   +##        \n";
    second_testing_num += "         +###+    ##+       \n";
    second_testing_num += "         +##+     +#+       \n";
    second_testing_num += "         ###      +#+       \n";
    second_testing_num += "        +##+      +##+      \n";
    second_testing_num += "        +##       +##+      \n";
    second_testing_num += "        +#+       +##+      \n";
    second_testing_num += "        +#+       +##       \n";
    second_testing_num += "        +#+       +#+       \n";
    second_testing_num += "        +##+     +##+       \n";
    second_testing_num += "         ###+    +##+       \n";
    second_testing_num += "         +####++###++       \n";
    second_testing_num += "         +#########         \n";
    second_testing_num += "          ++#######         \n";
    second_testing_num += "            +###+++         \n";
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";
    second_testing_num += "                            \n";

    
}

void ReadTestLabels() {
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
    
}

void ReadPartialModel() {
    string default_file = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model_example";
    
    ifstream file(default_file);
    string temp;
    
    string line;
    
    if (file.is_open()) {
        int linenum = 0;
        while (getline(file, line) && linenum < 3248)
        {
            temp += line + "\n";
            partial_model.push_back(temp);
            temp.clear();
            linenum++;
        }
        
        file.close();
    }
    
}



// ------------------------------- TRAINING TESTS-------------------------------//

TEST_CASE("reads from training images correctly") {
    SetFirstTrainingNum();
    
    REQUIRE(first_training_num == training->ReadTrainingImages(0));
}

TEST_CASE("reads from training labels correctly") {
    
    REQUIRE(4 == training->ReadTrainingLabels(2));
}

TEST_CASE("matches image to number correctly") {
  
    REQUIRE(first_training_num == training->MatchImageToNumber(5));
}


TEST_CASE("probability of priors") {
    
    CHECK(0.0986 == training->ProbabilityOfPriors(3));
    CHECK(0.099 == training->ProbabilityOfPriors(9));
    
}


// ------------------------------- CLASSIFYING TESTS---------------------------//


TEST_CASE("reads from test images correctly") {
    SetSecondTestingNum();
    REQUIRE(second_testing_num == classifying->ReadTestImages(1));
}

TEST_CASE("reads from test labels correctly") {
    
    REQUIRE(9 == classifying->ReadTestLabels(5));
}

TEST_CASE("reads from correct model file") {
    string fullpath = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model_example";
    
    REQUIRE(fullpath == classifying->ReadModel());
}


TEST_CASE("gets specific probabilities vector") {
    ReadPartialModel();
    vector<string> class_one;
    for (int k = 1624; k < partial_model.size(); k++) {
        class_one.push_back(partial_model[k]);
    }
    
    REQUIRE(class_one == classifying->GetNumberProbabilities(1));
}



// ------------------------------- NAIVE BAYES TESTS----------------------------//


TEST_CASE("save model file") {
    string fullpath = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model";
    
    REQUIRE(fullpath == naivebayes->SaveModelFile("default"));
}

TEST_CASE("load model file") {
    string fullpath = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/model";
    
    REQUIRE(fullpath == naivebayes->LoadModelFromFile("default"));
}

TEST_CASE("initialize file to classify images") {
    string fullpath = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/testimages";

    REQUIRE(fullpath == naivebayes->InitializeClassifiedFile("default"));
}

TEST_CASE("initialize training data file") {
    string fullpath = "/Users/evelynkrasnik/Documents/naivebayes-evelynk2/naivebayes/naivebayes/digitdata/trainingimages";
    
    REQUIRE(fullpath == naivebayes->InitializeTrainingData("default", "default"));
}

