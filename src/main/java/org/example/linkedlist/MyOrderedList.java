package org.example.linkedlist;

public class MyOrderedList<T> extends MyLinkedList {
    @Override
    public void add(INode newNode) {
        if (tail == null) {
            setTail(newNode);
        }
        if (head == null) {
            setHead(newNode);
            return;
        }
        if (head.compareTo(newNode) > 0) {
            INode<T> tempNode = head;
            setHead(newNode);
            head.setNext(tempNode);
            return;
        }
        currentNode = getHead();
        while (currentNode.getNext() != null && currentNode.getNext().compareTo(newNode) < 0) {
            currentNode = currentNode.getNext();
        }
        insertAfter(currentNode, newNode);
    }
}
