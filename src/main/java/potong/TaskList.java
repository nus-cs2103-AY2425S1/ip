package potong;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import potong.exceptions.IllegalInputPotongException;
import potong.task.Task;

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
     * @throws IllegalInputPotongException If the index is out of bounds.
     */
    public String mark(int index) throws IllegalInputPotongException {
        if (index - 1 >= this.arr.size() || index - 1 < 0) {
            throw new IllegalInputPotongException(String.format("Your index %d is out of bounds!", index));
        }
        return this.arr.get(index - 1).mark();
    }

    /**
     * Mark a task as not done in the list.
     *
     * @param index Index of the task.
     * @return String representation of the action.
     * @throws IllegalInputPotongException If the index is out of bounds.
     */
    public String unmark(int index) throws IllegalInputPotongException {
        if (index - 1 >= this.arr.size() || index - 1 < 0) {
            throw new IllegalInputPotongException(String.format("Your index %d is out of bounds!", index));
        }
        return this.arr.get(index - 1).unmark();
    }

    /**
     * Delete a task in the list.
     *
     * @param index Index of the task.
     * @return String representation of the action.
     * @throws IllegalInputPotongException If the index is out of bounds.
     */
    public String delete(int index) throws IllegalInputPotongException {
        if (index - 1 >= this.arr.size() || index - 1 < 0) {
            throw new IllegalInputPotongException(String.format("Your index %d is out of bounds!", index));
        }
        Task removed = this.arr.remove(index - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list.",
                removed, this.arr.size());
    }

    /**
     * Tag the task in the list
     *
     * @param tag String representation of the tag.
     * @param index Index of task to be tagged.
     * @return String representation of this action.
     * @throws IllegalInputPotongException If the index is out of bounds.
     */
    public String tag(String tag, int index) throws IllegalInputPotongException {
        if (index - 1 >= this.arr.size() || index - 1 < 0) {
            throw new IllegalInputPotongException(String.format("Your index %d is out of bounds!", index));
        }
        return this.arr.get(index - 1).tag(tag);
    }

    /**
     * Untag the task in the list.
     * @param index Index of task to untag.
     * @return String representation of this action.
     * @throws IllegalInputPotongException If the index is out of bounds.
     */
    public String untag(int index) throws IllegalInputPotongException {
        if (index - 1 >= this.arr.size() || index - 1 < 0) {
            throw new IllegalInputPotongException(String.format("Your index %d is out of bounds!", index));
        }
        return this.arr.get(index - 1).untag();
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
            String tag = curr.getTag();
            if (time.isEmpty()) {
                String task = String.format("%s | %s | %s | %s\n", type, status, tag, description);
                result.append(task);
            } else {
                String task = String.format("%s | %s | %s | %s | %s\n", type, status, tag, description, time);
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
     * @throws IllegalInputPotongException If keyword is empty.
     */
    public String find(String keyword) throws IllegalInputPotongException {
        if (keyword.isEmpty()) {
            throw new IllegalInputPotongException("Keyword cannot be empty!");
        }
        AtomicInteger i = new AtomicInteger();
        return this.arr.stream().filter(task -> task.findKeyword(keyword))
                .map(task -> String.format("%d. %s\n", i.incrementAndGet(), task))
                .reduce("Here are the matching tasks in your list:\n", (a, b) -> a + b);
    }

    /**
     * String representation of the list of tasks.
     *
     * @return String representation of this list.
     */
    @Override
    public String toString() {
        if (arr.isEmpty()) {
            return "You have nothing in the list!";
        }
        AtomicInteger i = new AtomicInteger();
        return this.arr.stream().map(task -> String.format("%d. %s\n", i.incrementAndGet(), task))
                .reduce("Here are the tasks in your list:\n", (a, b) -> a + b);
    }
}
