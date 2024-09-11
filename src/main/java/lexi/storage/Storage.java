package lexi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Task;
import lexi.task.Todo;

/**
 * Handles the loading and saving of tasks to and from a file.
 * This class manages the persistence of task data,
 * including reading tasks from a file and writing tasks back to the file.
 */
public class Storage {
    private final File dataFile;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a Storage object that handles the specified file.
     * Ensures that the file and its directory exist, creating them if necessary.
     *
     * @param filepath The path to the file used for storing tasks.
     * @throws LexiException If the directory or file cannot be created.
     */
    public Storage(String filepath) throws LexiException {
        assert filepath != null && !filepath.isEmpty() : "Filepath cannot be null or empty.";
        this.dataFile = new File(filepath);

        // Ensure the directory exists
        File directory = dataFile.getParentFile();
        if (directory != null && !directory.exists()) {
            if (!directory.mkdirs()) {
                throw new LexiException("Failed to create directory: " + directory.getAbsolutePath());
            }
        }
        // Ensure the file exists
        try {
            if (!dataFile.exists()) {
                boolean fileCreated = dataFile.createNewFile();
                assert fileCreated : "File creation failed despite no exception.";
                if (!fileCreated) {
                    throw new LexiException("Failed to create file: " + filepath);
                }
            }
        } catch (IOException e) {
            throw new LexiException("Failed to create file: " + filepath);
        }

        // Post-condition: Ensure that the file now exists
        assert dataFile.exists() : "Data file should exist after constructor completes.";
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return A list of tasks loaded from the file.
     * @throws LexiException If the file is not found or if there is an error reading from the file.
     */
    public ArrayList<Task> load() throws LexiException {
        assert dataFile.exists() : "Data file should exist before loading tasks.";

        try {
            Scanner contents = new Scanner(dataFile);
            while (contents.hasNextLine()) {
                String[] parts = contents.nextLine().split(" !- ");
                assert parts.length >= 3 : "Unexpected data format in the file.";

                String taskName = parts[2];
                String taskType = parts[0];

                switch (taskType) {
                case "T":
                    Todo todoTask = new Todo(taskName);
                    if (parts[1].equals("1")) {
                        todoTask.setDone(true);
                    }
                    tasks.add(todoTask);
                    break;
                case "D":
                    assert parts.length == 4 : "Deadline task should have exactly 4 parts.";
                    String deadline = parts[3];
                    LocalDateTime by = LocalDateTime.parse(deadline, this.inputFormatter);
                    Deadline deadlineTask = new Deadline(taskName, by);
                    if (parts[1].equals("1")) {
                        deadlineTask.setDone(true);
                    }
                    tasks.add(deadlineTask);
                    break;
                case "E":
                    assert parts.length == 5 : "Event task should have exactly 5 parts.";
                    String start = parts[3];
                    String end = parts[4];
                    LocalDateTime from = LocalDateTime.parse(start, this.inputFormatter);
                    LocalDateTime to = LocalDateTime.parse(end, this.inputFormatter);
                    Event eventTask = new Event(taskName, from, to);
                    if (parts[1].equals("1")) {
                        eventTask.setDone(true);
                    }
                    tasks.add(eventTask);
                    break;
                default:
                    throw new LexiException("Unknown task type: " + taskType);
                }
            }
        } catch (FileNotFoundException e) {
            throw new LexiException("File not found: " + dataFile.getAbsolutePath());
        }

        // Post-condition: Ensure tasks were loaded (if file was not empty)
        assert tasks != null : "Tasks list should not be null after loading.";
        return tasks;
    }

    /**
     * Updates the file with the current list of tasks.
     * Overwrites the file with the current task list.
     *
     * @param tasks The list of tasks to be saved to the file.
     * @throws LexiException If there is an error writing to the file.
     */
    public void updateStorage(ArrayList<Task> tasks) throws LexiException {
        assert tasks != null : "Tasks list cannot be null.";
        this.tasks = tasks;
        try {
            FileWriter fw = new FileWriter(dataFile);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

            for (Task task : tasks) {
                assert task != null : "Task should not be null when writing to storage.";

                char taskType = task.toString().split(" ")[0].charAt(1);
                if (taskType == 'T') {
                    fw.write(String.format("%c !- %d !- %s", taskType,
                            task.getIsDone() ? 1 : 0, task.getTaskName()) + System.lineSeparator());
                } else if (taskType == 'D') {
                    Deadline obj = (Deadline) task;
                    fw.write(String.format("%c !- %d !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(), obj.getBy().format(formatter))
                            + System.lineSeparator());
                } else if (taskType == 'E') {
                    Event obj = (Event) task;
                    fw.write(String.format("%c !- %d !- %s !- %s !- %s", taskType,
                            obj.getIsDone() ? 1 : 0, obj.getTaskName(),
                            obj.getFrom().format(formatter),
                            obj.getTo().format(formatter)) + System.lineSeparator());
                }
            }
            fw.close();

            // Post-condition: Ensure the file has been written
            assert dataFile.length() > 0 : "File should not be empty after updating storage.";
        } catch (IOException e) {
            throw new LexiException("Something went wrong with writing to data\n"
                    + "Please contact your system administrator!\n"
                    + "Error Message:\n"
                    + e.getMessage());
        }
    }
}
