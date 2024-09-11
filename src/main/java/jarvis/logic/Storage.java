package jarvis.logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
*Provides an abstraction of the Storage of previously saved user data,
*from the users' previous useage of Jarvis
 It handles tasks such as adding, deleting, marking, unmarking, listing, and clearing tasks stored in the file.
 This class implements the Singleton pattern to ensure that only one instance of {@code Storage} is created.
 */
public class Storage {

    private File storage;
    private static Storage instance;
    public static final String LOAD_SAVE = "src/Data/SavedData.txt";

    /**
     * Private constructor for the {@code Storage} class.
     * Initializes the storage file and creates it if it doesn't exist.
     */
    private Storage() {
        storage = new File(LOAD_SAVE);
        try {
            if (!storage.exists()) {
                // Create the file if it doesn't exist and write initial data
                if (storage.createNewFile()) {
                    System.out.println("File created: " + storage.getName());
                } else {
                    System.out.println("File already exists.");
                }
            }
            assert this.storage.exists();
        } catch (IOException e) {
            System.out.println("An error occurred while creating or writing to the file.");
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task at the specified index from the storage file.
     *
     * @param i the index of the task to delete.
     */
    public void delete(int i) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));
            lines.remove(i);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();  // Ensure each line ends with a new line character
                }
            }

        } catch (IOException e) {
            System.out.println("Erroneous entry");
            e.printStackTrace();
        }
    }

    /**
     * Marks a task at the specified index in the storage file by replacing "[ ]" with "[X]".
     *
     * @param i the index of the task to mark as done.
     */
    public void mark(int i) {
        try {
            // Read all lines from the file
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));

            // Get the specific line and replace [ ] with [X]
            String lineToUpdate = lines.get(i);
            String updatedLine = lineToUpdate.replace("[ ]", "[X]");
            lines.set(i, updatedLine);

            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();  // Ensure each line ends with a new line character
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Unmarks a task at the specified index in the storage file by replacing "[X]" with "[ ]".
     *
     * @param i the index of the task to unmark as done.
     */
    public void unmark(int i) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));

            // Replace [X] with [ ] on the specified line
            String updatedLine = lines.get(i).replace("[X]", "[ ]");
            lines.set(i, updatedLine);

            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOAD_SAVE))) {
                for (String line : lines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Adds a new task to the storage file.
     *
     * @param s the string representation of the task to add.
     */
    public void add(String s) {
        try {
            FileWriter myWriter = new FileWriter(LOAD_SAVE, true);
            myWriter.write(s + System.lineSeparator());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Lists all tasks currently stored in the storage file.
     */
    public void list() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(LOAD_SAVE));
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    /**
     * Clears all content from the storage file.
     */
    public void clearFile() {
        try {
            FileWriter myWriter = new FileWriter(LOAD_SAVE, false);
            myWriter.close();
            System.out.println("File cleared successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while clearing the file.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the singleton instance of the {@code Storage} class.
     * If the instance is not yet created, it creates a new instance.
     *
     * @return the singleton instance of the {@code Storage} class.
     */
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }
}

