package shnoop.storage;

import shnoop.exceptions.*;
import shnoop.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final java.nio.file.Path path;

    public Storage(java.nio.file.Path path) {
        this.path = path;
    }

    /**
     * Clears (not deletes) file at specified file path
     * @throws IOException
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
    private static Task readFileToTask(String line) throws EmptyDescriptionException, ImproperFileTypeException {
        boolean taskIsCompleted = false;
        if (line.substring(0, 1).equals("1")) {
            taskIsCompleted = true;
        } else if (!(line.substring(0,1).equals("0"))) {
            throw new ImproperFileTypeException();
        }
        String taskType = line.substring(1, 4);
        String desc;
        try {
            switch (taskType) {
            case ("001"):
                // Todo
                return new Todo(line.substring(4, line.length()), taskIsCompleted);
            case ("002"):
                // Event
                desc = line.substring(4, line.indexOf("/from/"));
                String from = line.substring(line.indexOf("/from/") + 6, line.indexOf("/to/"));
                String to = line.substring(line.indexOf("/to/") + 4, line.length());
                return new Event(desc, from, to, taskIsCompleted);
            case ("003"):
                // Deadline
                desc = line.substring(4, line.indexOf("/by/"));
                String by = line.substring(line.indexOf("/by/") + 4, line.length());
                return new Deadline(desc, by, taskIsCompleted);
            }
        } catch (StringIndexOutOfBoundsException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return null;
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
     */
    public ArrayList<Task> load() throws ShnoopException, FileNotFoundException {
        boolean directoryExists = java.nio.file.Files.exists(path);

        if (!directoryExists) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
                // TODO handle the exception
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
        for (int i = 0; i < tasks.size() - 1; i ++) {
            try {
                writeToFile(tasks.get(i).toUString() + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
            } catch (NullPointerException eNull) {
                throw new RuntimeException("The file seems to be corrupted in some way.");
            }
        }
        writeToFile(task.toUString() + "\n");
    }

    public void save(TaskList tasks) throws IOException {
        clearFile();
        for (int i = 0; i < tasks.size(); i ++) {
            try {
                writeToFile(tasks.get(i).toUString() + "\n");
            } catch (IOException e) {
                System.out.println("Something went wrong when trying to writeToFile: " + e.getMessage());
            }
        }
    }
}
