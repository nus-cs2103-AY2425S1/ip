package Gutti;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class handles loading and saving of tasks to a file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new {@code Storage} instance with the specified file path.
     *
     * @param filePath The path of the file where tasks will be stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks to the specified file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the specified file into the provided {@code TaskList}.
     *
     * @param taskList The task list to load the tasks into.
     */
    public void loadTasksFromFile(TaskList taskList) {
        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("[T]")) { // Todo list
                    String description = line.substring(6).trim();
                    boolean isDone = line.charAt(4) == 'X';
                    taskList.addTask(new Todo(description, isDone));
                } else if (line.startsWith("[E]")) {
                    int fromIndex = line.indexOf("(from: ");
                    int toIndex = line.indexOf(" to: ");
                    if (fromIndex != -1 && toIndex != -1) {
                        String description = line.substring(6, fromIndex).trim();
                        String from = line.substring(fromIndex + 6, toIndex).trim();
                        String to = line.substring(toIndex + 4, line.length() - 1).trim();
                        boolean isDone = line.charAt(4) == 'X';
                        taskList.addTask(new Event(description, from, to, isDone));
                    } else {
                        System.out.println("Corrupted Event line: " + line);
                    }
                } else if (line.startsWith("[D]")) {
                    int byIndex = line.indexOf("(by: ") + 5;
                    int byEndIndex = line.indexOf(')');
                    if (byIndex != -1) {
                        String description = line.substring(6, line.indexOf("(by:")).trim();
                        String by = line.substring(byIndex, byEndIndex).trim();
                        boolean isDone = line.charAt(4) == 'X';
                        taskList.addTask(new Deadline(description, by, isDone));
                    } else {
                        System.out.println("Corrupted Deadline line: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}