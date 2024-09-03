package bruno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import bruno.Bruno;
import bruno.Storage;
import bruno.exceptions.BrunoException;
import bruno.exceptions.EmptyTaskException;
import bruno.exceptions.InvalidTaskIndexException;
import bruno.exceptions.MissingFieldException;
import bruno.exceptions.UnknownCommandException;

/**
 * Manages the list of tasks, including adding, marking, unmarking, and deleting tasks.
 * Interacts with the Storage class to load and save tasks from and to a file.
 */
public class TaskList {
    private DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Task> tasks;
    private Storage storage;

    /**
     * Initializes a TaskList object and loads tasks from the storage file.
     * If there is an error loading the file, it initializes an empty task list.
     *
     * @param storage The storage instance used to load and save tasks.
     */
    public TaskList(Storage storage) {
        this.storage = storage;
        try {
            this.tasks = storage.loadFromFile();
        } catch (BrunoException e) {
            tasks = new ArrayList<>();
        }
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the input string and the task type.
     * Validates the input and ensures the required fields are present.
     *
     * @param str  The input string describing the task.
     * @param type The type of the task.
     * @throws BrunoException If the input is invalid or a required field is missing.
     */
    public Task addTask(String str, Bruno.TaskType type) throws BrunoException {
        if (str.trim().isEmpty()) {
            throw new EmptyTaskException();
        }

        Task task = null;
        boolean recognized = true;
        if (type.equals(Bruno.TaskType.TODO)) {
            task = new ToDo(str);
        } else if (type.equals(Bruno.TaskType.DEADLINE)) {
            if (!str.contains("/by")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/by")).trim();
            String byString = str.substring(str.indexOf("/by") + 3).trim();
            if (description.isEmpty()) {
                throw new EmptyTaskException();
            }
            try {
                LocalDateTime by = LocalDateTime.parse(byString, formatter1);
                task = new Deadline(description, by);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else if (type.equals(Bruno.TaskType.EVENT)) {
            if (!str.contains("/from") || !str.contains("/to")) {
                throw new MissingFieldException();
            }
            String description = str.substring(0, str.indexOf("/from")).trim();
            String fromString = str.substring(str.indexOf("/from") + 6, str.indexOf("/to")).trim();
            String toString = str.substring(str.indexOf("/to") + 4).trim();
            if (description.isEmpty()) {
                throw new EmptyTaskException();
            }
            try {
                LocalDateTime from = LocalDateTime.parse(fromString, formatter1);
                LocalDateTime to = LocalDateTime.parse(toString, formatter1);
                task = new Event(description, from, to);
            } catch (DateTimeParseException e) {
                throw new BrunoException("Invalid date format. Please use 'yyyy-MM-dd HH:mm'");
            }
        } else {
            recognized = false;
        }

        if (recognized) {
            tasks.add(task);
            storage.updateFile(this.tasks);
            return task;
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param num The index of the task to mark as completed.
     * @throws BrunoException If the index is invalid.
     */
    public Task markTask(String num) throws BrunoException {
        try {
            Task task = tasks.get(Integer.parseInt(num) - 1);
            task.complete();
            storage.updateFile(this.tasks);
            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Unmarks a task (sets it as incomplete) based on its index.
     *
     * @param num The index of the task to unmark.
     * @throws BrunoException If the index is invalid.
     */
    public Task unmarkTask(String num) throws BrunoException {
        try {
            Task task = tasks.get(Integer.parseInt(num) - 1);
            task.uncomplete();
            storage.updateFile(this.tasks);
            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Deletes a task based on its index.
     *
     * @param num The index of the task to delete.
     * @throws BrunoException If the index is invalid.
     */
    public Task deleteTask(String num) throws BrunoException {
        try {
            Task task = tasks.remove(Integer.parseInt(num) - 1);
            storage.updateFile(this.tasks);
            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Finds a task based on the entered keyword.
     *
     * @param keyword The keyword used to find results.
     */
    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                foundTasks.add(task);
            }
        }

        return foundTasks;
    }
}
