package myapp.helperbuddy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class which loads and saves task from the specified file
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into the provided list.
     *
     * @param taskList The list to which tasks will be added.
     */
    public void loadTasks(ArrayList<Task> taskList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task;
                if (line.startsWith("T")) {
                    task = ToDo.parseTask(line);
                } else if (line.startsWith("D")) {
                    task = Deadline.parseTask(line);
                } else if (line.startsWith("E")) {
                    task = Event.parseTask(line);
                } else {
                    throw new IllegalArgumentException("Unknown task type");
                }
                taskList.add(task);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file.");
        }
    }

    /**
     * Stores tasks from the current task list into the provided file.
     *
     * @param taskList The list to which tasks will be copied and saved into the file
     */
    public void saveTasks(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }
}
