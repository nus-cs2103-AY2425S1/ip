package task;

import java.util.ArrayList;

import exception.InvalidIndexException;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     *
     * Initializes the internal list to store tasks using an ArrayList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the TaskList.
     *
     * @param t The task to be added to the list.
     * @return A confirmation message indicating the task has been added, along with the current number of tasks in the list.
     */
    public String addTask(Task t) {
        this.tasks.add(t);
        String output = " Got it. I've added this task: \n" +
                String.format("  %s\n", t) +
                String.format(" Now you have %d tasks in the list.\n", tasks.size());
        return output;
    }

    /**
     * Deletes a task from the TaskList based on the specified index.
     *
     * @param loc The zero-based index of the task to be deleted.
     * @return A confirmation message indicating the task has been removed, along with the current number of tasks in the list.
     * @throws InvalidIndexException If the index is out of bounds (i.e., less than 0 or greater than or equal to the list size).
     */
    public String deleteTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        Task tmp = tasks.get(loc);
        tasks.remove(loc);
        String output = " Noted. I've removed this task:\n" +
                String.format("    %s\n", tmp) +
                String.format("  Now you have %d tasks in the list.\n", tasks.size());
        return output;
    }

    /**
     * Marks a task as done in the TaskList based on the specified index.
     *
     * @param loc The zero-based index of the task to be marked as done.
     * @return A confirmation message indicating the task has been marked as done.
     * @throws InvalidIndexException If the index is out of bounds (i.e., less than 0 or greater than or equal to the list size).
     */
    public String markTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).markAsDone();
        String output = "  Nice! I've marked this task as done:\n" +
                String.format("    %s\n", tasks.get(loc));
        return output;
    }

    /**
     * Marks a task as not done in the TaskList based on the specified index.
     *
     * @param loc The zero-based index of the task to be marked as not done.
     * @return A confirmation message indicating the task has been marked as not done.
     * @throws InvalidIndexException If the index is out of bounds (i.e., less than 0 or greater than or equal to the list size).
     */
    public String unMarkTask(int loc) throws InvalidIndexException {
        if (loc < 0 || loc >= tasks.size()) {
            throw new InvalidIndexException("Please enter a valid index!");
        }
        tasks.get(loc).unmark();
        String output = "  OK, I've marked this task as not done yet:\n" +
                String.format("    %s\n", tasks.get(loc));
        return output;
    }

    /**
     * Finds tasks in the TaskList that contain the specified text.
     *
     * @param text The substring to search for within task descriptions.
     * @return A list of matching tasks, formatted as a string, including their index in the TaskList.
     *         If no tasks match, the output will contain an empty list message.
     */
    public String findTasks(String text) {

        StringBuilder output = new StringBuilder("  Here are the matching tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).contains(text)) {
                output.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
            }
        }
        return output.toString();

    }

    /**
     * Returns a formatted list of all tasks in the TaskList.
     *
     * @return A string representation of all tasks in the TaskList, each task numbered in order.
     *         If the TaskList is empty, the response will still include a header with no tasks listed.
     */
    public String list() {
        StringBuilder response = new StringBuilder("  Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(String.format("    %d. %s\n", i + 1, tasks.get(i)));
        }
        return response.toString();
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The total number of tasks currently in the TaskList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index in the TaskList.
     *
     * @param loc The zero-based index of the task to retrieve.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds (i.e., less than 0 or greater than or equal to the list size).
     */
    public Task get(int loc) {
        return tasks.get(loc);
    }
    

}
