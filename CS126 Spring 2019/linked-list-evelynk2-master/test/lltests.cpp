//
//  lltests.cpp
//  linked-list-evelynk2
//
//  Created by Evelyn Krasnik on 3/30/19.
//  Copyright © 2019 Evelyn Krasnik. All rights reserved.
//

#include <stdio.h>
#include <vector>
#include <sstream>
#include <iostream>
#include "catch.hpp"

using namespace cs126linkedlist;

// LL initialized from a vector of int values
std::vector<int> llvector {4, 1, 5, 2, 9, 19};
LinkedList<int> *linkedlist_vector = new LinkedList<int>(llvector);

// LL initialized to empty
LinkedList<char> *linkedlist_chars = new LinkedList<char>();



TEST_CASE("outputs correct size") {
    
    REQUIRE(6 == linkedlist_vector->size());
}


TEST_CASE("default constructor") {
    
    CHECK(6 == linkedlist_vector->size());
    CHECK(0 == linkedlist_chars->size());
    
}

TEST_CASE("deep copy constructor") {
    LinkedList<int> deep_copy;
    
    // 3, 10
    deep_copy.push_back(3);
    deep_copy.push_back(10);
    
    LinkedList<int> deep_copy2(deep_copy);
    
    // check size
    CHECK(2 == deep_copy.size());
    CHECK(2 == deep_copy.size());
    
    // check to ensure they have correct elements
    CHECK(3 == deep_copy.front());
    CHECK(10 == deep_copy.back());
    
}

TEST_CASE("deep copy assigment operator") {
    LinkedList<int> deep_copy = LinkedList<int>(llvector);
    LinkedList<int> deep_copy2;

    // check size
    CHECK(0 == deep_copy2.size());
    CHECK(6 == deep_copy.size());

    deep_copy2 = deep_copy;

    // check to ensure they have correct size and elements
    CHECK(6 == deep_copy2.size());
    CHECK(4 == deep_copy2.front());
}



TEST_CASE("move constructor") {
    LinkedList<int> move_constructor;
    
    // 1, 9, 24
    move_constructor.push_back(9);
    move_constructor.push_back(24);
    move_constructor.push_front(1);
    
    LinkedList<int> move_constructor2(std::move(move_constructor));
    
    // checks to ensure correct elements
    CHECK(1 == move_constructor2.front());
    CHECK(24 == move_constructor2.back());
    
    // checks correct size
    CHECK(3 == move_constructor2.size());
    CHECK(0 == move_constructor.size());

    
    std::cout << move_constructor2;
}

TEST_CASE("move assignemnt operator") {
    LinkedList<int> move_operator;
    
    // 11, 6, 28
    move_operator.push_back(6);
    move_operator.push_back(28);
    move_operator.push_front(11);
    
    LinkedList<int> move_operator2;

    // checks correct size
    CHECK(3 == move_operator.size());
    CHECK(0 == move_operator2.size());

    move_operator2 = std::move(move_operator);

    // checks correct size after move
    CHECK(3 == move_operator2.size());
    CHECK(0 == move_operator.size());
    
    // checks correct elements
    CHECK(11 == move_operator2.front());
    CHECK(28 == move_operator2.back());

    std::cout<< move_operator2;
    
}


TEST_CASE("is empty") {
    
    CHECK(true == linkedlist_chars->empty());
    
    SECTION("push an element front and access it") {
    
        linkedlist_chars->push_front('c');
        CHECK('c' == linkedlist_chars->front());
    }
}


TEST_CASE("is not empty") {
    
    CHECK(false == linkedlist_chars->empty());
    
    SECTION("push an element back and access back") {
        
        linkedlist_chars->push_back('d');
        CHECK('d' == linkedlist_chars->back());
    }
}


TEST_CASE("vector linkedlist") {
    
    SECTION("push element to front and access front") {
        
        linkedlist_vector->push_front(8);
        REQUIRE(8 == linkedlist_vector->front());
    }
    
    SECTION("push element to back and access back") {
        
        linkedlist_vector->push_back(2);
        REQUIRE(2 == linkedlist_vector->back());
    }
    
}

TEST_CASE("removes elements correctly") {
    
    
    SECTION("pop back") {
        
        CHECK(2 == linkedlist_vector->back());
        linkedlist_vector->pop_back();
        CHECK(19 == linkedlist_vector->back());
    }
    
    SECTION("pop front") {
        
        CHECK(8 == linkedlist_vector->front());
        linkedlist_vector->pop_front();
        CHECK(4 == linkedlist_vector->front());
    }

    
    SECTION("removes odd elements only") {
        
        LinkedList<int> llvect = LinkedList<int>(llvector);
        llvect.push_back(13);
        
        // {4, 1, 5, 2, 9, 19, 13}
        // {4, 5, 9, 13}
        CHECK(7 == llvect.size());
        
        llvect.RemoveOdd();
        
        CHECK(4 == llvect.size());
        CHECK(4 == llvect.front());
        CHECK(13 == llvect.back());
        
        llvect.pop_back();
        llvect.pop_back();
        CHECK(5 == llvect.back());
        
    }
    
    
}



TEST_CASE("clear and destructor work") {
    
    linkedlist_vector->~LinkedList();
    REQUIRE(0 == linkedlist_vector->size());
    
}


TEST_CASE("not equal linked lists") {
    
    std::vector<int> llvector {2, 1, 5, 4};
    LinkedList<int> llint = LinkedList<int>(llvector);
    LinkedList<int> llint2 = LinkedList<int>(llvector);

    bool equal = false;
    
    
    SECTION("one list has an extra element") {
        
        llint2.push_back(3);
        
        if (llint == llint2) { equal = true; }
        REQUIRE(equal == false);
    }
    
    SECTION("move operator on one of the lists") {
        
        llint2 = std::move(llint);
        
        if (llint == llint2) { equal = true; }
        REQUIRE(equal == false);
    }
    
    
}



TEST_CASE("equal linked lists") {
    
    std::vector<std::string> stringvector {"hello", "bye", "ok"};
    bool equal_copies = false;
    bool equal_separate = false;
    
    SECTION("copies of each other") {
        
        LinkedList<std::string> *llstring = new LinkedList<std::string>(stringvector);
        LinkedList<std::string> *llstring2 = llstring;
        
        if (llstring == llstring2) { equal_copies = true; }
        
        CHECK(equal_copies == true);
        
    }
    
    SECTION("not copies of each other") {
        
        LinkedList<std::string> llstring = LinkedList<std::string>(stringvector);
        LinkedList<std::string> llstring2 = LinkedList<std::string>(stringvector);
        
        if (llstring == llstring2) { equal_separate = true; }
        
        CHECK(equal_separate == true);
    }
    
}





