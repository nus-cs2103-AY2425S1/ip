package util;

// deals with loading tasks from the file and saving tasks in the file

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import tasks.Todo;

/**
 * Storage handles the reading and writing of user input to text files.
 */
public class Storage {

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
    public TaskList loadFromFile() {
        TaskList taskList = new TaskList();

        try {
            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

            // read from file path
            List<String> lines = Files.readAllLines(filePath);

            // for each line, need to splice it according to the format
            // create new tasks in taskarray based on each line
            for (String stringTask : lines) {
                String[] arr = stringTask.split(" .. ");
                String taskType = "";
                String description = "";
                String status = "";
                String byFrom = "";
                String to = "";
                // for each line in the file, splice and extract relevant fields
                for (int i = 0; i < arr.length; i++) {
                    if (arr[i] != null) {
                        String trimmed = arr[i].trim();
                        if (i == 0) {
                            taskType = trimmed;
                        }
                        if (i == 1) {
                            status = trimmed;
                        }
                        if (i == 2) {
                            description = trimmed;
                        }
                        if (i == 3) {
                            byFrom = trimmed;
                        }
                        if (i == 4) {
                            to = trimmed;
                        }
                    }
                }

                // create new task based on each line in the file
                if (taskType.equals("T")) {
                    Task todoTask = new Todo(description);
                    taskList.add(todoTask);
                    if (status.equals("1")) {
                        todoTask.markAsDone();
                    }
                }
                if (taskType.equals("D")) {
                    Task deadlineTask = new Deadline(description, byFrom);
                    taskList.add(deadlineTask);
                    if (status.equals("1")) {
                        deadlineTask.markAsDone();
                    }
                }
                if (taskType.equals("E")) {
                    Task eventTask = new Event(description, byFrom, to);
                    taskList.add(eventTask);
                    if (status.equals("1")) {
                        eventTask.markAsDone();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Saves newly created tasks by writing to storage.
     * @param task Task that is created based on user input.
     * @param taskList List of tasks.
     */
    public void saveToFile(Task task, TaskList taskList) {
        try {

            if (Files.notExists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

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

            // close both files
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
                    // if it matches, update the status part of the line
                    // mark the task as done or not done
                    // use the to file format
                    // update there
                    String updatedLine = task.toFileFormat();
                    writer.write(updatedLine);
                    writer.newLine();
                } else {
                    // if it doesn't match, write it normally
                    writer.write(currLine);
                    writer.newLine();
                }
            }

            // close both files
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
        if (input.contains("unmark")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("mark")) {
            // get character value of index in input
            String indexAsString = input.substring(5);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else if (input.contains("delete")) {
            // get character value of index in the input
            String indexAsString = input.substring(7);
            // convert to arr index
            int index = Integer.valueOf(indexAsString) - 1;
            return index;
        } else {
            // should not reach here
            return -1;
        }
    }

}
