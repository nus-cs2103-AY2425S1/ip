package util;

import task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * The ListMapWriter class provides functionality to write a map of tasks to a file.
 * This class handles the serialization of tasks from a map structure into a plain text file.
 */
public class ListMapWriter {

    /**
     * Writes the provided map of tasks to the specified file.
     * Each task is written on a new line in the file.
     *
     * @param taskMap  A map where the key is a string representing the task name and the value is a Task object.
     * @param filePath The path to the file where the tasks will be written.
     */
    public void writeMapToFile(Map<String, Task> taskMap, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : taskMap.values()) {
                writer.write(task.toString());  // Write the string representation of the task to the file
                writer.newLine();  // Write a new line after each task
            }
        } catch (IOException e) {
            e.printStackTrace();  // Print stack trace if an IOException occurs
        }
    }
}
