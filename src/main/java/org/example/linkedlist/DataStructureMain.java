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
    public static final int EXIT = 0;
    private MyLinkedList<String> list;

    public static void main(String[] args) throws CustomException {
        DataStructureMain dataStructureMain = new DataStructureMain();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your choice :" +
                    "\n1 : unordered list operation" +
                    "\n0 : Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case UNORDERED_LIST:
                    dataStructureMain.unorderedListReadWriteToFile();
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Invalid input");

            }
        }
    }

    private void unorderedListReadWriteToFile() throws CustomException {
        list = new MyLinkedList<>();
        String[] strArr = readDataFromFile();
        Arrays.stream(strArr).forEach(s -> list.add(new MyNode<>(s)));
        Scanner sc = new Scanner(System.in);
        String key = sc.next();
        if (list.search(new MyNode<>(key)) != null)
            list.delete(new MyNode<>(key));
        else
            list.add(new MyNode<>(key));
        System.out.println(list);
        writeDataToTextFile();
    }

    String[] readDataFromFile() throws CustomException {
        String[] wordList;
        try {
            Path path = Paths.get("src/main/resources/listdata.txt");
            Stream<String> lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\\s"));
            wordList = data.toLowerCase().split("\\s");
            lines.close();
        } catch (IOException e) {
            throw new CustomException(ExceptionType.IO_EXCEPTION);
        }
        return wordList;
    }

    public void writeDataToTextFile() throws CustomException {
        Path path = Paths.get("src/main/resources/listdata.txt");
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write(list.toString().replaceAll("->"," "));
            System.out.println("Data write to file successfully");
        } catch (IOException e) {
            throw new CustomException(ExceptionType.IO_EXCEPTION);
        }
    }
}
