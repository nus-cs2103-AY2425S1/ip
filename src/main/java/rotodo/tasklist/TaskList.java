package rotodo.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import rotodo.exception.InvalidInputException;

public class TaskList {
    public static TaskList taskList;

    /**
     * List of tasks.
     */
    private List<Task> list;

    private TaskList() {
        list = new ArrayList<>();
    }

    public static TaskList of() {
        if (taskList != null) {
            return taskList;
        }
        taskList = new TaskList();
        return taskList;
    }

    /**
     * Mark the i'th Task as 'done'.
     * 
     * @param i task index
     * @return i'th task state
     */
    public String markDone(int i) throws InvalidInputException {
        if (i >= list.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\n"
                + "\u001B[0m    type 'list' to view tasklist");
        }
        Task done = list.get(i);
        return done.markAsDone();
    }

    /**
     * Unmark the i'th Task as 'done'.
     * 
     * @param i task index
     * @return i'th task state
     */
    public String unmarkDone(int i) throws InvalidInputException {
        if (i >= list.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\n"
                + "\u001B[0m    type 'list' to view tasklist");
        }
        Task done = list.get(i);
        return done.unmarkAsDone();
    }

    /**
     * Overloaded function addTask(). Adds tasks to 
     * the task list.
     * 
     * @param value task description
     * @return The action completed
     */
    public String addTask(String value) {
        Task toAdd = new Todo(value);
        list.add(toAdd);
        return "    Good good! RoTodo is happy to add:\n      " + toAdd
            + "\n    Now you have " + list.size() + " tasks in the list.";
    }

    public String addTask(String value, String by) {
        Task toAdd = new Deadline(value, by);
        list.add(toAdd);
        return "    Good good! RoTodo is happy to add:\n      " + toAdd
            + "\n    Now you have " + list.size() + " tasks in the list.";
    }

    public String addTask(String value, String from, String to) {
        Task toAdd = new Event(value, from, to);
        list.add(toAdd);
        return "    Good good! RoTodo is happy to add:\n      " + toAdd
            + "\n    Now you have " + list.size() + " tasks in the list.";
    }

    public void addTask(String ...value) {
        Task toAdd = null;
        switch (value.length) {
            case 2:
                toAdd = new Todo(value);
                 break;

            case 3:
                toAdd = new Deadline(value);
                break;

            case 4:
                toAdd = new Event(value);
                break;
        }
        if (toAdd != null) list.add(toAdd);
    }

    public String deleteTask(int i) throws InvalidInputException {
        if (i >= list.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\n"
                + "\u001B[0m    type 'list' to view tasklist");
        }
        Task removed = list.remove(i);
        return "    Deleting task? That's cheating... but whatever... removed:\n      " + removed
            + "\n    Now you have " + list.size() + " tasks in the list.";
    }

    public String saveList() throws IOException {
        String output = "";
        for (Task t : list) {
            output += t.saveString() + "\n";
        }
        return output.strip();
    }

    /**
     * String representation of TaskList.
     * 
     * @return The list of tasks
     */
    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < list.size(); i++) {
            output += "    " + (i + 1) + "." + list.get(i) + "\n";
        }
        if (output == "") {
            output = "    Nothing here. Go find more task to do!";
        }
        return output.stripTrailing();
    }
}