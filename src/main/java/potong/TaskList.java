package potong;

import potong.exceptions.PotongException;

import potong.task.Task;

import java.io.IOException;

import java.util.ArrayList;

/**
 * Represent the list of tasks present.
 */
public class TaskList {

    private ArrayList<Task> arr;

    /**
     * Initialise the task list with a size of 100.
     */
    public TaskList() {
        this.arr = new ArrayList<>(100);
    }

    /**
     * Load the task list with the saved data.
     *
     * @param arr ArrayList of saved tasks.
     */
    public void initialise(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Add a task into the list.
     *
     * @param input Task.
     * @return String representation of the action.
     */
    public String add(Task input) {
        this.arr.add(input);
        return String.format("Got it. I've added this task:\n %s\nNow you have %d tasks in the list.",
                input, this.arr.size());
    }

    /**
     * Mark a task as done in the list.
     *
     * @param index Index of the task.
     * @return String representation of the action.
     * @throws PotongException If the index is out of bounds.
     */
    public String mark(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot mark a task thats not there!");
        }
        return this.arr.get(index - 1).mark();
    }

    /**
     * Mark a task as not done in the list.
     *
     * @param index Index of the task.
     * @return String representation of the action.
     * @throws PotongException If the index is out of bounds.
     */
    public String unmark(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot unmark a task thats not there!");
        }
        return this.arr.get(index - 1).unmark();
    }

    /**
     * Delete a task in the list.
     *
     * @param index Index of the task.
     * @return String representation of the action.
     * @throws PotongException If the index is out of bounds.
     */
    public String delete(int index) throws PotongException {
        if (index - 1 >= this.arr.size()) {
            throw new PotongException("We cannot delete a task thats not there!");
        }
        Task removed = this.arr.remove(index - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                removed, this.arr.size());
    }

    /**
     * Save the list of tasks.
     *
     * @param storage Storage class to save the list.
     */
    public void writeToStorage(Storage storage) {
        StringBuilder result = new StringBuilder();
        for (Task curr : this.arr) {
            String type = curr.getType();
            String status = curr.getStatus();
            String description = curr.getDescription();
            String time = curr.getTime();
            if (time.isEmpty()) {
                String task = String.format("%s | %s | %s\n", type, status, description);
                result.append(task);
            } else {
                String task = String.format("%s | %s | %s | %s\n", type, status, description, time);
                result.append(task);
            }
        }
        storage.writeToFile(result.toString());
    }

    /**
     * Find all tasks with the keyword in the list of tasks.
     *
     * @param keyword Keyword.
     * @return String representation of the list of tasks with the keyword.
     */
    public String find(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task curr : this.arr) {
            if (curr.findKeyword(keyword)) {
                result.add(curr);
            }
        }
        StringBuilder stringResult = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < result.size(); i++) {
            stringResult.append(String.format("%d. %s\n", i + 1, result.get(i)));
        }
        return stringResult.toString();
    }

    /**
     * String representation of the list of tasks.
     *
     * @return String representation of this list.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.arr.size(); i++) {
            result.append(String.format("%d. %s\n", i + 1, this.arr.get(i)));
        }
        return result.toString();
    }
}
