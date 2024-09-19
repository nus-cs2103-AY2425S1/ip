package ynch.ui;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

import java.util.ArrayList;

/**
 * Handles the storage and retrieval of tasks from a specified file.
 */
class Storage {
    String filename;
    File file;
    Path filePath;
    
    /**
     * Constructs a Storage object with the specified file path and filename.
     *
     * @param filePath the directory path where the file is located
     * @param filename the name of the file to be used for storage
     */
    Storage(String filePath, String filename) {
        File file = new File(filename);
        this.filePath = Paths.get(filePath, filename);;
    }

    /**
     * Loads the task history from the specified file.
     *
     * @return a TaskList containing the tasks loaded from the file
     */
    TaskList load() {
        ArrayList<Task> taskHistory = new ArrayList<Task>();

        if (Files.exists(this.filePath)) {
            try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath.toFile()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    taskHistory.add(this.stringToTask(line));
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
            }
        }

        return new TaskList(taskHistory);
    }

    /**
     * Saves the current task history to the specified file.
     *
     * @param taskHistory the TaskList containing tasks to be saved
     */
    void save(TaskList taskHistory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile()))) {
            writer.write(taskHistory.toSaveAsString());
            // System.out.println("Task history saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving file: " + e.getMessage());
        }
    }

    /**
     * Converts a string representation of a task into a Task object.
     *
     * @param string the string representation of the task
     * @return the corresponding Task object
     */
    Task stringToTask(String string) {
        // string will be in the format "D1/task description/[deadline or from]/[to]"
        String taskType = String.valueOf(string.charAt(0));
        int status = Integer.parseInt(String.valueOf(string.charAt(1)));
        String[] task = string.split("/");
        return switch (taskType) {
            case "T" -> new Todo(status, task[1]);
            case "D" -> new Deadline(status, task[1], task[2]);
            case "E" -> new Event(status, task[1], task[2], task[3]);
            default -> new Task(string);
        };
    }
   
}
