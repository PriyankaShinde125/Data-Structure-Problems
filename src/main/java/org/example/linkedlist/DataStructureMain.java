package org.example.linkedlist;

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
    public static final int EXIT = 0;

    public static void main(String[] args) throws CustomException {
        DataStructureMain dataStructureMain = new DataStructureMain();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice :" +
                    "\n1 : unordered list operation" +
                    "\n2 : Ordered list operations" +
                    "\n3 : Balanced parenthesis" +
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
                case EXIT:
                    return;
                default:
                    System.out.println("Invalid input");

            }
        }
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
            if (expressionChars[i] == ')'){
                if(myStack.isEmpty()){
                    System.out.println("Expression is not balanced");
                    return;
                }
                myStack.pop();
            }
        }
        if(myStack.isEmpty())
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
