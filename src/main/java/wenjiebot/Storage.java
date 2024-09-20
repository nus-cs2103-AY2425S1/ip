package wenjiebot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import wenjiebot.exceptions.NoFileException;
import wenjiebot.tasks.Deadline;
import wenjiebot.tasks.Event;
import wenjiebot.tasks.Task;
import wenjiebot.tasks.ToDo;

/**
 * The Storage class handles the reading and writing of tasks to and from a file.
 * It allows tasks to be persisted between sessions of the bot.
 */
public class Storage {

    private final String filePath;
    private ArrayList<Task> tasks = new ArrayList<>(0);

    /**
     * Constructs a Storage object with the specified file path.
     * The constructor attempts to read existing tasks from the file.
     *
     * @param filePath the path of the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        readTasks();
    }

    /**
     * Loads the list of tasks from storage.
     *
     * @return an ArrayList of Task objects that were read from the file.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Reads tasks from the file specified by filePath and populates the tasks list.
     * If the file does not exist, a NoFileException is thrown.
     *
     * @throws NoFileException if the file does not exist.
     */
    public void readTasks() {
        File file = new File(filePath);

        // Check if file exists, if not create a new file
        if (doesFileExist(file)) {
            return; // Exit as there is nothing to read yet
        }

        // Guard clause for handling IO exception
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            // Guard clause for empty content
            if (content.isBlank()) {
                return; // No tasks to process, so return early
            }
            extractContentIntoTasks(content);

        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }

    /**
     * Extracts tasks from the given content and adds them to the task list.
     * Each task is represented as a string in the provided content, and
     * the method converts these stringified tasks into Task objects.
     *
     * @param content The string content containing tasks separated by newlines.
     */
    private void extractContentIntoTasks(String content) {
        String[] parts = content.split("\n");

        for (String stringifiedTask : parts) {
            boolean isDone = stringifiedTask.charAt(4) == '1';
            Task taskToAdd = convertStringToTask(stringifiedTask, isDone);
            tasks.add(taskToAdd);
        }
    }

    /**
     * Checks if the given file exists. If the file does not exist,
     * it creates a new file at the specified file path.
     *
     * @param file The file to check for existence.
     * @return {@code true} if the file exists or has been successfully created,
     *         {@code false} if an error occurs during file creation.
     */
    private boolean doesFileExist(File file) {
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(filePath)); // Create the file if it doesn't exist
                System.out.println("File not found. A new file has been created: " + filePath);
                return true;
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a stringified task into a Task object based on its type (ToDo, Deadline, or Event).
     *
     * @param stringifiedTask the string representation of the task.
     * @param isDone whether the task is marked as done.
     * @return the corresponding Task object.
     */
    private static Task convertStringToTask(String stringifiedTask, boolean isDone) {
        String description = stringifiedTask.substring(8);
        Task taskToAdd = null;

        switch (stringifiedTask.charAt(0)) {

        case 'T':
            taskToAdd = new ToDo(description);
            break;

        case 'D':
            String deadlineDetails = description.split("/")[0];
            String by = description.split("/by")[1];

            taskToAdd = new Deadline(deadlineDetails, by);
            break;

        case 'E':
            String eventDetails = description.split("/from")[0];
            String from = description.split("/from")[1].split("/to")[0];
            String to = description.split("/from")[1].split("/to")[1];
            taskToAdd = new Event(eventDetails, from, to);

            break;

        default:
            System.out.println("Invalid type of task in .txt file");
        }

        if (isDone) {
            taskToAdd.setStatusIcon(true);
        } else {
            taskToAdd.setStatusIcon(false);
        }
        return taskToAdd;
    }

    /**
     * Writes the current list of tasks to the file specified by filePath.
     * If the file does not exist, a FileNotFoundException is thrown.
     */
    public void writeTasks() {
        File file = new File(filePath);

        // Guard clause for missing file
        if (!file.exists()) {
            System.out.println("Bro I can't find a file to store the data, \n"
                    + " can help lobang me and create a file pls");
            return;
        }

        // Guard clause for handling IO exception
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            StringBuilder formattedString = new StringBuilder();

            for (Task task : tasks) {
                formattedString.append(task.toPrettierString());
                formattedString.append("\n");
            }

            writer.write(formattedString.toString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
            e.printStackTrace();
        }
    }

}
