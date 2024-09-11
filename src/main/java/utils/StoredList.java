package utils;

import java.util.ArrayList;

import tasks.Task;

/**
 * A representation of a task list for Chatterbox.
 */
public class StoredList {
    private final ArrayList<Task> data;

    /**
     * Initialisation of a list for storing task in Chatterbox.
     */
    public StoredList() {
        this.data = new ArrayList<>();
    }

    /**
     * Adds a task in to the task list.
     * @param item A task to be added.
     * @return The message if successful in adding a Task.
     */
    public String addItem(Task item) {
        StringBuilder message = new StringBuilder();
        this.data.add(item);
        message.append("____________________________________________________________\n");
        message.append("Got it, I've added this task: \n");
        message.append(item).append("\n");
        message.append("Now you have ").append(this.getSize()).append(" task in the list.\n");
        message.append("____________________________________________________________");
        return message.toString();
    }

    public Task getItem(int index) {
        return this.data.get(index);
    }

    public int getSize() {
        return this.data.size();
    }

    /**
     * Removes a task from the task list.
     * @param index The index to the task,
     * @return The message if successful in removing a Task.
     */
    public String removeItem(int index) {
        assert index < this.getSize() : "Accessing item not in list";
        String message = "____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + this.getItem(index) + "\n"
                + "Now you have " + this.getSize() + " task in the list.\n"
                + "____________________________________________________________\n";
        this.data.remove(index);
        return message;
    }

    /**
     * Finds an item within the stored task that matches/ contains the given name.
     * @param name Name or part of the name to be found
     * @return A message containing all task found.
     */
    public String findItem(String name) {
        StringBuilder message = new StringBuilder();
        int count = 1;
        message.append("____________________________________________________________\n");
        for (Task item : data) {
            if (item.getName().contains(name)) {
                message.append(count).append(". ").append(item).append("\n");
            }
        }
        message.append("____________________________________________________________\n");
        return message.toString();
    }

    /**
     * The string representation of a task list for Chatterbox.
     * @return A string representation of StoredList.
     */
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("____________________________________________________________\n");
        for (int i = 0; i < this.getSize(); i++) {
            message.append(i + 1).append(". ").append(this.getItem(i).toString()).append("\n");
        }
        message.append("____________________________________________________________\n");
        return message.toString();
    }
}
