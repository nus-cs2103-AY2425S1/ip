package calebyyy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import calebyyy.tasks.Deadline;
import calebyyy.tasks.Event;
import calebyyy.tasks.Task;
import calebyyy.tasks.Todo;

/**
 * Represents the storage of the application.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath The file path to store the tasks.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @param tasklist The task list to load the tasks into.
     */
    void loadTasks(TaskList tasklist) {
        assert tasklist != null : "TaskList cannot be null";

        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    addTaskToList(tasklist, task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line The line to parse.
     * @return The Task object.
     */
    private Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        Task task = null;

        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String by = parts[3];
            task = new Deadline(description, by);
            break;
        case "E":
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
            break;
        default:
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param tasklist The task list to add the task to.
     * @param task The task to add.
     */
    private void addTaskToList(TaskList tasklist, Task task) {
        try {
            tasklist.addTask(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves tasks to the file.
     *
     * @param taskList The task list to save the tasks from.
     */
    public void saveTasks(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";

        File file = new File(filePath);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writeTasksToFile(taskList, writer);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Writes tasks to the file.
     *
     * @param taskList The task list to write the tasks from.
     * @param writer The BufferedWriter to write the tasks.
     * @throws IOException If an I/O error occurs.
     */
    private void writeTasksToFile(TaskList taskList, BufferedWriter writer) throws IOException {
        for (Task task : taskList.getTasks()) {
            writer.write(task.toSaveFormat());
            writer.newLine();
        }
    }
}
