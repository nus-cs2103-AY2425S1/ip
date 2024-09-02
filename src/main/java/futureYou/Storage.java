package futureYou;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import futureYou.TaskList;
import futureYou.task.Deadline;
import futureYou.task.Events;
import futureYou.task.Task;

/**
 * The Storage class handles loading tasks from a file and saving tasks to a
 * file.
 * The file format is: <Task type>|<Done?>|<Task Name>|<Deadline>|<End Date>
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with a specified file path.
     *
     * @param filePath The path to the file storing the task list.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        initializeFile(filePath);
    }

    /**
     * Ensures the file at the specified file path exists.
     * If the file does not exist, it creates the necessary parent directories and
     * the file.
     * If the file already exists, no action is taken.
     */
    private void initializeFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) { // Check if the file does not exist
            try {
                // Ensure the parent directories are created
                if (file.getParentFile() != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    /**
     * Parses a task from a string representation and returns the corresponding task
     * object.
     *
     * @param taskString The string representation of the task.
     * @return The task object parsed from the string.
     */
    private Task parseTask(String taskString) {
        String[] stringParts = taskString.trim().split(" \\| ");
        String typeOfTask = stringParts[0].trim();
        String taskName = stringParts[1].trim();
        boolean taskStatus = stringParts[2].trim().equals("1");

        switch (typeOfTask) {
            case "T":
                return new Task(taskName, taskStatus);
            case "D":
                String deadline = stringParts[3].trim();
                return new Deadline(taskName, taskStatus, deadline);
            case "E":
                String startDateTime = stringParts[3].trim();
                String endDateTime = stringParts[4].trim();
                return new Events(taskName, taskStatus, startDateTime, endDateTime);
            default:
                return null;
        }
    }

    /**
     * Loads tasks from the file into a list. If the file does not exist, it creates
     * a new file.
     *
     * @return The list of tasks read from the file, if file is empty return emtpy ArrayList<Task>.
     * @throws IOException If there is an error reading from the file.
     */
    public ArrayList<Task> loadTasks() throws IOException {
        File file = new File(filePath);

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                Task task = parseTask(taskData);
                if (task != null) {
                    TaskList.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found when reading tasks: " + e.getMessage(), e);
        }

        // If file is empty
        return new ArrayList<Task>();
    }

    /**
     * Saves the current list of tasks to a file.
     *
     * @param taskList The TaskList containing the tasks to be saved.
     */
    public void saveTasks() {
        File file = new File(this.filePath);
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : TaskList.getTaskList()) {
                writer.write(task.toString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving the task list: " + e.getMessage());
        }
    }
}
