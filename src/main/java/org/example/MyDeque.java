package org.example;

public class MyDeque<T> {
    MyLinkedList<T> myQueue;

    public MyDeque() {
        myQueue = new MyLinkedList<>();
    }

    public void addFront(INode<T> element) {
        myQueue.add(element);
    }

    public void addRear(INode<T> element) {
        myQueue.append(element);
    }
    public T removeFront(){
       return myQueue.popFirst().getKey();
    }
    public T removeRear(){
       return myQueue.popLast().getKey();
    }
    public boolean isEmpty() {
        return myQueue.isEmpty();
    }

    public int size() {
        return myQueue.size();
    }

    @Override
    public String toString() {
        return myQueue.toString();
    }
}
