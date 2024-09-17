package gutti;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
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
        createFileIfNotExists();
    }

    /**
     * Ensures the file and its directory exist, creating them if necessary.
     */
    private void createFileIfNotExists() {
        File file = new File(filePath);
        File directory = file.getParentFile();

        // Create directory if it doesn't exist
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directory.getPath());
            } else {
                System.out.println("Failed to create directory: " + directory.getPath());
            }
        }

        // Create file if it doesn't exist
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getPath());
                } else {
                    System.out.println("Failed to create file: " + file.getPath());
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
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
                writer.write(String.valueOf(task.completionDate));
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
                String tempLine = sc.nextLine();
                int index = tempLine.indexOf("[");
                String markedDateString = tempLine.substring(0,index);
                String line = tempLine.substring(index);
                if (line.isEmpty()) {
                    continue;
                }
                if (line.startsWith("[T]")) { // Todo list
                    TodoParse(taskList, line,markedDateString);
                } else if (line.startsWith("[E]")) {
                    EventParse(taskList, line,markedDateString);
                } else if (line.startsWith("[D]")) {
                    DeadLineParse(taskList, line,markedDateString);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void DeadLineParse(TaskList taskList, String line, String markedDateString) {
        int byIndex = line.indexOf("(by: ") + 5;
        int byEndIndex = line.indexOf(')');
        if (byIndex != -1) {
            String description = line.substring(6, line.indexOf("(by:")).trim();
            String by = line.substring(byIndex, byEndIndex).trim();
            boolean isDone = line.charAt(4) == 'X';
            if (markedDateString.equals("null")) {
                taskList.addTask(new Deadline(description, by, isDone));
            }
            else {
                LocalDateTime markedDate = LocalDateTime.parse(markedDateString);
                taskList.addTask(new Deadline(description,by,isDone,markedDate));
            }
        } else {
            System.out.println("Corrupted Deadline line: " + line);
        }
    }

    private static void EventParse(TaskList taskList, String line, String markedDateString) {
        int fromIndex = line.indexOf("(from: ");
        int toIndex = line.indexOf(" to: ");
        if (fromIndex != -1 && toIndex != -1) {
            String description = line.substring(6, fromIndex).trim();
            String from = line.substring(fromIndex + 6, toIndex).trim();
            String to = line.substring(toIndex + 4, line.length() - 1).trim();
            boolean isDone = line.charAt(4) == 'X';
            if (markedDateString.equals("null")) {
                taskList.addTask(new Event(description, from, to, isDone));
            } else {
                LocalDateTime markedDate = LocalDateTime.parse(markedDateString);
                taskList.addTask(new Event(description, from, to, isDone, markedDate));
            }
        } else {
            System.out.println("Corrupted Event line: " + line);
        }
    }

    private static void TodoParse(TaskList taskList, String line, String markedDateString) {
        String description = line.substring(6).trim();
        boolean isDone = line.charAt(4) == 'X';
        if (markedDateString.equals("null")) {
            taskList.addTask(new Todo(description, isDone));
        } else {
            LocalDateTime markedDate = LocalDateTime.parse(markedDateString);
            taskList.addTask(new Todo(description, isDone, markedDate));
        }
    }
}
