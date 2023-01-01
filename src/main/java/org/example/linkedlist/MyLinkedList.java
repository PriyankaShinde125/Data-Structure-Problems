package org.example.linkedlist;

public class MyLinkedList<T> {
    INode<T> head;
    INode<T> tail;
    INode<T> currentNode;

    public MyLinkedList(INode<T> head, INode<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    public MyLinkedList() {}

    public INode<T> getHead() {
        return head;
    }

    public void setHead(INode<T> head) {
        this.head = head;
    }

    public INode<T> getTail() {
        return tail;
    }

    public void setTail(INode<T> tail) {
        this.tail = tail;
    }

    public void add(INode<T> newNode) {
        if (tail == null) {
            setTail(newNode);
        }
        if (head == null) {
            setHead(newNode);
        } else {
            INode<T> tempNode = head;
            setHead(newNode);
            head.setNext(tempNode);
        }
    }

    public void append(INode<T> newNode) {
        if (head == null) {
            setHead(newNode);
        }
        if (tail == null) {
            setTail(newNode);
        } else {
            INode<T> tempNode = getTail();
            setTail(newNode);
            tempNode.setNext(tail);
        }
    }

    public void insert(INode<T> firstNode, INode<T> newNode, INode<T> secondNode) {
        if (head == null || tail == head) {
            setHead(firstNode);
            head.setNext(newNode);
            setTail(secondNode);
            newNode.setNext(tail);
        } else {
            currentNode = head;
            while (currentNode.getKey() != firstNode.getKey() && currentNode.getNext().getKey() != secondNode.getKey()) {
                currentNode = currentNode.getNext();
            }
            currentNode.setNext(newNode);
            newNode.setNext(secondNode);
        }
    }

    public INode<T> popFirst() {
        INode<T> tempHead;
        if (tail == head) {
            tempHead = head;
            setHead(null);
            setTail(null);
        } else {
            tempHead = head;
            setHead(head.getNext());
            tempHead.setNext(null);
        }
        return tempHead;
    }

    public INode<T> popLast() {
        INode<T> tempTail;
        if (tail == head) {
            tempTail = tail;
            setHead(null);
            setTail(null);
        } else {
            tempTail = tail;
            currentNode = head;
            while (currentNode.getNext() != tail) {
                currentNode = currentNode.getNext();
            }
            setTail(currentNode);
            tail.setNext(null);
        }
        return tempTail;
    }

    @Override
    public String toString() {
        return String.valueOf(head);
    }

    public INode<T> search(INode<T> myNode) {
        currentNode = head;
        while (currentNode != null) {
            if (currentNode.getKey().equals(myNode.getKey())) {
                break;
            }
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public void insertAfter(INode<T> insertAfter, INode<T> myNode) {
        currentNode = getHead();
        while (currentNode.getKey() != insertAfter.getKey()) {
            currentNode = currentNode.getNext();
        }
        if (currentNode == tail) {
            currentNode.setNext(myNode);
            setTail(myNode);
        } else {
            INode<T> temp = currentNode.getNext();
            currentNode.setNext(myNode);
            myNode.setNext(temp);
        }
    }

    public void delete(INode<T> deleteNode) {
        INode<T> temp = null;
        if (head.getKey().equals(deleteNode.getKey())) {
            setHead(head.getNext());
        }else {
            currentNode = getHead();
            while (currentNode != null && !currentNode.getKey().equals(deleteNode.getKey())) {
                temp = currentNode;
                currentNode = currentNode.getNext();
            }
            if (currentNode == tail) {
                temp.setNext(null);
                setTail(temp);
            }
            if (currentNode != null) {
                temp.setNext(currentNode.getNext());
                currentNode.setNext(null);
            }
        }
    }

   public int size() {
        int size = 0;
        if (getHead() != null) {
            currentNode = getHead();
            size++;
            while (currentNode != getTail() && currentNode != null) {
                size++;
                currentNode = currentNode.getNext();
            }
        }
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public INode<T> getElementFromIndex(int index) {
        int indexSetter = 0;
        if (size() < index) {
            return null;
        }
        currentNode = getHead();
        indexSetter++;
        while (indexSetter < index) {
            indexSetter++;
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }

    public Integer getIndexOf(INode<T> myNode) {
        int indexOf = 0;
        if (search(myNode)!=null) {
            currentNode = getHead();
            indexOf++;
            while (currentNode.getKey() != myNode.getKey()) {
                indexOf++;
                currentNode = currentNode.getNext();
            }
        } else return null;
        return indexOf;
    }

    public boolean removeFromIndex(int index) {
        if (!isEmpty()) {
            delete(getElementFromIndex(index));
            return true;
        } else return false;
    }
}
