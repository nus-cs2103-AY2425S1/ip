package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.GrokInvalidUserInputException;
import tasklist.TaskList;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Persistent storage reader/writer, such as with a plaintext text file.
 * Other persistent media like databases could be explored at a later date.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Converts a compliant text string from a text file into a list of tasks.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> parseTextStorage() {
        File file = new File(filePath);

        ArrayList<Task> items = new ArrayList<>();

        try {
            // this creates a file only if it does not already exist - so running it un-conditionally is OK.
            // the only thing which changes is that it will return false if the file already exists.
            if (file.createNewFile()) {
                System.out.println("Text file not detected. Initiating a new file to hold records...");
            } else {
                System.out.println("Reading data from existing text file at location: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred in file opening :(");
            return items;
        }

        try {
            // this line potentially throws FileNotFoundException.
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                String[] components = s.split(" \\| ");
                assert !s.isEmpty();

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
        } catch (FileNotFoundException | GrokInvalidUserInputException e) {
            System.out.println("Something has gone wrong - text file is corrupted, or file creation is not working.");
            System.exit(1);
        }

        return items;
    }

    /**
     * Converts a compliant task list into persistent text storage.
     * Danger: Overwrites previous data.
     * @param tasks the taskList in use
     */
    public void writeToTextStorage(TaskList tasks) {
        try {
            // this line potentially throws IOException.
            FileWriter writer = new FileWriter(filePath);

            for (Task t: tasks.getAllTasks()) {
                writer.write(t.serialize());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the text file: " + e.getMessage());
        }
    }
}
