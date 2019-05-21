//
//  images.cpp
//  naivebayes
//
//  Created by Evelyn Krasnik on 3/20/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//


#include <stdio.h>
#include <string>
#include <vector>

using namespace std;


// https://thispointer.com/stdmap-tutorial-part-3-using-user-defined-class-objects-as-key-in-stdmap/

class Images
{
    vector<string> all_images;
    string image;
    
public:
    Images(vector<string> all, string i)
    :all_images(all), image(i)
    {}
    const vector<string>& GetVector() const {
        return all_images;
    }
    const string& GetImage() const {
        return image;
    }
    bool operator< (const Images& userObj) const
    {
        if(userObj.image < this->image) {
            return true;
        }
        return false;
    }
};

