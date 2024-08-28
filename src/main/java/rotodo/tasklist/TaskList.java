package rotodo.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.LocalDateTime;

import rotodo.exception.InvalidInputException;

public class TaskList {
    public static TaskList taskList;
    private boolean nextStatus;

    /**
     * List of tasks.
     */
    private List<Task> list;

    public TaskList() {
        list = new ArrayList<>();
        nextStatus = false;
    }

    /**
     * Mark the i'th Task as 'done'.
     * 
     * @param i task index
     * @return i'th task state
     */
    public String markDone(int i) throws InvalidInputException {
        if (i >= list.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                + "type 'list' to view tasklist");
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
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                + "type 'list' to view tasklist");
        }
        Task done = list.get(i);
        return done.unmarkAsDone();
    }
    
    public void setNextStatus(boolean status) {
        nextStatus = status;
    }

    /**
     * Overloaded function addTask(). Adds tasks to 
     * the task list.
     * 
     * @param value task description
     * @return The action completed
     */
    public String addTask(String value) {
        Task toAdd = new Todo(value, nextStatus);
        nextStatus = false;
        list.add(toAdd);
        return "Good good! RoTodo is happy to add:\n  " + toAdd
            + "\nNow you have " + list.size() + " tasks in the list.";
    }

    public String addTask(String value, LocalDateTime by) {
        Task toAdd = new Deadline(value, by, nextStatus);
        nextStatus = false;
        list.add(toAdd);
        return "Good good! RoTodo is happy to add:\n  " + toAdd
            + "\nNow you have " + list.size() + " tasks in the list.";
    }

    public String addTask(String value, LocalDateTime from, LocalDateTime to) {
        Task toAdd = new Event(value, from, to, nextStatus);
        nextStatus = false;
        list.add(toAdd);
        return " Good good! RoTodo is happy to add:\n  " + toAdd
            + "\n Now you have " + list.size() + " tasks in the list.";
    }

    public String deleteTask(int i) throws InvalidInputException {
        if (i >= list.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                + "type 'list' to view tasklist");
        }
        Task removed = list.remove(i);
        return "Deleting task? That's cheating... but whatever... removed:\n  " + removed
            + "\nNow you have " + list.size() + " tasks in the list.";
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
            output += "" + (i + 1) + "." + list.get(i) + "\n";
        }
        if (output == "") {
            output = "Nothing here. Go find more task to do!";
        }
        return output.stripTrailing();
    }
}