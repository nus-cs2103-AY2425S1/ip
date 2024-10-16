package michaelscott.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import michaelscott.task.Task;
import michaelscott.task.TaskList;
import michaelscott.task.TaskParser;

/**
 * Handles storage of tasks, including reading and loading data from file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a storage class.
     */
    public Storage(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        try {
            Files.createDirectories(Paths.get("./userdata"));
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
        this.filePath = "./userdata/tasks.txt";
        loadTasks(taskList);
    }

    /**
     * Reads the task from file and updates the task list.
     *
     * @param todo the task list.
     */
    public void saveTasks(ArrayList<Task> todo) throws MichaelScottException {
        assert todo != null : "Task list cannot be null";

        File file = new File(this.filePath);
        FileWriter fw;

        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            for (int i = 0; i < todo.toArray().length; i++) {
                assert todo.get(i) != null : "Task at index " + i + " is null";
                fw.write(todo.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new MichaelScottException("Error saving data: " + e.getMessage());
        }
    }

    /**
     * This function reads the userdata file and loads the task.
     * Parses the file and constructs the appropriate classes.
     */
    public void loadTasks(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        File file = new File(this.filePath);
        Scanner sc;
        String line;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            sc = new Scanner(file);
            while (sc.hasNext()) {
                line = sc.nextLine();
                assert line != null && !line.isEmpty() : "Invalid line read from file";
                Task task = TaskParser.parseTask(line);
                assert task != null : "Failed to parse task from line: " + line;
                taskList.addTask(task);
            }
            sc.close();
        } catch (IOException | MichaelScottException e) {
            Ui.showError(e.getMessage());
        }
    }
}
