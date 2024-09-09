package weeny.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weeny.task.Task;
import weeny.task.Todo;
import weeny.task.Events;
import weeny.task.Deadlines;

/**
 * Handles reading from and writing to files, as well as creating necessary files and directories.
 */
public class Storage {

    /**
     * Creates a directory and file if they do not already exist.
     *
     * @param dir The directory path.
     * @param file The file name.
     */
    public void createFileIfNotExist(String dir, String file) {
        File dataDir = new File(dir);
        File taskFile = new File(dataDir, file);
        try {
            if (dataDir.mkdir()) {
                // Directory created successfully
                assert dataDir.exists() : "Directory should be created";
            }
            if (taskFile.createNewFile()) {
                // File created successfully
                assert taskFile.exists() : "Task file should be created";
            }
        } catch (IOException e) {
            System.err.println("Error creating directory or file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from a file and returns them as a list.
     *
     * @param fileName The path to the file.
     * @return A list of tasks read from the file.
     */
    public List<Task> loadTask(String fileName) {
        List<Task> taskList = new ArrayList<>();
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(
                    Paths.get(fileName),
                    StandardCharsets.UTF_8);
            assert !lines.isEmpty() : "File should contain tasks";
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] processTask = line.split(" \\| ");
            String description = processTask[2];
            Task currentTask = null;

            switch (processTask[0]) {
            case "T":
                currentTask = new Todo(description);
                break;
            case "D":
                currentTask = new Deadlines(description, processTask[3]);
                break;
            case "E":
                int split = processTask[3].indexOf('-');
                assert split != -1 : "Event time format should contain a dash (-)";
                if (split == -1) {
                    throw new IllegalArgumentException("Invalid event time format: " + processTask[3]);
                }
                String startDatestring = processTask[3].substring(0, split).trim();
                String endDatestring = processTask[3].substring(split + 1).trim();
                currentTask = new Events(description, startDatestring, endDatestring);
                break;
            default:
                // Invalid task type
                throw new IllegalArgumentException("Unknown task type: " + processTask[0]);
            }

            if (Integer.parseInt(processTask[1]) == 1) {
                currentTask.setAsDone();
            }
            taskList.add(currentTask);
        }
        return taskList;
    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param path The path to the file.
     * @param tasks The list of tasks to save.
     */
    public void saveTask(String path, List<Task> tasks) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            for (Task task : tasks) {
                fileWriter.write(task.toOutput() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
