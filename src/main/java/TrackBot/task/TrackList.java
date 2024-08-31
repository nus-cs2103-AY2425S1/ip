package TrackBot.task;

import TrackBot.TrackBotException;
import TrackBot.TrackBotStorage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * A list of tasks loaded from TrackBot Storage.
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
    public void addToList(Task task) throws TrackBotException {
        if (task == null) {
            throw new TrackBotException("No task found.");
        }
        list.add(task);
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully added this task:\n  " + task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("````````````````````````````````````````````````````````````");
    }

    /**
     * Marks a task as done and saves the updated list to storage.
     *
     * @param num The index of the task to be marked as done (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public void markTask(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        list.get(num).mark();
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully marked task " + (num + 1) + " as done:");
        System.out.println("  " + list.get(num).toString());
        System.out.println("````````````````````````````````````````````````````````````");

    }

    /**
     * Unmarks a task as not done yet and saves the updated list to storage.
     *
     * @param num The index of the task to be unmarked (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public void unmarkTask(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        list.get(num).unmark();
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully marked task " + (num + 1) + " as not done yet:");
        System.out.println("  " + list.get(num).toString());
        System.out.println("````````````````````````````````````````````````````````````");
    }

    /**
     * Deletes a task from the list and saves the updated list to storage.
     *
     * @param num The index of the task to be deleted (0-based index).
     * @throws TrackBotException If the task number is invalid.
     */
    public void deleteFromList(int num) throws TrackBotException {
        if (num < 0 || num > list.size() - 1) {
            throw new TrackBotException("Please enter a valid task number.");
        }
        String deletedTask = list.get(num).toString();
        list.remove(num);
        saveList();
        System.out.println("````````````````````````````````````````````````````````````");
        System.out.println("Successfully deleted task " + (num + 1) + " from list:");
        System.out.println("  " + deletedTask);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        System.out.println("````````````````````````````````````````````````````````````");
    }

    /**
     * Prints the entire list of tasks.
     *
     * @throws TrackBotException If the list is currently empty.
     */
    public void printList() throws TrackBotException {
        if (list.isEmpty()) {
            throw new TrackBotException("The list is currently empty.");
        }
        System.out.println("````````````````````````````````````````````````````````````\n" + "List:");
        int i = 1;
        for (Task item : list) {
            System.out.print(i + ". " + item.toString() + "\n");
            i++;
        }
        System.out.println("````````````````````````````````````````````````````````````");
    }
}
