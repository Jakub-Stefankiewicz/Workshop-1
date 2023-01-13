package pl.coderslab;

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
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        optionsList();

        String option;
        Scanner scan = new Scanner(System.in);
        option = scan.nextLine();
//        switch (option){
//            case "list":
//                tasksList();
//                break;
//            default:
//                System.out.println("wybierz dobrze");
//        }

    }

    public static void optionsList() {
        String optionsList[] = {"add", "remove", "list", "exit"};
        for (int i = 0; i < optionsList.length; i++) {
            System.out.println(optionsList[i]);
        }
        return;
    }

    public static void tasksList() {
        StringBuilder reading = new StringBuilder();
        try {
            File file = new File("tasks.csv");
            Scanner tasks = new Scanner(file);
            long lines = 0;
            Path path = Paths.get("tasks.csv");
            try {
                lines = Files.lines(path).count();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(lines);
            int linesInt = (int) lines;
            String[][] list = new String[3][linesInt];

            while (tasks.hasNextLine()) {
                reading.append(tasks.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        System.out.println(reading.toString());
        return;

//        static String[][] tasks;  ????????????????

    }
}

