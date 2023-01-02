package org.example.linkedlist;

public class MyStack<T> {
    MyLinkedList<T> myLinkedList;

    public MyStack() {
        myLinkedList = new MyLinkedList<>();
    }

    public void push(INode<T> element) {
        myLinkedList.add(element);
    }

    @Override
    public String toString() {
        return myLinkedList.toString();
    }

    public INode<T> pop() {
        return myLinkedList.popFirst();
    }

    public T peak() {
        return myLinkedList.getHead().getKey();
    }

    public boolean isEmpty() {
        return myLinkedList.isEmpty();
    }

    public int size() {
        return myLinkedList.size();
    }
}
