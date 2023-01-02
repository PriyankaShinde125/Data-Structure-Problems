package org.example;

public interface INode<T> extends Comparable<INode<T>>{
    T getKey();

    void setKey(T key);

    INode<T> getNext();

    void setNext(INode<T> next);

    int compareTo(INode<T> o);
}
