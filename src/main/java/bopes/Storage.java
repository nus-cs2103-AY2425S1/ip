package bopes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import bopes.exception.BopesException;
import bopes.task.Task;
import bopes.task.TaskList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws BopesException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // Return an empty list if the file does not exist
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = Parser.parseTask(taskData);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new BopesException("Error loading tasks from file: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(TaskList tasks) throws BopesException {
        // Ensure the directory exists before trying to save
        File directory = new File(filePath).getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks.getTasks()) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new BopesException("Error saving tasks to file: " + e.getMessage());
        }
    }
}
