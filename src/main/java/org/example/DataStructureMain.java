package org.example;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DataStructureMain {
    public static final int UNORDERED_LIST = 1;
    public static final int ORDERED_LIST = 2;
    public static final int BALANCED_PARENTHESIS = 3;
    public static final int BANKING_CASH_COUNTER = 4;
    public static final int PALINDROME_CHECKER = 5;
    public static final int PRIME_IN_2D_ARRAY = 6;
    public static final int PRIME_ANAGRAMS = 7;
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
                    "\n6 : Store all primes in 2D array" +
                    "\n7 : Prime anagrams" +
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
                case PRIME_IN_2D_ARRAY:
                    int[][] primeArray = dataStructureMain.getPrimeNumbers();
                    dataStructureMain.printArray(primeArray, 10, 100);
                    break;
                case PRIME_ANAGRAMS:
                    dataStructureMain.printAllAnagramPrime();
                    break;
                case EXIT:
                    return;
                default:
                    System.out.println("Invalid input");
            }
        }
    }

    private boolean isAnagram(String str1, String str2) {
        if (str1.equalsIgnoreCase(str2))
            return true;

        if (str1.length() == 1 && str2.length() == 1 && !str1.equalsIgnoreCase(str2))
            return false;
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] str1CharArray = str1.toCharArray();
        char[] str2CharArray = str2.toCharArray();
        Arrays.sort(str1CharArray);
        Arrays.sort(str2CharArray);
        return Arrays.equals(str1CharArray, str2CharArray);
    }

    private void printAllAnagramPrime() {
        int[] primes = Arrays.stream(getPrimeNumbers()).flatMapToInt(IntStream::of).filter(value -> value != 0).toArray();
        int[] primeAnagramsArr = new int[1000];
        int[][] primeAnagrams = new int[10][100];
        int k = 0;
        System.out.println("Anagram Primes");
        for (int i = 0; i < primes.length; i++) {
            for (int j = i + 1; j < primes.length; j++) {
                if (isAnagram(String.valueOf(primes[i]), String.valueOf(primes[j]))) {
                    primeAnagramsArr[k] = primes[i];
                    k++;
                    primeAnagramsArr[k] = primes[j];
                    k++;
                }
           }
        }
        int[] tempArr = Arrays.stream(primeAnagramsArr).sorted().distinct().filter(value -> value != 0).toArray();
        int temp = tempArr[0] / 100;
        int j = 0;
        int l = 0;
        for (int value : tempArr) {
            if (value / 100 > temp) {
                j++;
                l = 0;
            }
            primeAnagrams[j][l] = value;
            l++;
            temp = value / 100;
        }
        printArray(primeAnagrams, 10, 100);
    }

    private void printArray(int[][] primeArray, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (primeArray[i][j] == 0)
                    break;
                System.out.print(primeArray[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] getPrimeNumbers() {
        int[][] primeNumbers = new int[10][100];
        int limit = 1000;
        int number = 2;
        int i = 0;
        int j = 0;
        int temp = number;

        while (number <= limit) {
            if (number == 2) {
                primeNumbers[i][j] = 2;
                j++;
                number++;
                continue;
            }
            if (temp > number % 100)
                j = 0;
            if (isPrime(number)) {
                primeNumbers[i][j] = number;
                j++;
            }
            i = number / 100;
            temp = number % 100;
            number++;
        }
        return primeNumbers;
    }

    private boolean isPrime(int number) {
        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0)
                return false;
        }
        return true;
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
                    if (deposit(queue)) continue;
                    break;

                case WITHDRAW:
                    if (withdraw(queue)) continue;
                    break;

                case ENQUEUE_PEOPLE:
                    enqueuePeople(queue);
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

    private static void enqueuePeople(MyQueue<String> queue) {
        Scanner sc = new Scanner(System.in);
        System.out.println("how many people do you want to enqueue :");
        int numberOfPeople = sc.nextInt();
        for (int i = 1; i <= numberOfPeople; i++) {
            System.out.println("Enter person name :");
            queue.enqueue(new MyNode<>(sc.next()));
        }
        System.out.println(queue);
    }

    private boolean withdraw(MyQueue<String> queue) {
        int amount;
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            return true;
        }
        amount = getAmount();
        queue.balance -= amount;
        System.out.println(queue.dequeue() + " has made withdrawal of Rs " + amount);
        return false;
    }

    private boolean deposit(MyQueue<String> queue) {
        int amount;
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            return true;
        }
        amount = getAmount();
        queue.balance += amount;
        System.out.println(queue.dequeue() + " has made deposit of Rs " + amount);
        return false;
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
