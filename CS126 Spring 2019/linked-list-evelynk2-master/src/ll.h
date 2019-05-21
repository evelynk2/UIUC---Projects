#ifndef LL_H
#define LL_H

#include <iostream>
#include <vector>




namespace cs126linkedlist {

/*
 *  Declare any struct, class, or enum types you need to use here
 */



// Template linked list class
template<typename ElementType>
class LinkedList {
 
    
    /*
     *  Declare any struct, class, or enum types you need to use here
     **/
    

    
    
    class LinkedListNode {
        friend class LinkedList;
        
        LinkedListNode * next;
        ElementType data;

        LinkedListNode();
        
        // Initialize current node with data and point it to next
        LinkedListNode(ElementType data);
        
    };
    
    LinkedListNode *head = new LinkedListNode();
    LinkedListNode *tail = new LinkedListNode();
    
    
public:
    // Default constructor
    //should construct an empty linked list.
    LinkedList();
    
    // Initilize from vector
    // This constructor will create a linked list containing in order the elements from the value vector.
    explicit LinkedList(const std::vector<ElementType> &values);
    
    
    // BIG 5
    
    // Copy constructor
    // will create a new linked list that is a deep copy of the source.
    LinkedList(const LinkedList& source);
    
    
    // Move constructor
    // will implement a move con- structor that will make a new linked list using the already allocated elements from the source.
    LinkedList(LinkedList&& source) noexcept;
    
    // Destruct*or
    // will delete all the allocated data in the linked list class.
    ~LinkedList();
    
    
    // Copy assignment operator
    // This function is the copy assign- ment operator which should make a deep copy from the source deallocating all data from the old location. Remember to handle the case where the both sides are the same list.
    LinkedList<ElementType>& operator=(const LinkedList<ElementType>& source);
    
    
    // Move assignment operator
    // This function will copy the data from the source reusing the allocated data from the source and deleting any data that was allocated. Remember to handle the case where the both sides are the same list.
    LinkedList<ElementType>& operator=(LinkedList<ElementType>&& source) noexcept;
    
    
    // This function will add a new element to the linked list at the front of the list.
    void push_front(ElementType value);
    
    // This function will add a new element to the linked list at the back of the list.
    void push_back(ElementType value);
    
    // Access the front value
    // Return a copy of the ElementType element stored in the first node of the list. This does not remove any items from the list. If the list is empty you should crash to an assert ElementType.
    ElementType front() const;
    
    
    // Access the back value
    // Return a copy of the ElementType element stored in the last node of the list. This does not remove any items from the list. If the list is empty you should crash to an assert ElementType.
    ElementType back() const;
    
    // This will remove the front element from the linked list and delete the allocated data. If the list is empty it will do nothing.
    void pop_front();
    
    // This will remove the back element from the linked list and delete the allocated data. If the list is empty it will do nothing.
    void pop_back();
    
    // Return the number of elements in the list.
    int size() const;
    
    // This function returns true if the list is empty otherwise returns false.
    bool empty() const;
    
    // Delete all data in the linked list returning the list to the same state as the default constructor.
    void clear();
    
    // Remove all the odd elements from the list. Remember that the list is zero indexed so the first element in the list is even. This should be implemented without directly calling any functions in this class.
    void RemoveOdd();

    
    // This function will compare the list element by element returning true if they are all equal otherwise it will return false.
    bool operator==(const LinkedList<ElementType> &rhs) const;
    
    void PassString(std::string s, std::ostream& os);
    
    // iterator
    class iterator : std::iterator<std::forward_iterator_tag, ElementType> {
        LinkedListNode *current_;
    public:
        iterator() : current_(nullptr) {};
        iterator(LinkedListNode *ptr) {current_ = ptr;};
        iterator& operator++();
        ElementType& operator*() const;
        bool operator!=(const iterator& other) const;
    };
    
    // returns an iterator that points to the start of the list.
    iterator begin();
    
    // returns an iterator that points to the past the end of the list.
    iterator end();
    
    // const_iterator
    class const_iterator : std::iterator<std::forward_iterator_tag, ElementType> {
        const LinkedListNode *current_;
    public:
        const_iterator() : current_(nullptr) {};
        const_iterator(LinkedListNode *ptr) {current_ = ptr;};
        const_iterator& operator++();
        const ElementType& operator*() const;
        bool operator!=(const const_iterator& other) const;
    };
    
    // returns a const iterator that points to the start of the list.
    const_iterator begin() const;
    
    // returns a const iterator that points to the past the end of the list.
    const_iterator end() const;
};




// This should print the ElementType elements stored in the list using the « operator from the ElementType class to print the list with a comma and a space separating each element for example "3, 2" when there are elements that would print as 3 and 2 respectively.
template<typename ElementType>
std::ostream& operator<<(std::ostream& os, const LinkedList<ElementType>& list);

// needed for template instantiation
#include "ll.hpp"

} // namespace cs126linkedlist
#endif //LL_H



