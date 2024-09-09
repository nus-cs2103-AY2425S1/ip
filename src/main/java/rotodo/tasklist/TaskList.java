package rotodo.tasklist;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import rotodo.exception.InvalidInputException;

/**
 * This class implements the TaskList. The list can
 * be interacted and modified through provided functions.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class TaskList {
    private boolean nextIsDoneStatus;

    /**
     * List of tasks.
     */
    private List<Task> tasks;

    /**
     * Initialise tasklist
     */
    public TaskList() {
        tasks = new ArrayList<>();
        nextIsDoneStatus = false;
    }

    /**
     * Marks the i'th Task as 'done'.
     *
     * @param i task index
     * @return i'th task state
     */
    public String markDone(int i) throws InvalidInputException {
        if (i >= tasks.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                    + "type 'list' to view tasklist");
        }
        Task done = tasks.get(i);
        return done.markAsDone();
    }

    /**
     * Unmarks the i'th Task as 'done'.
     *
     * @param i task index
     * @return i'th task state
     */
    public String unmarkDone(int i) throws InvalidInputException {
        if (i >= tasks.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                    + "type 'list' to view tasklist");
        }
        Task done = tasks.get(i);
        return done.unmarkAsDone();
    }

    public void setNextStatus(boolean status) {
        nextIsDoneStatus = status;
    }

    /**
     * Adds new Todo task to tasklist
     *
     * @param value description of task
     * @return status to be printed by Ui
     */
    public String addTask(String value) {
        Task toAdd = new Todo(value, nextIsDoneStatus);
        nextIsDoneStatus = false;
        tasks.add(toAdd);
        return "Good good! RoTodo is happy to add:\n  " + toAdd
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds new Deadline task to tasklist
     *
     * @param value description of task
     * @param by datetime
     * @return status to be printed by Ui
     */
    public String addTask(String value, LocalDateTime by) {
        Task toAdd = new Deadline(value, by, nextIsDoneStatus);
        nextIsDoneStatus = false;
        tasks.add(toAdd);
        return "Good good! RoTodo is happy to add:\n  " + toAdd
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Adds new Event task to tasklist.
     *
     * @param value description of task
     * @param from datetime
     * @param to datetime
     * @return status to be printed by Ui
     */
    public String addTask(String value, LocalDateTime from, LocalDateTime to) {
        Task toAdd = new Event(value, from, to, nextIsDoneStatus);
        nextIsDoneStatus = false;
        tasks.add(toAdd);
        return " Good good! RoTodo is happy to add:\n  " + toAdd
                + "\n Now you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes the i'th task.
     *
     * @param i task index
     * @return status to be printed by Ui
     * @throws InvalidInputException
     */
    public String deleteTask(int i) throws InvalidInputException {
        if (i >= tasks.size() || i < 0) {
            throw new InvalidInputException("Task number doesn't exist\u001B[0m\n"
                    + "type 'list' to view tasklist");
        }
        Task removed = tasks.remove(i);
        return "Deleting task? That's cheating... but whatever... removed:\n  " + removed
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Finds task using keyword.
     *
     * @param keyword
     * @return output list of tasks found
     */
    public String findString(String keyword) {
        String output = "RoTodo worked hard, here's the list of matching task:\n";
        String output2 = "";
        int count = 1;
        for (Task t : tasks) {
            if (t.matchDescription(keyword, true)) {
                output += count + "." + t.toString() + "\n";
                count++;
            } else if (t.matchDescription(keyword, false)) {
                output2 += "%d." + t.toString() + "\n";
            }
        }
        if (count == 1) {
            return "Rotodo worked hard, but unable to find matching tasks...";
        }
        output2 = String.format(output2,
            (Object[]) IntStream.range(count, tasks.size() + 1).mapToObj(x -> x).toArray());
        return (output + output2).stripTrailing();
    }

    /**
     * Retrieves the saveString format of all task in
     * tasklist.
     *
     * @return saveString format of the TaskList
     * @throws IOException
     */
    public String saveList() throws IOException {
        String output = "";
        for (Task t : tasks) {
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
        for (int i = 0; i < tasks.size(); i++) {
            output += "" + (i + 1) + "." + tasks.get(i) + "\n";
        }
        if (output == "") {
            output = "Nothing here. Go find more task to do!";
        }
        return output.stripTrailing();
    }
}
