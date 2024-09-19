package garfield.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import garfield.exceptions.GarfieldException;
import garfield.tasks.Deadline;
import garfield.tasks.Event;
import garfield.tasks.Task;
import garfield.tasks.TaskList;
import garfield.tasks.Todo;

/**
 * The Storage class represents the local storage where tasks are being saved so
 * that they persist even after the chatbot is exited. It contains methods to
 * load and save tasks from and into a .txt file for local storage.
 */
public class Storage {
    private final String saveFilePath;

    /**
     * Constructs a new storage object with the specified save file path.
     *
     * @param saveFilePath Path to the save .txt file.
     */
    public Storage(String saveFilePath) {
        assert saveFilePath != null : "Save file path cannot be null";
        assert !saveFilePath.isEmpty() : "Save file path cannot be empty";
        this.saveFilePath = saveFilePath;
    }

    /**
     * Loads the tasks in the save .txt file into memory.
     *
     * @return ArrayList of Tasks which have been loaded from the persistent save file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File savedFile = new File(saveFilePath);
        try (Scanner fileScanner = new Scanner(savedFile)) {
            while (fileScanner.hasNext()) {
                String savedTask = fileScanner.nextLine();
                Task task = createTaskFromDetails(savedTask);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // If no file is found, return an empty list.
        } catch (GarfieldException e) {
            throw new RuntimeException(e); // Convert checked exceptions to unchecked.
        }
        return taskList;
    }

    /**
     * Saves the inputted taskList into the persistent save .txt file.
     *
     * @param taskList ArrayList of Tasks to be saved.
     */
    public void save(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";
        ArrayList<Task> internalTasks = taskList.getArrayList();
        try (FileWriter fw = new FileWriter(this.saveFilePath)) {
            fw.write(convertTasksToString(internalTasks));
        } catch (IOException e) {
            // You can throw a GarfieldException or handle this in a more specific way.
        }
    }

    /**
     * Parses a line from the save file and creates the corresponding Task.
     *
     * @param savedTask The line from the save file.
     * @return The corresponding Task object.
     * @throws GarfieldException If there's an error during task creation.
     */
    private Task createTaskFromDetails(String savedTask) throws GarfieldException {
        String[] taskDetails = savedTask.split(" \\| ");
        assert taskDetails.length >= 3 : "Invalid task format in save file";
        String taskType = taskDetails[0];
        boolean isDone = taskDetails[1].equals("1");

        Task task;
        switch (taskType) {
        case "T":
            task = parseTodoTask(taskDetails);
            break;
        case "D":
            task = parseDeadlineTask(taskDetails);
            break;
        case "E":
            task = parseEventTask(taskDetails);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }

        if (isDone) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }

        return task;
    }

    /**
     * Converts the task list into a string suitable for saving to a file.
     *
     * @param tasks The list of tasks to convert.
     * @return The string representation of all tasks.
     */
    private String convertTasksToString(ArrayList<Task> tasks) {
        StringBuilder textToWrite = new StringBuilder();
        String prefix = "";

        for (Task t : tasks) {
            textToWrite.append(prefix).append(t.toSaveRepresentation());
            prefix = System.lineSeparator(); // Ensure new line after each task.
        }

        return textToWrite.toString();
    }

    /**
     * Parses a todo task from the task details array.
     *
     * @param taskDetails The array containing task details.
     * @return The Todo task.
     */
    private Todo parseTodoTask(String[] taskDetails) {
        return new Todo(taskDetails[2]);
    }

    /**
     * Parses a deadline task from the task details array.
     *
     * @param taskDetails The array containing task details.
     * @return The Deadline task.
     * @throws GarfieldException If there's an error during date parsing.
     */
    private Deadline parseDeadlineTask(String[] taskDetails) throws GarfieldException {
        return new Deadline(taskDetails[2], this.parseDateTime(taskDetails[3]));
    }

    /**
     * Parses an event task from the task details array.
     *
     * @param taskDetails The array containing task details.
     * @return The Event task.
     * @throws GarfieldException If there's an error during date parsing.
     */
    private Event parseEventTask(String[] taskDetails) throws GarfieldException {
        return new Event(taskDetails[2], this.parseDateTime(taskDetails[3]), this.parseDateTime(taskDetails[4]));
    }

    /**
     * Returns a LocalDateTime object after parsing a String representation of a date and time.
     *
     * @param dateInput String representing the date and time of a garfield.task.Task.
     * @return LocalDateTime object.
     * @throws DateTimeParseException Error thrown if date time format is wrong in the input.
     */
    private LocalDateTime parseDateTime(String dateInput) throws DateTimeParseException {
        assert dateInput != null : "Date input cannot be null";
        assert !dateInput.isEmpty() : "Date input cannot be empty";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateInput, formatter);
    }
}
