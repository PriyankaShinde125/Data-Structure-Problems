package org.example;

public class MyQueue<T> {
    int balance;
    MyLinkedList<T> myQueue;

    public MyQueue() {
        myQueue = new MyLinkedList<>();
        balance = 500000;
    }

    public void enqueue(INode<T> element) {
        myQueue.append(element);
    }

    @Override
    public String toString() {
        return myQueue.toString();
    }

    public INode<T> dequeue() {
        return myQueue.popFirst();
    }

    public boolean isEmpty() {
        return myQueue.isEmpty();
    }

    public int size() {
        return myQueue.size();
    }
}
