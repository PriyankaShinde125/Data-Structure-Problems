package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataStructureMain {
    public static final int UNORDERED_LIST = 1;
    public static final int ORDERED_LIST = 2;
    public static final int BALANCED_PARENTHESIS = 3;
    public static final int BANKING_CASH_COUNTER = 4;
    public static final int PALINDROME_CHECKER = 5;
    public static final int EXIT = 0;
    public static final int DEPOSIT = 1;
    public static final int WITHDRAW = 2;
    public static final int ENQUEUE_PEOPLE = 3;

    public static void main(String[] args) throws CustomException {
        DataStructureMain dataStructureMain = new DataStructureMain();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice :" +
                    "\n1 : unordered list operation" +
                    "\n2 : Ordered list operations" +
                    "\n3 : Balanced parenthesis" +
                    "\n4 : Simulate bank cash counter" +
                    "\n5 : Palindrome checker" +
                    "\n0 : Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case UNORDERED_LIST:
                    dataStructureMain.unorderedListReadWriteToFile();
                    break;
                case ORDERED_LIST:
                    dataStructureMain.orderedListReadWriteToFile();
                    break;
                case BALANCED_PARENTHESIS:
                    dataStructureMain.ensureBalancedParenthesis();
                    break;
                case BANKING_CASH_COUNTER:
                    dataStructureMain.simulateCashCounter();
                    break;
                case PALINDROME_CHECKER:
                    dataStructureMain.palindromeChecker();
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private void palindromeChecker() {
        MyDeque<Character> myDeque = new MyDeque<>();
        System.out.println("Enter a string");
        Scanner sc = new Scanner(System.in);
        char[] inputStringChars = sc.next().toCharArray();
        for (int i = 0; i < inputStringChars.length; i++) {
            myDeque.addRear(new MyNode<>(inputStringChars[i]));
        }
        while (myDeque.size() > 1) {
            if (myDeque.removeFront() != myDeque.removeRear()) {
                System.out.println("Strings are not palindrome");
                return;
            }
        }
        System.out.println("String are palindrome");
    }

    private void simulateCashCounter() {
        MyQueue<String> queue = new MyQueue<>();
        Scanner sc = new Scanner(System.in);
        int amount;
        boolean flag = false;

        while (!flag) {
            System.out.println("Select operation type : \n1 : Deposite \n2 : Withraw \n3 : Enqueue people \n0 : Close counter");
            int input = sc.nextInt();
            switch (input) {
                case DEPOSIT:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                        continue;
                    }
                    amount = getAmount();
                    queue.balance += amount;
                    System.out.println(queue.dequeue() + " has made deposit of Rs " + amount);
                    break;

                case WITHDRAW:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty");
                        continue;
                    }
                    amount = getAmount();
                    queue.balance -= amount;
                    System.out.println(queue.dequeue() + " has made withdrawal of Rs " + amount);
                    break;

                case ENQUEUE_PEOPLE:
                    System.out.println("how many people do you want to enqueue :");
                    int numberOfPeople = sc.nextInt();
                    for (int i = 1; i <= numberOfPeople; i++) {
                        System.out.println("Enter person name :");
                        queue.enqueue(new MyNode<>(sc.next()));
                    }
                    System.out.println(queue);
                    break;

                case EXIT:
                    return;

                default:
                    System.out.println("Enter valid input");
                    continue;
            }

            System.out.println("Cash balance = " + queue.balance);
            flag = queue.isEmpty();
        }
    }

    private int getAmount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter amount");
        int amount = sc.nextInt();
        return amount;
    }

    private void ensureBalancedParenthesis() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an expression : ");
        String expression = sc.next();
        char[] expressionChars = expression.toCharArray();
        MyStack myStack = new MyStack<>();
        for (int i = 0; i < expressionChars.length; i++) {
            if (expressionChars[i] == '(')
                myStack.push(new MyNode(expressionChars[0]));
            if (expressionChars[i] == ')') {
                if (myStack.isEmpty()) {
                    System.out.println("Expression is not balanced");
                    return;
                }
                myStack.pop();
            }
        }
        if (myStack.isEmpty())
            System.out.println("Expression is balanced");
        else
            System.out.println("Expression is not balanced");
    }

    private void orderedListReadWriteToFile() throws CustomException {
        MyLinkedList<Integer> list = new MyOrderedList<>();
        String[] strArr = readDataFromFile("orderedlistdata.txt");
        Arrays.stream(strArr).forEach(s -> list.add(new MyNode<>(Integer.parseInt(s))));
        System.out.println(list);
        System.out.println("Enter a key to add or remove :");
        Scanner sc = new Scanner(System.in);
        Integer key = sc.nextInt();
        if (list.search(new MyNode(key)) != null)
            list.delete(new MyNode(key));
        else
            list.add(new MyNode(key));
        System.out.println(list);
        writeDataToTextFile("orderedlistdata.txt", list);
    }

    private void unorderedListReadWriteToFile() throws CustomException {
        MyLinkedList<String> list = new MyLinkedList<>();
        String[] strArr = readDataFromFile("listdata.txt");
        Arrays.stream(strArr).forEach(s -> list.add(new MyNode(s)));
        System.out.println(list);
        System.out.println("Enter a key to add or remove :");
        Scanner sc = new Scanner(System.in);
        String key = sc.next();
        if (list.search(new MyNode(key)) != null)
            list.delete(new MyNode(key));
        else
            list.add(new MyNode(key));
        System.out.println(list);
        writeDataToTextFile("listdata.txt", list);
    }

    String[] readDataFromFile(String fileName) throws CustomException {
        String[] wordList;
        try {
            Path path = Paths.get("src/main/resources/" + fileName);
            Stream<String> lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\\s"));
            wordList = data.toLowerCase().split("\\s");
            lines.close();
        } catch (IOException e) {
            throw new CustomException(ExceptionType.IO_EXCEPTION);
        }
        return wordList;
    }

    public <T extends Comparable<T>> void writeDataToTextFile(String fileName, MyLinkedList<T> list) throws CustomException {
        Path path = Paths.get("src/main/resources/" + fileName);
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write(list.toString().replaceAll("->", " "));
            System.out.println("Data write to file successfully");
        } catch (IOException e) {
            throw new CustomException(ExceptionType.IO_EXCEPTION);
        }
    }
}
