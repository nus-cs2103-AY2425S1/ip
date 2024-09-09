package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.TaskList;


/**
 * The Storage class is responsible for loading tasks from and saving tasks to a file.
 * It handles reading from and writing to the specified file path where tasks are stored.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the specified file.
     * If the file does not exist, it creates a new empty file and returns an empty task list.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException If an I/O error occurs while loading the tasks.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
            return tasks;
        }
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNext()) {
                String taskLine = fileReader.nextLine();
                Task task = Parser.parseTask(taskLine);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the list of tasks to the specified file.
     * The file is overwritten with the current list of tasks.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     * @throws IOException If an I/O error occurs while saving the tasks.
     */
    public void save(TaskList tasks) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toFileString() + "\n");
            }
        }
    }
}
