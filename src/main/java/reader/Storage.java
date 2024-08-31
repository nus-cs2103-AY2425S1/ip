package storage;

import grok.GrokInvalidUserInputException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public static ArrayList<Task> parseTextStorage() {
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
                String s = sc.nextLine();
                String[] components = s.split(" \\| ");

                // this may trigger an invalid input exception - but this is not to be expected at all.
                switch (s.substring(0, 1)) {
                case "T":
                    items.add(new Todo(components[2], components[1].equals("X")));
                    break;
                case "D":
                    items.add(new Deadline(components[2], components[3], components[1].equals("X")));
                    break;
                case "E":
                    items.add(new Event(components[2], components[3], components[4], components[1].equals("X")));
                    break;
                default:
                    System.out.println("Corrupted data detected.");
                    items.clear();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred in file scanning :( Stack trace:");
            e.printStackTrace();
        } catch (GrokInvalidUserInputException e) {
            System.out.println("Something has gone seriously wrong - you should not have invalid user input without a user!");
            e.printStackTrace();
            System.exit(1);
        }
        return items;
    }

    public static void writeToTextStorage(ArrayList<Task> tasks) {
        // I can't convert this with polymorphism since I cannot overwrite static methods :(
        // any alternate ideas?
        try {
            // this line potentially throws IOException.
            FileWriter writer = new FileWriter(filePath);

            for (Task t: tasks) {
                writer.write(t.serialize());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred in file scanning :( Stack trace:");
            e.printStackTrace();
        }
    }
}
