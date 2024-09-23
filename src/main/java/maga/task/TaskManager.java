package maga.task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import maga.exceptions.LoadTaskException;

/**
 * The {@code TaskManager} class handles the saving and loading of tasks to and from a file.
 * It is responsible for persisting the task list between sessions and retrieving tasks from a saved file.
 */
public class TaskManager {
    private static final String FILE_PATH = "./data/maga.txt";

    /**
     * Saves the tasks from the given {@code TaskList} to a file.
     * If the parent directory does not exist, it is created. The tasks are written to a file in the format
     * defined by each task's {@code toString()} method.
     *
     * @param taskList The {@code TaskList} containing the tasks to be saved.
     */
    public void saveTasks(TaskList taskList) {
        //create parent directory if it doesn't exist
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        //write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                writer.write(taskList.getTask(i).toString());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Error while saving tasks!");
        }
    }

    /**
     * Loads tasks from the saved file into a new {@code TaskList}.
     * If the file does not exist, a new empty {@code TaskList} is created and returned.
     * The tasks in the file are read and converted back into {@code Task} objects
     * using the {@code Task.fromString()} method.
     *
     * @return A {@code TaskList} containing the loaded tasks.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        File file = new File(FILE_PATH);

        // check if file exists
        if (!file.exists()) {
            // handle file not existing scenario
            System.out.println("No save detected: Creating fresh tasklist!");
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.addTask(Task.fromString(line));
            }
        } catch (IOException | LoadTaskException e) {
            System.out.println("Error while loading tasks!");
        }

        return tasks;
    }
}
