package jude;

import java.util.ArrayList;
import java.util.List;

import jude.task.Task;

/**
 * Handles storing and managing the Tasks.
 *
 * This class provides methods to add, remove, mark and unmark tasks.
 * It stores tasks in a list and allows efficient management of different task types, ensuring the users
 * to keep track of their pending tasks easily.
 */
public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) throws JudeException {
        this.list = list;
    }

    /** Add the Task by taking in its index. */
    public void addTask(Task task) {
        list.add(task);
    }

    /** Delete the Task by taking in its index. Throws JudeException, if the index is not valid. */
    public void deleteTask(int index) throws JudeException {
        validateIndex(index);
        list.remove(index);
    }

    /** Mark the Task by taking in its index. Throws JudeException, if the index is not valid. */
    public void markTask(int index) throws JudeException {
        validateIndex(index);
        list.get(index).markAsDone();
    }

    /** Unmark the Task by taking in its index. Throws JudeException, if the index is not valid. */
    public void unmarkTask(int index) throws JudeException {
        validateIndex(index);
        list.get(index).unmarkAsDone();
    }

    public Task getTask(int index) throws JudeException {
        validateIndex(index);
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    /** Returns the String representation of the TaskList to be written in the save file. */
    public String toFileFormat() {
        return list.stream().map(x -> x.toFileFormat()).reduce("", (x, y) -> y + x + "\n");
    }

    /** Returns the String representation of the TaskList to be displayed on the Ui. */
    public String toUiFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("%d. %s\n", (i + 1), list.get(i).toStringDetails()));
        }
        return sb.toString();
    }

    private void validateIndex(int index) throws JudeException {
        if (index < 0 || index >= list.size()) {
            throw new JudeException(
                    "You are trying to get an element of index out of the list size." + list.size()
            );
        }
    }

    /** Searches a task with the given keyword from the tasklist. */
    public String search(String keyword) {
        StringBuilder matches = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(keyword)) {
                matches.append(String.format("%d. %s\n", (i + 1), task.toStringDetails()));
            }
        }

        return matches.toString();
    }
}
