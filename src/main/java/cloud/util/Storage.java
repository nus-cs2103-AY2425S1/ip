package cloud.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cloud.exception.CloudException;
import cloud.task.Deadline;
import cloud.task.Event;
import cloud.task.Task;
import cloud.task.Todo;


/**
 * Represents the data storage for the chatbot.
 * This class is responsible for reading from and writing to the storage file.
 */
public class Storage {
    private final String filePath;
    private final String DELIMITER = "|||$DELIM|||";

    /**
     * Constructs a Storage object. Checks if the file exists at the filepath
     * and creates a new file if it does not exist yet
     */
    public Storage() {
        this.filePath = "./data/Cloud.txt";
        initializeStorage();
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        initializeStorage();
    }

    private void initializeStorage() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error initializing storage!");
        }
    }

    /**
     * Saves the given TaskList to the storage file.
     *
     * @param taskList the TaskList to be saved
     */
    public void saveData(TaskList taskList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                writer.write(taskList.getTask(i).formatSave());
            }
        } catch (IOException e) {
            System.out.println("Error writing to save file!");
        }
    }

    /**
     * Reads data from the storage file and returns a TaskList.
     *
     * @return TaskList containing tasks read from the file
     */
    public TaskList readData() {
        List<String> lines = readLinesFromFile();
        return createTaskListFromLines(lines);
    }

    /**
     * Reads all lines from the storage file.
     *
     * @return List of Strings, each representing a line from the file
     */
    private List<String> readLinesFromFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from save file!");
        }
        return lines;
    }

    /**
     * Creates a TaskList from a list of string lines.
     *
     * @param lines List of Strings, each representing a task
     * @return TaskList containing tasks created from the lines
     */
    private TaskList createTaskListFromLines(List<String> lines) {
        TaskList taskList = new TaskList();
        for (String line : lines) {
            Task task = createTaskFromLine(line);
            if (task != null) {
                taskList.add(task);
            }
        }
        return taskList;
    }

    /**
     * Creates a Task object from a single line of text.
     *
     * @param line String representing a task
     * @return Task object created from the line, or null if creation fails
     */
    private Task createTaskFromLine(String line) {
        String[] splits = line.split(" \\s*\\|\\s* ");
        if (splits.length < 3) {
            return null;
        }

        String taskType = splits[0];
        boolean isDone = "1".equals(splits[1]);
        String description = splits[2];

        Task task = createTaskByType(taskType, description, splits);
        if (task != null && isDone) {
            task.markDone();
        }
        return task;
    }

    /**
     * Creates a specific Task subclass based on the task type.
     *
     * @param taskType String representing the type of task
     * @param description String description of the task
     * @param splits Array of Strings containing additional task information
     * @return Task object of the appropriate subclass, or null if creation fails
     */
    private Task createTaskByType(String taskType, String description, String[] splits) {
        try {
            switch (taskType) {
            case "T":
                return new Todo(description);
            case "D":
                return new Deadline(description, DateTime.of(splits[3]));
            case "E":
                return new Event(description, DateTime.of(splits[3]), DateTime.of(splits[4]));
            default:
                return null;
            }
        } catch (CloudException e) {
            System.out.println("Error reading from file");
            return null;
        }
    }
}
