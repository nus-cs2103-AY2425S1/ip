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
    private final String fileName;

    /**
     * Initializes read and write capabilities to some given text file in a directory.
     * @param filePath The absolute or relative directory the text file should be in.
     * @param fileName The name of the text file, without any directories, and inclusive of ".txt" at the end.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    private String fullDir() {
        return filePath + fileName;
    }

    /**
     * Opens a file at a given file directory and name during initialization of the object.
     * It should create a new empty file if the file does not exist, or open an existing file at the directory.
     */
    public void openFile() {
        try {
            // Inspired by https://stackoverflow.com/questions/28947250/ to handle creation of directories.
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File fullTextFileDir = new File(fullDir());

            // this creates a file only if it does not already exist - so running it un-conditionally is OK.
            // the only thing which changes is that it will return false if the file already exists.
            if (fullTextFileDir.createNewFile()) {
                System.out.println("Text file not detected. Initiating a new file to hold records...");
            } else {
                System.out.println("Reading data from existing text file at location: " + fullDir());
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

    /**
     * Creates a reader to read off text at a given existing text file.
     * @param f The relative directory of the text file to read from.
     * @return A scanner able to read from the text file.
     */
    private Scanner createScannerAtFile(File f) {
        Scanner sc = null;
        try {
            sc = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Something has gone wrong - file creation is not working.\n"
                    + "CHECK: Do you have a directory called 'data' at the same level as this app? If so, "
                    + "please delete it before continuing.");
            System.exit(1);
        }
        return sc;
    }

    /**
     * Converts a compliant text string from a text file into a list of tasks.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> parseTextStorage() {
        openFile();

        ArrayList<Task> items = new ArrayList<>();
        Scanner sc = createScannerAtFile(new File(fullDir()));

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
            FileWriter writer = new FileWriter(fullDir());

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
