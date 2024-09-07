package jude;

import jude.task.Task;
import java.util.ArrayList;
import java.util.List;

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

    public void addTask(Task task) {
        list.add(task);
    }

    /** Delete the Task by taking in its index. Throws JudeException, if the index is not valid. */
    public void deleteTask(int index) throws JudeException {
        validateIndex(index);
        list.remove(index);
    }

    public void markTask(int index) throws JudeException {
        validateIndex(index);
        list.get(index).markAsDone();
    }

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
        String text = "";
        for (Task task : list) {
            text += task.toFileFormat() + "\n";
        }
        return text;
    }

    /** Returns the String representation of the TaskList to be displayed on the Ui. */
    public String toUiFormat() {
        String string = "";
        for (int i = 0; i < list.size(); i++) {
            string += String.format("%d. %s\n", (i + 1), list.get(i).toStringDetails());
        }
        return string;
    }

    private void validateIndex(int index) throws JudeException {
        if (index < 0 || index >= list.size()) {
            throw new JudeException("You are trying to get an element of index out of the list size." + list.size());
        }
    }

    public String search(String keyword) {
        String matches = "";

        for (int i = 0; i < size(); i++) {
            Task task = list.get(i);
            if (task.toString().contains(keyword)) {
                matches += String.format("%d. %s\n", (i + 1), task.toStringDetails());
            }
        }
        return matches;
    }
}
