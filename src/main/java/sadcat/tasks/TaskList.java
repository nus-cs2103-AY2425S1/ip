package sadcat.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import sadcat.exceptions.SadCatException;
import sadcat.storage.Storage;

/**
 * The TaskList class manages a list of tasks.
 * It follows the Singleton pattern to ensure only one instance exists.
 */
public class TaskList {
    private static TaskList taskList;
    private final List<Task> taskStore;
    private final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");

    /**
     * Constructs a new TaskList object.
     */
    public TaskList() {
        this.taskStore = new ArrayList<>();
    }

    /**
     * Gets the singleton instance of the TaskList class.
     *
     * @return The TaskList instance
     */
    public static TaskList getInstance() {
        if (taskList == null) {
            taskList = new TaskList();
        }
        return taskList;
    }

    /**
     * Loads a task from a string representation and adds it to the task list.
     *
     * @param data String representation of the task
     */
    public void loadData(String data) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Arrays.stream(data.split("\n"))
                .map(line -> line.split("\\s*\\|\\s*"))
                .forEach(parts -> {
                    try {
                        Task task = switch (parts[0]) {
                        case "T" -> new Todo(parts[2]);
                        case "D" -> new Deadline(parts[2], LocalDateTime.parse(parts[3].trim(), dtf));
                        case "E" -> new Event(parts[2], LocalDateTime.parse(parts[3].trim(), dtf),
                                LocalDateTime.parse(parts[4].trim(), dtf));
                        default -> throw new SadCatException("Invalid Task type provided.");
                        };
                        if (parts[1].equals("1")) {
                            task.markAsDoneNonVerbose();
                        }
                        taskStore.add(task);
                    } catch (SadCatException e) {
                        System.out.println(e.getMessage());
                    }
                });
    }

    /**
     * Prints all tasks in the list.
     */
    public void printTaskList() {
        if (taskStore.isEmpty()) {
            System.out.println("List is currently empty.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        IntStream.range(0, taskStore.size())
                .forEach(i -> System.out.printf("%d. %s\n", i + 1, taskStore.get(i)));
    }

    /**
     * Filters and prints all tasks containing the input.
     */
    public void filter(String input) {
        String lowercaseInput = input.toLowerCase();
        List<Task> filtered = taskStore.stream()
                .filter(task -> {
                    String lowercaseDescription = task.description.toLowerCase();
                    if (lowercaseDescription.contains(lowercaseInput)) {
                        return true;
                    }
                    if (task instanceof Deadline deadline) {
                        String deadlineStr = deadline.by.format(formatter).toLowerCase();
                        return deadlineStr.contains(lowercaseInput);
                    }
                    if (task instanceof Event event) {
                        String fromStr = event.from.format(formatter).toLowerCase();
                        String toStr = event.to.format(formatter).toLowerCase();
                        return fromStr.contains(lowercaseInput) || toStr.contains(lowercaseInput);
                    }
                    return false;
                })
                .toList();

        if (filtered.isEmpty()) {
            System.out.println("No matching tasks found in your list.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            IntStream.range(0, filtered.size())
                    .forEach(i -> System.out.printf("%d. %s\n", i + 1, filtered.get(i)));
        }
    }

    /**
     * Returns a copy of the task list.
     *
     * @return ArrayList containing all tasks
     */
    public ArrayList<Task> getTaskList() {
        ArrayList<Task> resList = new ArrayList<>(taskStore);
        return resList;
    }

    /**
     * Creates a new task and adds it to the list.
     *
     * @param type Type of the task (todo, deadline, or event)
     * @param input Description of the task
     * @throws SadCatException If the input is invalid
     */
    public void createTask(String type, String input) throws SadCatException {
        try {
            if (input.isEmpty()) {
                throw new SadCatException("Empty Task description provided.");
            }
            Task task;
            switch (type) {
            case "todo":
                task = Task.createTodo(input);
                break;
            case "deadline":
                task = Task.createDeadline(input);
                break;
            case "event":
                task = Task.createEvent(input);
                break;
            default:
                throw new SadCatException("Invalid Task type.");
            }
            taskStore.add(task);
            Storage.saveData();
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            System.out.printf(
                    "Now you have %d tasks in the list.\n",
                    taskStore.size()
            );
        } catch (DateTimeParseException | SadCatException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input Index of the task to delete
     * @throws SadCatException If the index is invalid or the list is empty
     */
    public void deleteTask(String input) throws SadCatException {
        if (taskStore.isEmpty()) {
            throw new SadCatException("Task list is already empty.");
        }
        if (input.isEmpty()) {
            throw new SadCatException("No Task index provided.");
        }
        String reg = input.replaceAll("\\D+", "");
        if (reg.isEmpty()) {
            throw new SadCatException("No index provided.");
        }
        int id = Integer.parseInt(reg);
        if (id > taskStore.size() || id < 1) {
            throw new SadCatException("Invalid index provided.");
        }
        Task task = taskStore.get(id - 1);
        taskStore.remove(id - 1);
        Storage.saveData();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf(
                "Now you have %d tasks in the list.\n",
                taskStore.size()
        );
    }

    /**
     * Updates an existing task in the list.
     *
     * @param input The user input containing the index and new task details
     * @throws SadCatException If the input is invalid or the index is out of range
     */
    public void updateTask(String input) throws SadCatException {
        if (taskStore.isEmpty()) {
            throw new SadCatException("Task list is empty. Nothing to update.");
        }
        String[] parts = input.trim().split("\\s+", 3);
        if (parts.length < 3) {
            throw new SadCatException(
                    "Invalid update format.");
        }

        int index;
        try {
            index = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new SadCatException("Invalid index provided.");
        }

        if (index < 0 || index >= taskStore.size()) {
            throw new SadCatException("Task index out of range.");
        }

        String type = parts[1].toLowerCase();
        String description = parts[2];

        Task newTask;
        try {
            switch (type) {
            case "todo":
                newTask = new Todo(description);
                break;
            case "deadline":
                if (!description.contains("/by")) {
                    throw new SadCatException("Invalid deadline format.");
                }
                String[] deadlineParts = description.split("/by", 2);
                newTask = new Deadline(deadlineParts[0].trim(),
                        LocalDateTime.parse(deadlineParts[1].trim(), formatter));
                break;
            case "event":
                if (!description.contains("/from") || !description.contains("/to")) {
                    throw new SadCatException("Invalid event format.");
                }
                String[] eventParts = description.split("/from", 2);
                String[] eventTimeParts = eventParts[1].split("/to", 2);
                newTask = new Event(eventParts[0].trim(),
                        LocalDateTime.parse(eventTimeParts[0].trim(), formatter),
                        LocalDateTime.parse(eventTimeParts[1].trim(), formatter));
                break;
            default:
                throw new SadCatException("Invalid task type.");
            }
        } catch (DateTimeParseException e) {
            throw new SadCatException("Invalid date format.");
        }

        if (taskStore.get(index).isDone) {
            newTask.markAsDoneNonVerbose();
        }

        taskStore.set(index, newTask);
        Storage.saveData();
        System.out.println("Got it. I've updated the task:");
        System.out.println(newTask);
    }

    /**
     * Marks a task as done.
     *
     * @param input Index of the task to mark as done
     * @throws SadCatException If the index is invalid or the list is empty
     */
    public void mark(String input) throws SadCatException {
        if (taskStore.isEmpty()) {
            throw new SadCatException("List is empty, no tasks to mark.");
        }
        if (input == null) {
            throw new SadCatException("No input provided.");
        }
        String reg = input.replaceAll("\\D+", "");
        if (reg.isEmpty()) {
            throw new SadCatException("No index provided.");
        }
        int id = Integer.parseInt(reg);
        if (id > taskStore.size() || id < 1) {
            throw new SadCatException("Invalid index provided.");
        }
        taskStore.get(id - 1).markAsDone();
        Storage.saveData();
    }

    /**
     * Marks a task as not done.
     *
     * @param input Index of the task to mark as not done
     * @throws SadCatException If the index is invalid or the list is empty
     */
    public void unmark(String input) throws SadCatException {
        if (taskStore.isEmpty()) {
            throw new SadCatException("List is empty, no tasks to unmark.");
        }
        if (input == null) {
            throw new SadCatException("No input provided.");
        }
        String reg = input.replaceAll("\\D+", "");
        if (reg.isEmpty()) {
            throw new SadCatException("No index provided.");
        }
        int id = Integer.parseInt(reg);
        if (id > taskStore.size() || id < 1) {
            throw new SadCatException("Invalid index provided.");
        }
        taskStore.get(id - 1).markAsNotDone();
        Storage.saveData();
    }

    /**
     * Changes the current save file.
     *
     * @param input The name of the new file to use
     */
    public void saveFile(String input) {
        if (input.isEmpty()) {
            System.out.println("Please provide a file name.");
            return;
        }
        Storage.getInstance().changeFile(input);
        System.out.println("Tasks will now be saved to and loaded from: " + Storage.getInstance().getCurrentFile());
    }

    /**
     * Clears all tasks from the list.
     */
    public void clearTasks() {
        taskStore.clear();
    }
}
