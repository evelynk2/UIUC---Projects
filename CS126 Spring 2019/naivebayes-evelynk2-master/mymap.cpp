//
//  MultiMap.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/20/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#include "mymap.h"
#include <iostream>

using namespace std;


MyMap::MyMap() {
    
}

MyMap::MyMap(int k, string image) {
    
    key = k;
    AddImageToKey(key, image);
    
}

void MyMap::AddImageToKey(int key, string i) {
    
    GetVector(key);
    current_board.push_back(i);

}


void MyMap::GetVector(int key) {
    
    if (key == 0) {
        
        current_board = zero_images;
        
    } else if (key == 1) {
        
        current_board = one_images;
        
    } else if (key == 2) {
        
        current_board = two_images;
        
    } else if (key == 3) {
        
        current_board = three_images;
        
    } else if (key == 4) {
        
        current_board = four_images;
        
    } else if (key == 5) {
        
        current_board = five_images;
        
    } else if (key == 6) {
        
        current_board = six_images;
        
    } else if (key == 7) {
        
        current_board = seven_images;
        
    } else if (key == 8) {
        
        current_board = eight_images;
        
    } else {
        
        current_board = nine_images;
        
    }
    
    
}


int MyMap::GetKey() {
    
    return key;
    
}


string MyMap::GetImage(int key, int spot) {
    cout << "yes" << key << spot << endl;

    GetVector(key);
    
    if (current_board.at(spot) != "") {
        cout << current_board.at(spot);
        
        return current_board.at(spot);
        
    } else {
        
        return "nah";
    }
    
}
