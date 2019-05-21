//
//  main.cpp
//  linked-list-evelynk2
//
//  Created by Evelyn Krasnik on 3/30/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#define CATCH_CONFIG_RUNNER

#include <stdio.h>
#include <iostream>
#include "src/ll.h"
#include "test/lltests.cpp"
#include "catch.hpp"

int main(int argc, const char * argv[]) {
    
    LinkedList<int> llint = LinkedList<int>();
    llint.push_back(4);
    llint.push_back(19);
    llint.push_back(2);
    
    LinkedList<std::string> llstring = LinkedList<std::string>();
    llstring.push_back("hello");
    llstring.push_back("cs");
    llstring.push_back("code review");
    
    
    // insert code here...
    std::cout << llint;
    std::cout << llstring;
    int result = Catch::Session().run(argc, argv);
    return result;
}

