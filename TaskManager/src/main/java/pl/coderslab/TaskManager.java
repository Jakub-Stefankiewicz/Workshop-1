package pl.coderslab;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {

    /* lista dostępnych do wyboru opcji: **/
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        optionsList();

    /* wybór opcji: **/
        String option;
        Scanner scan = new Scanner(System.in);
        option = scan.nextLine();
        switch (option){
            case "list":
                tasksList();
                break;
            default:
                System.out.println("wybierz dobrze");
        }

    }

    /* Użyte metody **/
    /* metoda z listą opcji do wybrou:**/
    public static void optionsList() {
        String optionsList[] = {"add", "remove", "list", "exit"};
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println(optionsList[i]);
        }
    }


    public static void tasksList() {
        StringBuilder reading = new StringBuilder();
        try {
            File file = new File("tasks.csv");
            Scanner tasksFromFile = new Scanner(file);
            long lines = 0;
            Path path = Paths.get("tasks.csv");

            /* pobieranie liczby wierszy:**/
                try {
                    lines = Files.lines(path).count();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(lines);
                int linesInt = (int) lines;

            /* definiowanie wielkości tablicy:**/
            tasks=new String[linesInt][3];

            for (int i = 0; i < linesInt; i++) {
                for (int j = 0; j < 3; j++) {
                    tasks[i][j]="test";
                }
            }

            for (int i = 0; i < linesInt; i++) {

            }


            for (int i = 0; i < linesInt; i++) {
                StringBuilder wiersz= new StringBuilder();
                for (int j = 0; j < 3; j++) {
                    wiersz.append(tasks[i][j] + ",");
                }
                System.out.println(wiersz);
            }


            int counter=0;
            String[] liniaStr= new String[linesInt];
            while (tasksFromFile.hasNextLine()) {
                String linia=tasksFromFile.nextLine();
                liniaStr[counter]=linia;
                System.out.println(counter);
                System.out.println(liniaStr[counter]);
                String[] str= liniaStr[counter].split(",");
                for (int i = 0; i < 3; i++) {
                    tasks[counter][i]=str[i];
                }
                counter++;
                }
            for (int i = 0; i < tasks.length; i++) {
                System.out.println("linia numer" + i);
                for (int j = 0; j < 3; j++) {
                    System.out.println("kolumna numer" + j);
                    System.out.println(tasks[i][j]);
                }

            }

//            while (tasksFromFile.hasNextLine()) {
//                reading.append(tasksFromFile.nextLine() + "\n");
//                String[] str= reading.toString().split(",");
//                for (int i = 0; i < str.length; i++) {
//                    System.out.println(str[i]);
//                }
//            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        System.out.println(reading.toString());

    }

    static String[][] tasks;
}

