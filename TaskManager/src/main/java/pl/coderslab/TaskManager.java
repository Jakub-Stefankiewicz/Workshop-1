package pl.coderslab;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TaskManager {
    public static void main(String[] args) {
        StringBuilder reading = new StringBuilder();
        try {
            File file = new File("tasks.csv");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                reading.append(scan.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Brak pliku tasks.");
        }
        System.out.println(reading.toString());

        }
}
