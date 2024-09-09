package bruno.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import bruno.Bruno;
import bruno.Parser;
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
        if (type.equals(Bruno.TaskType.TODO)) {
            task = makeToDo(str);
        } else if (type.equals(Bruno.TaskType.DEADLINE)) {
            task = makeDeadline(str);
        } else if (type.equals(Bruno.TaskType.EVENT)) {
            task = makeEvent(str);
        }

        return add(task);
    }

    private Task add(Task task) throws UnknownCommandException {
        if (task != null) {
            tasks.add(task);
            storage.updateFile(this.tasks);
            return task;
        } else {
            throw new UnknownCommandException();
        }
    }

    private Event makeEvent(String str) throws BrunoException {
        if (!str.contains("/from") || !str.contains("/to")) {
            throw new MissingFieldException();
        }

        String description = str.substring(0, str.indexOf("/from")).trim();
        String fromString = str.substring(str.indexOf("/from") + 6, str.indexOf("/to")).trim();
        String toString = str.substring(str.indexOf("/to") + 3).trim();
        LocalDateTime from;
        LocalDateTime to;

        if (description.isEmpty()) {
            throw new EmptyTaskException();
        }

        try {
            from = Parser.parseNaturalDateTime(fromString);
            to = Parser.parseNaturalDateTime(toString);
        } catch (BrunoException e) {
            throw new BrunoException("Invalid date format.");
        }

        return new Event(description, from, to);
    }

    private Deadline makeDeadline(String str) throws BrunoException {
        if (!str.contains("/by")) {
            throw new MissingFieldException();
        }

        String description = str.substring(0, str.indexOf("/by")).trim();
        String byString = str.substring(str.indexOf("/by") + 3).trim();
        LocalDateTime by;

        if (description.isEmpty()) {
            throw new EmptyTaskException();
        }

        try {
            by = Parser.parseNaturalDateTime(byString);
        } catch (BrunoException e) {
            throw new BrunoException("Invalid date format.");
        }

        return new Deadline(description, by);
    }

    private static ToDo makeToDo(String str) {
        return new ToDo(str);
    }

    /**
     * Marks a task as completed based on its index.
     *
     * @param nums The index of the task to mark as completed.
     * @throws BrunoException If the index is invalid.
     */
    public ArrayList<Task> markTask(String ... nums) throws BrunoException {
        try {
            ArrayList<Task> markedTasks = new ArrayList<>();
            mark(markedTasks, nums);
            return markedTasks;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    private void mark(ArrayList<Task> markedTasks, String ... nums) {
        for (String i : nums) {
            Task task = tasks.get(Integer.parseInt(i) - 1);
            task.complete();
            markedTasks.add(task);
            storage.updateFile(this.tasks);
        }
    }

    /**
     * Unmarks a task (sets it as incomplete) based on its index.
     *
     * @param nums The index of the task to unmark.
     * @throws BrunoException If the index is invalid.
     */
    public ArrayList<Task> unmarkTask(String ... nums) throws BrunoException {
        try {
            ArrayList<Task> unmarkedTasks = new ArrayList<>();
            unmark(unmarkedTasks, nums);
            return unmarkedTasks;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    private void unmark(ArrayList<Task> unmarkedTasks, String ... nums) {
        for (String i : nums) {
            Task task = tasks.get(Integer.parseInt(i) - 1);
            task.uncomplete();
            unmarkedTasks.add(task);
            storage.updateFile(this.tasks);
        }
    }

    /**
     * Deletes a task based on its index.
     *
     * @param nums The indices of the tasks to delete.
     * @throws BrunoException If the index is invalid.
     */
    public ArrayList<Task> deleteTask(String ... nums) throws BrunoException {
        try {
            ArrayList<Task> deletedTasks = new ArrayList<>();
            List<Integer> taskIndices = Arrays.stream(nums)
                    .map(i -> Integer.parseInt(i.trim()) - 1)
                    .sorted(Comparator.reverseOrder())
                    .toList();
            
            delete(deletedTasks, taskIndices);

            return deletedTasks;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    private void delete(ArrayList<Task> deletedTasks, List<Integer> indices) {
        for (int i : indices) {
            Task task = tasks.remove(i);
            deletedTasks.add(task);
            storage.updateFile(this.tasks);
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
            checkForKeyword(keyword, task, foundTasks);
        }
        return foundTasks;
    }

    private static void checkForKeyword(String keyword, Task task, ArrayList<Task> foundTasks) {
        if (task.toString().contains(keyword)) {
            foundTasks.add(task);
        }
    }
}
