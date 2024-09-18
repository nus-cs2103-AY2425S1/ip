package trackbot.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import trackbot.TrackBotStorage;

/**
 * A list of tasks loaded from trackbot Storage.
 */
public class TrackList {
    static final String INVALID_TASK_NUMBER_MESSAGE = "Please enter a valid task number.";
    private List<Task> tasks;
    private final TrackBotStorage storage;

    /**
     * Constructs a TrackList and loads tasks from the specified storage.
     *
     * @param storage The TrackBotStorage object used for saving and loading tasks.
     * @throws IOException If an I/O error occurs while loading the tasks.
     */
    public TrackList(TrackBotStorage storage) throws IOException {
        assert storage != null : "Storage cannot be null";
        this.storage = storage;
        try {
            tasks = storage.loadContents();
            assert tasks != null;
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Starting with an empty task list.");
        }
    }

    /**
     * Saves the current task list to storage.
     * Handles any I/O errors that occur during saving.
     */
    private void saveList() {
        try {
            storage.saveContents(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    /**
     * Checks if the given task already exists in the list and returns the existing task if found.
     *
     * @param newTask The task to check.
     * @return The existing duplicate task if found, null otherwise.
     */
    private Task findDuplicate(Task newTask) {
        for (Task task : tasks) {
            if (task.getDesc().equalsIgnoreCase(newTask.getDesc())) {
                return task;
            }
        }
        return null;
    }

    /**
     * Adds a new task to the list and saves the updated list to storage.
     *
     * @param task The task to be added to the list.
     */
    public String addToList(Task task) {
        assert task != null : "Task must not be null";
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        Task existingTask = findDuplicate(task);
        if (existingTask != null) {
            writer.println("Failed to add task. This task already exists:\n  " + existingTask);
            writer.println("Please delete the existing task to add a new one :D");
            return stringWriter.toString();
        }
        tasks.add(task);
        saveList();
        writer.println("Successfully added this task:\n  " + task);
        writer.println("Now you have " + tasks.size() + " tasks in the list.");
        return stringWriter.toString();
    }

    /**
     * Marks a task as done and saves the updated list to storage.
     *
     * @param num The index of the task to be marked as done (0-based index).
     */
    public String markTask(int num) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (!isValidTaskIndex(num, writer)) {
            return stringWriter.toString();
        }
        tasks.get(num).mark();
        saveList();
        writer.println("Successfully marked task " + (num + 1) + " as done:");
        writer.println("  " + tasks.get(num).toString());
        return stringWriter.toString();
    }

    /**
     * Unmarks a task as not done yet and saves the updated list to storage.
     *
     * @param num The index of the task to be unmarked (0-based index).
     */
    public String unmarkTask(int num) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (!isValidTaskIndex(num, writer)) {
            return stringWriter.toString();
        }
        tasks.get(num).unmark();
        saveList();
        writer.println("Successfully marked task " + (num + 1) + " as not done yet:");
        writer.println("  " + tasks.get(num).toString());
        return stringWriter.toString();
    }

    /**
     * Deletes a task from the list and saves the updated list to storage.
     *
     * @param num The index of the task to be deleted (0-based index).
     */
    public String deleteFromList(int num) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (!isValidTaskIndex(num, writer)) {
            return stringWriter.toString();
        }
        String deletedTask = tasks.get(num).toString();
        tasks.remove(num);
        saveList();
        writer.println("Successfully deleted task " + (num + 1) + " from list:");
        writer.println("  " + deletedTask);
        writer.println("Now you have " + tasks.size() + " tasks in the list.");
        return stringWriter.toString();
    }

    /**
     * Validates the task index.
     *
     * @param num The index of the task to be validated.
     * @param writer The PrintWriter object to write any error messages.
     * @return true if the index is valid, false otherwise.
     */
    private boolean isValidTaskIndex(int num, PrintWriter writer) {
        if (num < 0 || num > tasks.size() - 1) {
            writer.println(INVALID_TASK_NUMBER_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * Finds tasks that contain the keyword in their description.
     *
     * @param keyword the keyword to search for.
     * @return a list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDesc().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Displays the list of matching tasks found by the search.
     *
     * @param matchingTasks the list of matching tasks.
     */
    public String showFoundTasks(List<Task> matchingTasks) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (matchingTasks.isEmpty()) {
            writer.println("No matching tasks found.");
            return writer.toString();
        } else {
            writeMatchingTasks(matchingTasks, writer);
            return stringWriter.toString();
        }
    }

    /**
     * Writes matching tasks to writer.
     * @param matchingTasks Tasks that match with keyword.
     * @param writer Records all matching tasks.
     */
    private static void writeMatchingTasks(List<Task> matchingTasks, PrintWriter writer) {
        writer.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            writer.println((i + 1) + ". " + matchingTasks.get(i));
        }
    }

    /**
     * Prints the entire list of tasks.
     *
     */
    public String printList() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (tasks.isEmpty()) {
            writer.println("The list is currently empty.");
            return stringWriter.toString();
        }
        writeListOfTask(writer);
        return stringWriter.toString();
    }

    /**
     * Writes all tasks in list into writer.
     * @param writer Records all tasks from list.
     */
    private void writeListOfTask(PrintWriter writer) {
        writer.println("List:");
        int i = 1;
        for (Task item : tasks) {
            writer.println(i + ". " + item.toString() + "\n");
            i++;
        }
    }
}
