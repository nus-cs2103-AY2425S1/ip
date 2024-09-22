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
     * Opens a file at a given file directory.
     * It should create a new empty file if the file does not exist, or open an existing file at the directory.
     * @param f The file directory of a file which may or may not exist.
     */
    public void openFile(File f) {
        try {
            // this creates a file only if it does not already exist - so running it un-conditionally is OK.
            // the only thing which changes is that it will return false if the file already exists.
            if (f.createNewFile()) {
                System.out.println("Text file not detected. Initiating a new file to hold records...");
            } else {
                System.out.println("Reading data from existing text file at location: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred in file opening :(");
            System.exit(1);
        }
    }

    private Task initializeTaskFromInputLine(String inputLine) {
        String[] components = inputLine.split(" \\| ");
        try {
            switch (inputLine.substring(0, 1)) {
            case "T":
                return new Todo(components[2], components[1].equals("X"));
            case "D":
                return new Deadline(components[2], components[3], components[1].equals("X"));
            case "E":
                return new Event(components[2], components[3], components[4], components[1].equals("X"));
            default:
                System.out.println("Corrupted data detected.");
                System.exit(1);
                return null;
            }
        } catch (GrokInvalidUserInputException e) {
            System.out.println("Data entered is not denoted as a task subtype.");
            System.exit(1);
            return null;
        }
    }

    private Scanner createScannerAtFile(File f) {
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Something has gone wrong - text file is corrupted, or file creation is not working.");
            System.exit(1);
        }
        return sc;
    }

    /**
     * Converts a compliant text string from a text file into a list of tasks.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> parseTextStorage() {
        File file = new File(filePath);
        openFile(file);

        ArrayList<Task> items = new ArrayList<>();
        Scanner sc = createScannerAtFile(file);

        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            items.add(initializeTaskFromInputLine(s));
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

            tasks.getAllTasks()
                    .forEach(t -> {
                        try {
                            writer.write(t.serialize() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the text file: " + e.getMessage());
        }
    }
}
