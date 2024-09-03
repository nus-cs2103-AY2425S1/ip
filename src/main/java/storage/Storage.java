package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import exceptions.BrockException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Class to create, and interact with save file.
 */
public class Storage {
    private static final String FILE_PATH = "./src/main/java/saveFile.txt";
    private static final File SAVE_FILE = new File(FILE_PATH);

    /**
     * Removes closing bracket from the body string of each task.
     * So that the correct date or time can be extracted for deadline and event tasks.
     *
     * @param target String fragment with closing bracket to be removed.
     * @return String without the closing bracket.
     */
    private String removeCloseBracket(String target) {
        int length = target.length();
        // Substring from start to the second last index
        return target.substring(0, length - 1);
    }

    /**
     * Parses date from "MMM dd yyyy format" into "yyyy-mm-dd" format.
     *
     * @param targetDate Date string to be parsed.
     * @return Parsed date string.
     */
    private String parseDate(String targetDate) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return LocalDate.parse(targetDate, dateFormatter)
                .toString();
    }

    /**
     * Creates a {@code ToDo} object corresponding to a todo task.
     *
     * @param taskBody String storing task description.
     * @param taskStatus Character representing task status.
     * @return {@code ToDo} object created.
     */
    private Task handleToDo(String taskBody, char taskStatus) {
        Task todoTask = new ToDo(taskBody);
        if (taskStatus == 'X') {
            todoTask.markAsDone();
        }
        return todoTask;
    }

    /**
     * Creates a {@code Deadline} object corresponding to a deadline task.
     *
     * @param taskBody String storing task description and due datetime.
     * @param taskStatus Character representing task status.
     * @return {@code Deadline} object created.
     * @throws BrockException If date and time are invalid when constructing object.
     *      (They should already be validated)
     */
    private Task handleDeadline(String taskBody, char taskStatus) throws BrockException {
        String[] parts = taskBody.split(" \\(by: ", 2);
        String description = parts[0];

        String dateTime = parts[1];
        String[] dateTimeParts = dateTime.split(", ");

        String dueDateString = dateTimeParts.length == 1
                ? parseDate(removeCloseBracket(dateTimeParts[0]))
                : parseDate(dateTimeParts[0]);
        String dueTimeString = dateTimeParts.length == 1
                ? ""
                : removeCloseBracket(dateTimeParts[1])
                .replace(":", "");

        Task deadlineTask;
        if (dueTimeString.isEmpty()) {
            deadlineTask = new Deadline(description, dueDateString);
        } else {
            deadlineTask = new Deadline(description, dueDateString, dueTimeString);
        }
        if (taskStatus == 'X') {
            deadlineTask.markAsDone();
        }
        return deadlineTask;
    }

    /**
     * Creates an {code Event} object corresponding to an event task.
     *
     * @param taskBody String storing task description, as well as start and end datetime.
     * @param taskStatus Character representing task status.
     * @return {@code Event} object created.
     * @throws BrockException If date and time are invalid when constructing object.
     *      (They should already be validated)
     */
    private Task handleEvent(String taskBody, char taskStatus) throws BrockException {
        String[] parts = taskBody.split(" \\(from: ", 2);
        String description = parts[0];

        String dateTime = parts[1];
        String[] dateTimeParts = dateTime.split(" \\| ", 2);
        String startDateTime = dateTimeParts[0];
        String endDateTime = dateTimeParts[1];

        String[] startDateTimeParts = startDateTime.split(", ");
        String[] endDateTimeParts = endDateTime.substring(4)
                .split(", ");

        String startDateString = parseDate(startDateTimeParts[0]);
        String startTimeString = startDateTimeParts.length == 1
                ? ""
                : startDateTimeParts[1].replace(":", "");
        String endDateString = endDateTimeParts.length == 1
                ? parseDate(removeCloseBracket(endDateTimeParts[0]))
                : parseDate(endDateTimeParts[0]);
        String endTimeString = endDateTimeParts.length == 1
                ? ""
                : removeCloseBracket(endDateTimeParts[1])
                .replace(":", "");

        Task eventTask;
        if (startTimeString.isEmpty()) {
            eventTask = new Event(description, startDateString, endDateString);
        } else {
            eventTask = new Event(description, startDateString, startTimeString,
                    endDateString, endTimeString);
        }
        if (taskStatus == 'X') {
            eventTask.markAsDone();
        }
        return eventTask;
    }

    /**
     * Converts a task string into a corresponding {@code Task} object.
     *
     * @param taskString Task string being passed in.
     * @return Corresponding {@code Task} object.
     * @throws BrockException If task string is invalid.
     */
    private Task convertToTaskObject(String taskString) throws BrockException {
        // Split by ". "
        String[] taskComponents = taskString.split("\\. ", 2);

        String taskDetails = taskComponents[1];
        char taskType = taskDetails.charAt(1);
        char taskStatus = taskDetails.charAt(4);
        // Remove the [<type>][<status>]
        String taskBody = taskDetails.substring(7);

        return switch (taskType) {
            case 'T' -> handleToDo(taskBody, taskStatus);
            case 'D' -> handleDeadline(taskBody, taskStatus);
            case 'E' -> handleEvent(taskBody, taskStatus);
            default -> throw new BrockException("Unrecognized task type!");
        };
    }

    /**
     * Converts all tasks in save file into corresponding {@code Task} objects.
     *
     * @return An {@code ArrayList<Task>} to store all objects.
     * @throws BrockException If unable to find save file.
     */
    public ArrayList<Task> loadTasksFromFile() throws BrockException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner s = new Scanner(SAVE_FILE);
            while (s.hasNext()) {
                String taskString = s.nextLine();
                Task task = convertToTaskObject(taskString);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new BrockException("Unable to find and read from save file!");
        }
    }

    /**
     * Checks if the save file and parent directories are present.
     * If they are not, create them accordingly.
     *
     * @return {@code String[]} of size 2.
     *      First element is directory result, second element is file result.
     * @throws IOException If there were issues creating the files and folders.
     */
    public String[] createFile() throws IOException {
        StringBuilder dirResult = new StringBuilder();
        String dirStatus;
        boolean isDirectoryMissing = SAVE_FILE.getParentFile().mkdirs();
        if (!isDirectoryMissing) {
            dirStatus = "Parent directories already exists!";
        } else {
            dirStatus = "Parent directories successfully created!";
        }
        dirResult.append("Creating parent directories for save file...\n")
                .append(dirStatus);

        StringBuilder fileResult = new StringBuilder();
        String fileStatus;
        boolean isFileMissing = SAVE_FILE.createNewFile();
        if (!isFileMissing) {
            fileStatus = "Save file already exists!";
        } else {
            fileStatus = "Save file successfully created";
        }
        fileResult.append("Creating save file for tasks...\n")
                .append(fileStatus);

        return new String[] {dirResult.toString(),
                fileResult.toString()};
    }

    /**
     * Writes to the save file, to update it when there are new tasks or deleted tasks.
     *
     * @param writeContent The task to be written.
     * @param isAppendMode Option to append to existing content, or overwrite existing content.
     * @throws BrockException If there are issues with writing to the file.
     */
    public void writeToFile(String writeContent, boolean isAppendMode) throws BrockException {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, isAppendMode);
            fw.write(writeContent);
            fw.close();
        } catch (IOException e) {
            throw new BrockException(e.getMessage());
        }
    }
}
