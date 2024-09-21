package shnoop.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

import shnoop.exceptions.EmptyDescriptionException;
import shnoop.exceptions.ImproperFileTypeException;
import shnoop.exceptions.ShnoopException;
import shnoop.tasks.Deadline;
import shnoop.tasks.Event;
import shnoop.tasks.Task;
import shnoop.tasks.TaskList;
import shnoop.tasks.Todo;

/**
 * Represents class that interacts with the text file containing all the relevant information used by the ChatBot.
 */
public class Storage {
    private final java.nio.file.Path path;

    /**
     * Initializes Storage with a certain file directory where text file will be used.
     *
     * @param path File path where text file is / will be located.
     */
    public Storage(java.nio.file.Path path) {
        this.path = path;
    }

    public Storage() {
        path = java.nio.file.Paths.get(System.getProperty("user.home"), "my", "apps", "dir");
    }

    /**
     * Clears (not deletes) file at specified file path
     *
     * @throws IOException If file does not exist.
     */
    public void clearFile() throws IOException {
        FileWriter fw = new FileWriter(path.toString() + "/shnoopstorage.txt", false);
        fw.write("");
        fw.close();
    }

    /**
     * Reads line from file and returns relevant task.
     *
     * @param line Taken from the file to be read, line by line.
     * @return Task based on details in file line.
     * @throws EmptyDescriptionException If description is empty.
     * @throws ImproperFileTypeException If file is corrupt or not in the proper format.
     */
    protected static Task readFileToTask(String line) throws EmptyDescriptionException, ImproperFileTypeException {
        boolean isTaskCompleted = false;
        if (line.substring(0, 1).equals("1")) {
            isTaskCompleted = true;
        } else if (!(line.substring(0, 1).equals("0"))) {
            throw new ImproperFileTypeException();
        }
        String taskType = line.substring(1, 4);
        String desc;
        try {
            switch (taskType) {
            case ("001"):
                // Todo
                return new Todo(line.substring(4, line.length()), isTaskCompleted);
            case ("002"):
                // Event
                desc = line.substring(4, line.indexOf("/from/"));
                String from = line.substring(line.indexOf("/from/") + 6, line.indexOf("/to/"));
                String to = line.substring(line.indexOf("/to/") + 4, line.length());
                return new Event(desc, from, to, isTaskCompleted);
            case ("003"):
                // Deadline
                desc = line.substring(4, line.indexOf("/by/"));
                String by = line.substring(line.indexOf("/by/") + 4, line.length());
                return new Deadline(desc, by, isTaskCompleted);
            default:
                throw new RuntimeException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    // @@author CS2103T Website
    // Reused from https://nus-cs2103-ay2425s1.github.io/website/schedule/week3/topics.html
    // With minor modifications

    /**
     * Writes to specified file.
     *
     * @param textToAdd Text to be appended (not overwritten) to the file.
     * @throws IOException If there are errors when writing to the file.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(path.toString() + "/shnoopstorage.txt", true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads tasks from file.
     *
     * @param path Specified location of file to read from.
     * @throws FileNotFoundException If file is not found.
     */
    private ArrayList<Task> loadFileContents(String path) throws FileNotFoundException {
        File f = new File(path); // create a File for the given file path
        ArrayList<Task> result = new ArrayList<>();
        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                try {
                    result.add(readFileToTask(s.nextLine()));
                } catch (EmptyDescriptionException | ImproperFileTypeException e) {
                    System.out.println("You can find the file at " + path.toString());
                    throw new FileNotFoundException("You can find the file at " + path.toString()
                            + "\n Consider deleting or rectifying the file.");
                }
            }
        }
        return result;
    }
    // @@author CS2103T Website


    /**
     * Loads file based on path Storage was initialised with. Creates new one if not available.
     * @return ArrayList of Tasks that was loaded from the text file.
     */
    public ArrayList<Task> load() throws ShnoopException, FileNotFoundException {
        boolean directoryExists = java.nio.file.Files.exists(path);

        // @@author nigeltzy-reused
        // Original author: Steve Hills
        // Reused from https://www.sghill.net/2014/how-do-i-make-cross-platform-file-paths-in-java/
        // with minor modifications
        if (!directoryExists) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
                throw new ShnoopException("Another IOException error huh...");
            }
        }

        File file = new File(path.toFile(), "shnoopstorage.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new ShnoopException("There has been an IOException Error.");
            }
        }
        // @@author


        ArrayList<Task> result = loadFileContents(path.toString() + "/shnoopstorage.txt");
        return result;
    }

    /**
     * Saves tasks into storage.
     *
     * @param tasks TaskList comprising tasks.
     * @throws IOException If something went wrong when trying to write into the file.
     */
    public void save(TaskList tasks, Task task) throws IOException {
        clearFile();
        for (int i = 0; i < tasks.size() - 1; i++) {
            try {
                writeToFile(tasks.get(i).toUniqueFileString() + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
            } catch (NullPointerException eNull) {
                throw new RuntimeException("The file seems to be corrupted in some way.");
            }
        }
        writeToFile(task.toUniqueFileString() + "\n");
    }

    /**
     * Saves the Tasks and any relevant changes into the text file specified in the file path.
     *
     * @param tasks Tasks to be saved.
     * @throws IOException If the file does not exist.
     */
    public void save(TaskList tasks) throws IOException {
        clearFile();
        for (int i = 0; i < tasks.size(); i++) {
            try {
                writeToFile(tasks.get(i).toUniqueFileString() + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
            }
        }
    }
}
