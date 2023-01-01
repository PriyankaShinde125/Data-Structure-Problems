package org.example.linkedlist;

public class MyNode<T extends Comparable> implements INode<T> {
    T key;
    INode<T> next;

    public MyNode(T key) {
        this.key = key;
        this.next = null;
    }

    public MyNode(T key, INode<T> next) {
        this.key = key;
        this.next = next;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public INode<T> getNext() {
        return next;
    }

    public void setNext(INode<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder nodeString = new StringBuilder();
              nodeString.append(key);
        if (next != null)
            nodeString = nodeString.append("->"+next);
        return String.valueOf(nodeString);
    }
    @Override
    public int compareTo(INode<T> o) {
        return this.key.compareTo(o.getKey());
    }
}
