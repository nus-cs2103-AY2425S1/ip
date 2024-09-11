package duke;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Represents a list of Task items.
 */
public class TaskList {
    private Task[] tasks;
    private int numberOfTasks = 0;
    private static final int TASK_LIST_SIZE = 100;

    private static final String REPLY_INVALID_TASK_NUMBER = "There is no task with the given task number.";
    private static final String REPLY_ADDED = "Got it. I've added this task:\n  ";
    private static final String REPLY_DELETED = "Noted. I've removed this task:\n ";
    private static final String REPLY_MARKED = "Nice! I've marked this task as done:\n ";
    private static final String REPLY_UNMARKED = "OK, I've marked this task as not done yet:\n ";
    private static final String REPLY_LIST = "Here are the tasks in your list:";
    private static final String REPLY_SUCCESSFUL_FIND = "Here are the matching tasks in your list:";
    private static final String REPLY_UNSUCCESSFUL_FIND = "No matching tasks in your list.";

    TaskList(Task[] allTasks, int numberTasks){
        tasks = allTasks;
        numberOfTasks = numberTasks;
    }

    /**
     * Adds new task to list.
     *
     * @param task Task to be added to the list.
     * @return String reply confirming updated task list.
     * */
    public String add(Task task) {
        tasks[numberOfTasks] = task;
        String reply = REPLY_ADDED + get(numberOfTasks);
        numberOfTasks++;
        reply += "\nNow you have " + numberOfTasks + " tasks in the list.";
        return reply;
    }

    /**
     * Returns task at the specified position in the list.
     *
     * @param index Index of the task to be returned.
     * @return Task item requested.
     * */
    public Task get(int index) {
        assert index > 0: "Invalid task number";
        return tasks[index];
    }

    /**
     * Deletes task at that index in the list.
     *
     * @param index Index of the task to be deleted.
     * @throws DuckException if there is no task at the given index in the list.
     * @return String reply confirming item has been deleted.
     * */
    public String delete(int index) {
        assert index > 0: "Invalid task number";
        if (index >= numberOfTasks + 1) {
            return REPLY_INVALID_TASK_NUMBER;
        }
        String reply = REPLY_DELETED + get(index - 1);
        ArrayList<Task> newCmds = new ArrayList<Task>(Arrays.asList(tasks));
        newCmds.remove(index - 1);
        tasks = newCmds.toArray(new Task[TASK_LIST_SIZE]);
        numberOfTasks--;
        return reply + "\nNow you have " + numberOfTasks + " tasks in the list.";

    }

    /**
     * Returns all tasks in the list.
     *
     * @return Array of all tasks in the list.
     * */
    public Task[] allTasks(){
        return tasks;
    }

    /**
     * Returns list of all tasks in the list.
     *
     * @return String of all tasks in the list.
     * */
    public String getAllTasks() {
        StringBuilder reply = new StringBuilder(REPLY_LIST);
        for (int n = 1; n <= numberOfTasks; n++) {
            reply.append("\n").append(n).append(". ").append(get(n - 1));
        }
        return reply.toString();
    }

    /**
     * Marks task at that index in the list as done.
     *
     * @param num Index of the task to be marked as done.
     * @throws DuckException if there is no task at the given index in the list.
     * @return String confirming task has been marked.
     * */
    public String mark(int num) {
        assert num > 0: "Invalid task number";
        if (num > numberOfTasks) {
            return REPLY_INVALID_TASK_NUMBER;
        }
        get(num - 1).mark();
        return REPLY_MARKED + get(num - 1);
    }

    /**
     * Marks task at that index in the list as not done.
     *
     * @param num Index of the task to be marked as not done.
     * @throws DuckException if there is no task at the given index in the list.
     * @return String confirming task has been unmarked.
     * */
    public String unmark(int num) {
        assert num > 0: "Invalid task number";
        if (num > numberOfTasks) {
            return REPLY_INVALID_TASK_NUMBER;
        }
        get(num - 1).unmark();
        return REPLY_UNMARKED + get(num - 1);
    }

    /**
     * Prints all tasks in the list with the given keyword.
     *
     * @param keyword String to find in all the tasks.
     * @return String representation of all matching tasks found.
     * */
    public String find(String keyword) {
        Task[] foundTasks = new Task[100];
        int numOfFoundTasks = 0;

        for (int i = 0; i < numberOfTasks; i++) {
            Task t = tasks[i];
            if (t.getDescription().contains(keyword)) {
                foundTasks[numOfFoundTasks] = t;
                numOfFoundTasks++;
            }
        }
        if (numOfFoundTasks == 0) {
            return REPLY_UNSUCCESSFUL_FIND;
        }
        StringBuilder reply = new StringBuilder(REPLY_SUCCESSFUL_FIND);
        for (int n = 1; n < numOfFoundTasks; n++) {
            reply.append("\n").append(n).append(". ").append(foundTasks[n - 1]);
        }
        return reply.toString();
    }

    /**
     * Saves list of all current tasks.
     *
     * @throws DuckException if current list is corrupted.
     * */
    public void save() throws DuckException {
        Storage.save(allTasks(), numberOfTasks);
    }
    public int getCmdNum() {
        return numberOfTasks;
    }
}
