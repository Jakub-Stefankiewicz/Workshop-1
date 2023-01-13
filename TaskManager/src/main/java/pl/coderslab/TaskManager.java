package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        System.out.println(ConsoleColors.BLUE + "Please select an option" + ConsoleColors.RESET);
        optionsList();

        String option;
        String [][] list= new String[4][4];
        Scanner scan= new Scanner(System.in);

        option=scan.nextLine();
        if (option.contentEquals("list")) {
            StringBuilder reading = new StringBuilder();
            try {
                File file = new File("tasks.csv");
                Scanner tasks = new Scanner(file);
                while (tasks.hasNextLine()) {
                    reading.append(tasks.nextLine() + "\n");
                }
            } catch (FileNotFoundException e) {
                System.out.println("Brak pliku tasks.");
            }
            System.out.println(reading.toString());
        } else {
            System.out.println("coś źle");
        }

        }

        public static void optionsList(){
            String optionsList[]={"add", "remove", "list", "exit"};
            for (int i = 0; i < optionsList.length; i++) {
                System.out.println(optionsList[i]);
            }
            return;
        }
}
