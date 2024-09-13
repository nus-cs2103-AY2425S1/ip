package gutti;

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
        assert tasks != null : "Task list cannot be null";
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
                    TodoParse(taskList, line);
                } else if (line.startsWith("[E]")) {
                    EventParse(taskList, line);
                } else if (line.startsWith("[D]")) {
                    DeadLineParse(taskList, line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void DeadLineParse(TaskList taskList, String line) {
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

    private static void EventParse(TaskList taskList, String line) {
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
    }

    private static void TodoParse(TaskList taskList, String line) {
        String description = line.substring(6).trim();
        boolean isDone = line.charAt(4) == 'X';
        taskList.addTask(new Todo(description, isDone));
    }
}
