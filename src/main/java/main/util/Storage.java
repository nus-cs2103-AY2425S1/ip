package main.util;

// deals with loading tasks from the file and saving tasks in the file

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import main.exceptions.PrinceException;
import main.tasks.Deadline;
import main.tasks.Event;
import main.tasks.Task;
import main.tasks.TaskList;
import main.tasks.Todo;

/**
 * Storage handles the reading and writing of user input to text files.
 */
public class Storage {

    private static final String TASK_TYPE_TODO = "T";
    private static final String TASK_TYPE_DEADLINE = "D";
    private static final String TASK_TYPE_EVENT = "E";
    private static final String STATUS_DONE = "1";
    private static final String FIELD_SEPARATOR = " .. ";

    private static Path filePath;

    /**
     * A constructor for the storage class.
     */
    public Storage(Path path) {
        filePath = path;
    }

    /**
     * Returns a TaskList that is read from storage.
     * If storage is empty, then it returns an empty TaskList.
     * @return A TaskList.
     */
    public TaskList loadFromFile() throws PrinceException {
        TaskList taskList = new TaskList();
        try {
            ensureFileExists();
            List<String> lines = Files.readAllLines(filePath);
            for (String stringTask : lines) {
                Task task = parseTaskFromLine(stringTask);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new PrinceException(e.getMessage());
        }
        return taskList;
    }

    /**
     * Parses a line from the file into a Task object.
     * @param line The line read from the file.
     * @return A Task object or null if the line format is invalid.
     */
    private Task parseTaskFromLine(String line) {
        String[] fields = line.split(FIELD_SEPARATOR);
        if (fields.length < 3) {
            return null;
        }

        String taskType = fields[0].trim();
        String status = fields[1].trim();
        String description = fields[2].trim();
        String byFrom = fields.length > 3 ? fields[3].trim() : "";
        String to = fields.length > 4 ? fields[4].trim() : "";

        Task task;
        switch (taskType) {
        case TASK_TYPE_TODO:
            task = new Todo(description);
            break;
        case TASK_TYPE_DEADLINE:
            task = new Deadline(description, byFrom);
            break;
        case TASK_TYPE_EVENT:
            task = new Event(description, byFrom, to);
            break;
        default:
            task = null;
            break;
        };

        if (task != null && STATUS_DONE.equals(status)) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves newly created tasks by writing to storage.
     * @param task Task that is created based on user input.
     * @param taskList List of tasks.
     */
    public void saveToFile(Task task, TaskList taskList) {
        try {
            ensureFileExists();
            BufferedWriter bw = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND);
            String taskString = task.toFileFormat();
            bw.write(taskString);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the task from storage.
     * @param input Input of the user.
     * @param taskList List of tasks.
     */
    public void deleteFromFile(String input, TaskList taskList) {
        try {
            int index = getIndex(input);
            if (index == -1) {
                return;
            }
            Task task = taskList.get(index);
            // open the old file for reading
            BufferedReader reader = Files.newBufferedReader(filePath);
            // open a new (temporary) file for writing
            Path tempPath = Paths.get("data", "temp.txt");
            BufferedWriter writer = Files.newBufferedWriter(tempPath);
            // iterate over the lines in the old file (probably using a BufferedReader)
            String lineToRemove = task.toFileFormat();
            String currLine;
            // for each line, check if it matches what you are supposed to remove
            while ((currLine = reader.readLine()) != null) {
                if (currLine.equals(lineToRemove)) {
                    continue;
                }
                // if it doesn't match, write it to the temporary file
                writer.write(currLine);
                writer.newLine();
            }
            reader.close();
            writer.close();

            // delete the old file
            Files.delete(filePath);
            // move temp file to old file path
            Files.move(tempPath, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates storage when user marks or unmarks tasks.
     * @param input Input of the user.
     * @param taskList List of tasks.
     */
    public void updateFile(String input, TaskList taskList) {
        try {
            int index = getIndex(input);
            Task task = taskList.get(index);
            String desc = task.getDescription();
            // open the old file for reading
            BufferedReader reader = Files.newBufferedReader(filePath);
            // open a new (temporary) file for writing
            Path tempPath = Paths.get("data", "temp.txt");
            BufferedWriter writer = Files.newBufferedWriter(tempPath);
            String currLine;
            // for each line, check if it matches what you are supposed to remove
            while ((currLine = reader.readLine()) != null) {
                if (currLine.contains(desc)) {
                    String updatedLine = task.toFileFormat();
                    writer.write(updatedLine);
                } else {
                    writer.write(currLine);
                }
                writer.newLine();
            }
            reader.close();
            writer.close();

            // delete the old file
            Files.delete(filePath);
            // move temp file to old file path
            Files.move(tempPath, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an integer representing the index of a task in an array.
     * @param input Input by the user.
     * @return Index of the task.
     */
    private int getIndex(String input) {
        String indexAsString = "";
        if (input.contains("unmark")) {
            indexAsString = input.substring(7);
        }
        if (input.contains("mark")) {
            indexAsString = input.substring(5);
        }
        if (input.contains("delete")) {
            indexAsString = input.substring(7);
        }
        if (indexAsString.equals("")) {
            return -1;
        }
        int index = Integer.valueOf(indexAsString) - 1;
        return index;
    }

    /**
     * Ensures that the storage file and its directories exist.
     * @throws IOException
     */
    private void ensureFileExists() throws IOException {
        if (Files.notExists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }
    }
}
