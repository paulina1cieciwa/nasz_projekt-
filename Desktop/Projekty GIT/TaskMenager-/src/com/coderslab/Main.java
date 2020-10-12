package com.coderslab;

import org.apache.commons.lang3.ArrayUtils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static String[][] tasks;

    public static void main(String[] args) throws IOException {
        tasks = tasksLeader("tasks.csv");
        System.out.println(pl.coderslab.ConsoleColors.BLUE + "Please select an option" + pl.coderslab.ConsoleColors.RESET);
        String[] option = {"add", "remove", "list", "exit"};
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            String opt = scan.nextLine();
            switch (opt) {
                case "add":
                    addTasks();
                    break;
                case "remove":
                    removeTasks();
                    break;
                case "list":
                    printTasks(tasks);
                    break;
                case "exit":
                    exitTasks("tasks.csv");
                    break;
                default:
                    System.out.println("Please select a correct option");
            }
        }
        for (int i = 0; i < option.length; i++) {
            System.out.println(option[i]);
        }
    }

    public static String[][] tasksLeader(String fileName) throws IOException {
        Path path1 = Paths.get(fileName);
        if (!Files.exists(path1)) {
            System.out.println("No file");
        }
        String[][] tasks = null;
        try {
            List<String> lista = Files.readAllLines(path1);
            tasks = new String[lista.size()][lista.get(0).split(",").length];
            for (int i = 0; i < lista.size(); i++) {
                String[] splited = lista.get(i).split(",");
                for (int j = 0; j < splited.length; j++) {
                    tasks[i][j] = splited[j];
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return tasks;
    }

    public static void printTasks(String[][] tablica) {
        for (int i = 0; i < tablica.length; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < tablica[i].length; j++) {
                System.out.print(tablica[i][j] + " " + "");
            }
            System.out.print("\n");
        }
    }

    public static void addTasks() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please add task description");
        String descrition = scan.nextLine();
        System.out.println("Please add task due date");
        String date = scan.nextLine();
        System.out.println("Is your task is important: true/false");
        String importatnt = scan.nextLine();
        tasks = Arrays.copyOf(tasks, tasks.length + 1);
        int a = tasks.length - 1;
        int b = 3;
        tasks[a] = new String[b];
        tasks[a][0] = descrition;
        tasks[a][1] = date;
        tasks[a][2] = importatnt;

    }

    public static void removeTasks() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select number to remove");
        int number = scan.nextInt();
        System.out.println("Value was successfully deleted");
        while (number == 0) {
            System.out.println("Incorrect argument passed. Please give number greater or equal 0");
            scan.nextLine();
        }
        if (number < tasks.length) {
            tasks = ArrayUtils.remove(tasks, number);
        }
    }
    public static void exitTasks(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        String[] newTask = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            newTask[i] = String.join(",", tasks[i]);
        }
        try {
            Files.write(path, Arrays.asList(newTask));
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(pl.coderslab.ConsoleColors.RED + "Bye, bye." + pl.coderslab.ConsoleColors.RESET);

    }

}



