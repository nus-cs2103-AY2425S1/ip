package nah.tasklist;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import nah.data.Task;
import nah.exceptions.NahException;

/**
 * Handles the changes to the list of Tasks.
 */
public class TaskList {
    private LinkedList<Task> tasks;
    private int taskCount = 0;

    /**
     * Creates new TaskList based on a LinkList of tasks.
     * @param tasks the LinkList of task
     */
    public TaskList(LinkedList<Task> tasks) {

        this.tasks = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Creates new TaskList with empty LinkList of tasks.
     */
    public TaskList() {
        this.tasks = new LinkedList<Task>();
    }

    /**
     * Returns number of tasks in TaskList.
     *
     * @return an int
     */
    public int taskCount() {
        return this.taskCount;
    }

    /**
     * Adds new Task to the TaskList.
     *
     * @param newTask new Task to be added
     * @return a description of the action, that will be delivered to a UI
     */
    public String add(Task newTask) {
        tasks.add(newTask);
        taskCount++;
        assert taskCount == tasks.size() : "taskCount is wrong";
        return " Got it. I've added this task:\n"
                + "   " + newTask.toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
    }

    /**
     * Marks task i as done.
     *
     * @param i the order of the task
     * @return a description of the action, that will be delivered to a UI
     * @throws NahException.InvalidTaskNumberException if there is no task that has order i
     */
    public String mark(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
        tasks.get(i - 1).mark();
        return " Nice! I've marked this task as done:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Marks the task i as not done.
     *
     * @param i the order of the task
     * @return a description of the action, that will be delivered to a UI
     * @throws NahException.InvalidTaskNumberException if there is no task that has order i
     */
    public String unMark(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i > taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
        tasks.get(i - 1).unMark();
        return " OK, I've marked this task as not done yet:\n"
                + "   " + tasks.get(i - 1).toString();

    }

    /**
     * Delete task i.
     *
     * @param i the order of the task
     * @return a description of the action, that will be delivered to a UI
     * @throws NahException.InvalidTaskNumberException if there is no task that has order i
     */
    public String delete(int i) throws NahException.InvalidTaskNumberException {
        if (taskCount == 0) {
            throw new NahException.InvalidTaskNumberException();
        }
        if (i <= 0 || i >= taskCount) {
            throw new NahException.InvalidTaskNumberException(i, taskCount);
        }
        taskCount--;
        assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
        String s = " Noted. I've removed this task:\n"
                + "   " + tasks.get(i - 1).toString() + "\n"
                + " Now you have " + taskCount + " tasks in the list.\n";
        tasks.remove(i - 1);
        return s;
    }

    /**
     * Returns String representation of all tasks in the TaskList.
     *
     * @return a String
     */
    public String readTask() {
        if (this.taskCount == 0) {
            return " There is no task in your list!\n";
        }
        String s = " Here are the tasks in your list:\n";
        for (int i = 1; i <= taskCount; i++) {
            assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
            s += " " + i + ". " + tasks.get(i - 1).toString() + "\n";
        }
        return s;
    }

    /**
     * Return String representation of tasks in the list that are before due.
     *
     * @param time String representation of the due
     * @return a description of the action, that will be delivered to a UI
     */
    public String dueOn(String time) {
        LocalDateTime due = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        String s = " Here are the tasks in your list that ends before the due:\n";
        int i = 1;
        for (Task t : tasks) {
            if (t instanceof Task.Deadlines || t instanceof Task.Events) {
                assert t != null : "task at position " + i + "should not be null";
                if (t.endTime().isBefore(due) && !t.isDone()) {
                    s += " " + i + ". " + t + "\n";
                }

            }
            i++;
        }
        return s;
    }

    /**
     * A brief description of TaskList to store in a Storage.
     *
     * @return a String
     */
    public String brief() {
        String s = "";
        for (int i = 1; i <= taskCount; i++) {
            assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
            s += tasks.get(i - 1).brief() + "\n";
        }
        return s;
    }

    /**
     * Clears the TaskList.
     *
     * @return a description of the action, that will be delivered to a UI
     */
    public String clean() {
        this.tasks.clear();
        this.taskCount = 0;
        return " Got it. I've cleaned the storage. Now you have no tasks in the list.\n";
    }

    /**
     * Returns a list of task that description partially match a String.
     * This String can have 0, 1 or more than 2 words.
     *
     * @param s the String to be checked
     * @return a description of the action, that will be delivered to a UI
     */
    public String find(String s) {
        assert s != null : "string for finding should not be null";
        String ret = "";
        if (s.trim().isEmpty()) {
            ret += " Please specify keywords. I show you all the tasks\n";
        } else {
            ret += " Oke. Here are the task that match keywords:\n";
        }
        for (int i = 1; i <= taskCount; i++) {
            assert tasks.get((i - 1)) != null : "task at position " + i + "should not be null";
            Task t = tasks.get(i - 1);
            if (t.isOneMatch(s)) {
                ret += " " + i + ". " + t.toString() + "\n";
            }

        }
        return ret;

    }

}
