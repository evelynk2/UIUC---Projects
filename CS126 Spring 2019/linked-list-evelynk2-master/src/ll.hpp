#include <utility>
#include <cassert>
#include <vector>
#include <iostream>
#include "ll.h"

using namespace cs126linkedlist;


template<typename ElementType>
LinkedList<ElementType>::LinkedListNode::LinkedListNode() {
    
    next = nullptr;
}


template<typename ElementType>
LinkedList<ElementType>::LinkedListNode::LinkedListNode(ElementType d) {

    data = d;
    next = nullptr;

}


template<typename ElementType>
LinkedList<ElementType>::LinkedList() {
    
    head = nullptr;
    tail = nullptr;

}

template<typename ElementType>
LinkedList<ElementType>::LinkedList(const std::vector<ElementType> &values) {
    
    head = nullptr;
    tail = nullptr;

    for (ElementType e : values) {

        push_back(e);
    }

}

// Copy constructor
template<typename ElementType>
LinkedList<ElementType>::LinkedList(const LinkedList<ElementType>& source) {
 
    LinkedListNode *curr = source.head;

    if (head == nullptr && curr != nullptr) {
        
        head = new LinkedListNode(curr->data);
        tail = head;
        curr = curr->next;

    }

    while (curr != nullptr) {

        LinkedListNode *newNode = new LinkedListNode(curr->data);
        tail->next = newNode;
        tail = newNode;
        curr = curr->next;
    }

}

// Move constructor
template<typename ElementType>
LinkedList<ElementType>::LinkedList(LinkedList<ElementType>&& source) noexcept {
    
    
    head = source.head;
    source.head = nullptr;
    
    tail = source.tail;
    source.tail = nullptr;
}

// Destructor
template<typename ElementType>
LinkedList<ElementType>::~LinkedList() {

    clear();

}

// Copy assignment operator
template<typename ElementType>
LinkedList<ElementType>& LinkedList<ElementType>::operator= (const LinkedList<ElementType>& source) {
    
    if (&source != this) {
        
        LinkedListNode *curr = source.head;
        
        if (head == nullptr && curr != nullptr) {
            
            head = new LinkedListNode(curr->data);
            tail = head;
            curr = curr->next;
            
        }
        
        while (curr != nullptr) {
            
            LinkedListNode *newNode = new LinkedListNode(curr->data);
            tail->next = newNode;
            tail = newNode;
            curr = curr->next;
        }

    }
    
    return *this;
}

// Move assignment operator
template<typename ElementType>
LinkedList<ElementType>& LinkedList<ElementType>::operator= (LinkedList<ElementType>&& source) noexcept {
    
    if (&source != this) {
        
        head = source.head;
        source.head = nullptr;
        
        tail = source.tail;
        source.tail = nullptr;
    }
    
    
    return *this;
}

template<typename ElementType>
void LinkedList<ElementType>::push_front(ElementType value) {

    LinkedListNode *new_head = new LinkedListNode(value);
    new_head->next = head;
    head = new_head;
    
    if (empty()) {
        
        tail = new_head;
    }
}

template<typename ElementType>
void LinkedList<ElementType>::push_back(ElementType value) {
    
    LinkedListNode *new_tail = new LinkedListNode(value);

    if (tail == nullptr) {

        new_tail->next = tail;
        tail = new_tail;
        head = new_tail;
        return;

    } else {

        tail->next = new_tail;
        tail = tail->next;
    }
}

template<typename ElementType>
ElementType LinkedList<ElementType>::front() const{

    assert(!empty());
    return head->data;
    
}

template<typename ElementType>
ElementType LinkedList<ElementType>::back() const {

    assert(!empty());
    return tail->data;
}


template<typename ElementType>
void LinkedList<ElementType>::pop_front() {

    if (empty()) {
        
        return;
        
    } else {
        
        LinkedListNode *temp = head;
        head = head->next;
        delete temp;
    }
    
}

template<typename ElementType>
void LinkedList<ElementType>::pop_back() {

    if (empty()) {
        
        return;
        
    } else {
        
        LinkedListNode *pre = head;
        LinkedListNode *temp = head->next;

        while (temp->next != nullptr) {

            pre = pre->next;
            temp = temp->next;

        }

        pre->next = nullptr;
        delete temp;
        tail = pre;
    }
    
}

template<typename ElementType>
int LinkedList<ElementType>::size() const {

    if (empty()) {
        return 0;
    }
    
    int count = 0;
    LinkedListNode *temp = head;
    
    while (temp != nullptr) {
        
        count++;
        temp = temp->next;

    }
    
    return count;
}

template<typename ElementType>
bool LinkedList<ElementType>::empty() const {

    if (head == nullptr && tail == nullptr) {
        return true;
    }
    
    return false;
    
}

template<typename ElementType>
void LinkedList<ElementType>::clear() {

    
    LinkedListNode* temp = head;
    
    while(temp != nullptr)
    {
        temp = temp->next;
        head = temp;
        delete temp;
        
    }
    
   
}


template<typename ElementType>
std::ostream& operator<<(std::ostream& os, const LinkedList<ElementType>& list) {
    
    ElementType et;
    
    typename LinkedList<ElementType>::const_iterator ptr;
    
    for (ptr = list.begin(); ptr != list.end(); ++ptr) {
        
        et = *ptr;
        os << et;
        
        if (et != list.back()) {
            
            os << ", ";
            
        } else {
            
            os << std::endl;
        }
    }
    return os;
}



template<typename ElementType>
void LinkedList<ElementType>::RemoveOdd() {

    if (empty()) {
        
        return;
        
    } else {
        // {4, 1, 5, 2, 9, 19}
        // {4, 5, 9}
       
        LinkedListNode *curr = head;
        LinkedListNode *temp = head->next;
        
        while (curr != nullptr && temp != nullptr)
        {
            curr->next = temp->next;
            delete temp;
            curr = curr->next;
            
            if (curr != nullptr) {
                
                temp = curr->next;

            }
        }
    }

}

template<typename ElementType>
bool LinkedList<ElementType>::operator==(const LinkedList<ElementType> &rhs) const {
    
    if (this->size() != rhs.size()) {
        
        return false;
    }
    
    LinkedListNode *thishead = head;
    LinkedListNode *rhshead = rhs.head;
    
    while (thishead->next != NULL || rhshead->next != NULL) {
        
        if (thishead->data == rhshead->data) {
            
            thishead = thishead->next;
            rhshead = rhshead->next;
            
        } else {
            
            return false;
        }
        
    }
    
    
    if ((thishead->next == nullptr && rhshead->next == nullptr) ||
        (thishead->next != nullptr && rhshead->next != nullptr)) {
        
        return true;
        
    } else {
        
        return false;
    }
    
    
}
// This function will compare the list element by element returning false if they are all equal otherwise it will return true.
template<typename ElementType>
bool operator!=(const LinkedList<ElementType>& lhs, const LinkedList<ElementType> &rhs) {
    
    return !(lhs == rhs);
    
}

template<typename ElementType>
typename LinkedList<ElementType>::iterator& LinkedList<ElementType>::iterator::operator++() {

    if (current_ != nullptr) {
        current_ = current_->next;
    }
    
    return *this;
}

template<typename ElementType>
ElementType& LinkedList<ElementType>::iterator::operator*() const {

    return current_->data;

    
}

template<typename ElementType>
bool LinkedList<ElementType>::iterator::operator!=(const LinkedList<ElementType>::iterator& other) const {

    return current_ != other.current_;
    
}

template<typename ElementType>
typename LinkedList<ElementType>::iterator LinkedList<ElementType>::begin() {
    
    return iterator(head);

}

template<typename ElementType>
typename LinkedList<ElementType>::iterator LinkedList<ElementType>::end() {

    return iterator(tail->next);
}

template<typename ElementType>
typename LinkedList<ElementType>::const_iterator& LinkedList<ElementType>::const_iterator::operator++() {

    if (current_ != nullptr) {
        current_ = current_->next;
    }
    
    return *this;
    
}

template<typename ElementType>
const ElementType& LinkedList<ElementType>::const_iterator::operator*() const {

    return current_->data;
    
}

template<typename ElementType>
bool LinkedList<ElementType>::const_iterator::operator!=(const LinkedList<ElementType>::const_iterator& other) const {

    return current_ != other.current_;
    
}

template<typename ElementType>
typename LinkedList<ElementType>::const_iterator LinkedList<ElementType>::begin() const {

    return const_iterator(head);
}

template<typename ElementType>
typename LinkedList<ElementType>::const_iterator LinkedList<ElementType>::end() const {

    return const_iterator(tail->next);
}
    

