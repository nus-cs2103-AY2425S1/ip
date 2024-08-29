package grok;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileReader {

    public static ArrayList<Task> parseFile(String fileName) {
        File file = new File(fileName);

        ArrayList<Task> items = new ArrayList<>();

        try {
            // this creates a file only if it does not already exist - so running it un-conditionally is OK.
            // the only thing which changes is that it will return false if the file already exists.
            if (file.createNewFile()) {
                System.out.println("Initiating a new file to hold records...");
            } else {
                System.out.println("Reading data from existing file at location: " + fileName);
            }
        } catch (IOException e) {
            System.out.println("An error occurred in file opening :( Stack trace:");
            e.printStackTrace();
            return items;
        }

        try {
            // this line potentially throws FileNotFoundException.
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            return items;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in file scanning :( Stack trace:");
            e.printStackTrace();
            return items;
        }
    }

    public static void writeToFile(String fileName) {

    }
}
