package trackbot.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import trackbot.TrackBotException;
import trackbot.TrackBotStorage;

/**
 * A list of tasks loaded from trackbot Storage.
 */
public class TrackList {
    private List<Task> list;
    private final TrackBotStorage storage;

    /**
     * Constructs a TrackList and loads tasks from the specified storage.
     *
     * @param storage The TrackBotStorage object used for saving and loading tasks.
     * @throws IOException If an I/O error occurs while loading the tasks.
     */
    public TrackList(TrackBotStorage storage) throws IOException {
        this.storage = storage;
        try {
            list = storage.loadContents();
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
            storage.saveContents(list);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a new task to the list and saves the updated list to storage.
     *
     * @param task The task to be added to the list.
     * @throws TrackBotException If the task is null.
     */
    public String addToList(Task task) throws TrackBotException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (task == null) {
            // throw new TrackBotException("No task found.");
            writer.println("No task found.");
            return stringWriter.toString();
        }
        list.add(task);
        saveList();
        // System.out.println("````````````````````````````````````````````````````````````");
        // System.out.println("Successfully added this task:\n  " + task);
        // System.out.println("Now you have " + list.size() + " tasks in the list.");
        // System.out.println("````````````````````````````````````````````````````````````");
        writer.println("Successfully added this task:\n  " + task);
        writer.println("Now you have " + list.size() + " tasks in the list.");
        return stringWriter.toString();
    }

    /**
     * Marks a task as done and saves the updated list to storage.
     *
     * @param num The index of the task to be marked as done (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public String markTask(int num) throws TrackBotException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (num < 0 || num > list.size() - 1) {
            // throw new TrackBotException("Please enter a valid task number.");
            writer.println("Please enter a valid task number.");
            return stringWriter.toString();
        }
        list.get(num).mark();
        saveList();
        // System.out.println("````````````````````````````````````````````````````````````");
        // System.out.println("Successfully marked task " + (num + 1) + " as done:");
        // System.out.println("  " + list.get(num).toString());
        // System.out.println("````````````````````````````````````````````````````````````");
        writer.println("Successfully marked task " + (num + 1) + " as done:");
        writer.println("  " + list.get(num).toString());
        return stringWriter.toString();
    }

    /**
     * Unmarks a task as not done yet and saves the updated list to storage.
     *
     * @param num The index of the task to be unmarked (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public String unmarkTask(int num) throws TrackBotException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (num < 0 || num > list.size() - 1) {
            // throw new TrackBotException("Please enter a valid task number.");
            writer.println("Please enter a valid task number.");
            return stringWriter.toString();
        }
        list.get(num).unmark();
        saveList();
        // System.out.println("````````````````````````````````````````````````````````````");
        // System.out.println("Successfully marked task " + (num + 1) + " as not done yet:");
        // System.out.println("  " + list.get(num).toString());
        // System.out.println("````````````````````````````````````````````````````````````");
        writer.println("Successfully marked task " + (num + 1) + " as not done yet:");
        writer.println("  " + list.get(num).toString());
        return stringWriter.toString();
    }

    /**
     * Deletes a task from the list and saves the updated list to storage.
     *
     * @param num The index of the task to be deleted (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public String deleteFromList(int num) throws TrackBotException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (num < 0 || num > list.size() - 1) {
            // throw new TrackBotException("Please enter a valid task number.");
            writer.println("Please enter a valid task number.");
            return stringWriter.toString();
        }
        String deletedTask = list.get(num).toString();
        list.remove(num);
        saveList();
        // System.out.println("````````````````````````````````````````````````````````````");
        // System.out.println("Successfully deleted task " + (num + 1) + " from list:");
        // System.out.println("  " + deletedTask);
        // System.out.println("Now you have " + list.size() + " tasks in the list.");
        // System.out.println("````````````````````````````````````````````````````````````");
        writer.println("Successfully deleted task " + (num + 1) + " from list:");
        writer.println("  " + deletedTask);
        writer.println("Now you have " + list.size() + " tasks in the list.");
        return stringWriter.toString();
    }

    /**
     * Finds tasks that contain the keyword in their description.
     *
     * @param keyword the keyword to search for.
     * @return a list of tasks that match the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : list) {
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
            // System.out.println("No matching tasks found.");
            writer.println("No matching tasks found.");
            return writer.toString();
        } else {
            // System.out.println("````````````````````````````````````````````````````````````");
            // System.out.println("Here are the matching tasks in your list:");
            writer.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                // System.out.println((i + 1) + ". " + matchingTasks.get(i));
                writer.println((i + 1) + ". " + matchingTasks.get(i));
            }
            // System.out.println("````````````````````````````````````````````````````````````");
            return stringWriter.toString();
        }
    }

    /**
     * Prints the entire list of tasks.
     *
     * @throws TrackBotException If the list is currently empty.
     */
    public String printList() throws TrackBotException {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter, true);
        if (list.isEmpty()) {
            // throw new TrackBotException("The list is currently empty.");
            writer.println("The list is currently empty.");
            return stringWriter.toString();
        }
        // System.out.println("````````````````````````````````````````````````````````````\n" + "List:");
        writer.println("List:");
        int i = 1;
        for (Task item : list) {
            // System.out.print(i + ". " + item.toString() + "\n");
            writer.println(i + ". " + item.toString() + "\n");
            i++;
        }
        // System.out.println("````````````````````````````````````````````````````````````");
        return stringWriter.toString();
    }
}
