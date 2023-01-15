package pl.coderslab;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;
import java.util.SortedMap;

public class TaskManager {
    public static void main(String[] args) {

    /* wybór opcji: **/
        options();
    }

    /* METODY GŁÓWNE **/
    /* metoda z listą opcji do wybrou:**/
    public static void optionsList() {

        listArray();

        String optionsList[] = {"add", "remove", "list", "exit"};
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println(optionsList[i]);
        }
    }

    /* metoda zamieniająca plik CSV na tablicę dwuwymiarową:**/
    public static String[][] listArray() {
        try {
            File file = new File("tasks.csv");
            Scanner tasksFromFile = new Scanner(file);
            int counter=0;
            String[] liniaStr= new String[rows()];
            while (tasksFromFile.hasNextLine()) {
                String linia=tasksFromFile.nextLine();
                liniaStr[counter]=linia;
                String[] str= liniaStr[counter].split(",");
                for (int i = 0; i < cols(); i++) {
                    tasks[counter][i]=str[i];
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return tasks;
    }

    /* metoda dodająca zadanie**/
    public static void add(){

        listArray();

        tasks=Arrays.copyOf(tasks,tasks.length+1);
        tasks[tasks.length-1]=new String[3];

        System.out.println("Please add task description");
        Scanner description= new Scanner(System.in);
        tasks[tasks.length-1][0]=description.next();

        System.out.println("Please add task date");
        Scanner date= new Scanner(System.in);
        tasks[tasks.length-1][1]=date.next();

        System.out.println("Please add task priority: true/false");
        Scanner scan = new Scanner(System.in);
        String option="";
        while (!option.equals("true") && !option.equals("false")){
            switch (option= scan.nextLine()) {
                case "true":
                    tasks[tasks.length - 1][2] = option;
                    break;
                case "false":
                    tasks[tasks.length - 1][2] = option;
                    break;
                default:
                    System.out.println(ConsoleColors.PURPLE_BOLD + "write true or false!" + ConsoleColors.RESET);
            }
        }
    }

    /* metoda usuwająca zadanie**/
    public static void remove(){

        listArray();

        System.out.println("Please select number to remove");
        Scanner rem=new Scanner(System.in);
        String remString="";
        int remInt=tasks.length+1;
        while (remInt > tasks.length || remInt < 0) {
            remString = rem.nextLine();
            try {
            remInt = Integer.parseInt(remString);
                if (remInt < tasks.length && remInt > 0) {
                    tasks = ArrayUtils.remove(tasks, remInt);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(ConsoleColors.PURPLE_BOLD + "podaj wartość liczbową!" + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.PURPLE_BOLD + "podaj wartość z zakresu od 0 do " + (tasks.length - 1) + ConsoleColors.RESET);
            }
        }

    /* metoda wyświetlająca listę zadań**/
    public static void tasksList() {
        for (int i = 0; i < tasks.length; i++) {
            StringBuilder linia= new StringBuilder();
            for (int j = 0; j < cols(); j++) {
                linia.append(tasks[i][j] + ",");
            }
            String lineNr=Integer.toString(i);
            System.out.println(lineNr + " " + ":" + " " + linia);
        }
    }

    /* metoda zapisująca zmiany i wychodząca z programu**/
    public static void exit(){
        Path plik=Paths.get("tasks.csv");
        try {
        StringBuilder sb = new StringBuilder();
            for (int i = 0; i < tasks.length; i++) {
                for (int j = 0; j < cols(); j++) {
                    sb.append(tasks[i][j] + ",");
                }
                sb.append("\n");
            }
            Files.writeString(plik,sb.toString());
            } catch (IOException ex){
            System.out.println("Błąd zapisu do pliku");
        }

        System.out.println(ConsoleColors.RED + "bye, bye" + ConsoleColors.RESET);
    }

    /* metoda do wyboru opcji**/
    public static void options(){
        String option="";
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        optionsList();
        while (!option.equals("exit")){
            switch (option= scan.nextLine()) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove();
                    break;
                case "list":
                    tasksList();
                    break;
                case "exit":
                    exit();
                    break;
                default:
                    System.out.println(ConsoleColors.PURPLE_BOLD + "wybierz jedną z dostępnych opcji!" + ConsoleColors.RESET);
            }
        }
    }

    /* METODY POMOCNICZE**/

    /* liczba wierszy w tablicy tasks**/
    public static int rows() {
        int rows=0;
        try {
            File file = new File("tasks.csv");
            Scanner tasksFromFile = new Scanner(file);
            long lines = 0;
            Path path = Paths.get("tasks.csv");
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            rows = (int) lines;

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return rows;
    }

    /* liczba kolumn w tablicy tasks**/
    public static int cols(){
        int cols=0;
        try {
            File file = new File("tasks.csv");
            Scanner tasksline = new Scanner(file);
            String tasks=tasksline.nextLine();
            String[] tasksCols=tasks.split(",");
            cols=tasksCols.length;

        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        return cols;
    }

    /* deklaracja dwuwymiarowej tablicy**/
    static String[][] tasks= new String[rows()][cols()];

}

