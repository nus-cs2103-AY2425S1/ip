package duke;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Represents a list of Task items.
 */
public class TaskList {
    private Task[] tasks;
    private int cmdNum = 0;
    TaskList(Task[] t, int  n){
        tasks = t;
        cmdNum = n;
    }

    /**
     * Adds new task to list.
     *
     * @param t Task to be added to the list.
     * @return String reply confirming updated task list.
     * */
    public String add(Task t) {
        tasks[cmdNum] = t;
        String s = "Got it. I've added this task:\n  " + get(cmdNum);
        cmdNum++;
        s+="\nNow you have " + cmdNum + " tasks in the list.";
        return s;
    }

    /**
     * Returns task at the specified position in the list.
     *
     * @param n Index of the task to be returned.
     * @return Task item requested.
     * */
    public Task get(int n) {
        assert n > 0: "Invalid task number";
        return tasks[n];
    }

    /**
     * Deletes task at that index in the list.
     *
     * @param n Index of the task to be deleted.
     * @throws DuckException if there is no task at the given index in the list.
     * @return String reply confirming item has been deleted.
     * */
    public String delete(int n) {
        assert n > 0: "Invalid task number";
        if (n >= cmdNum + 1) {
            return "There is no task with the given task number.";
        }
        String s = "Noted. I've removed this task:\n " + get(n - 1);
        ArrayList<Task> newCmds = new ArrayList<Task>(Arrays.asList(tasks));
        newCmds.remove(n - 1);
        tasks = newCmds.toArray(new Task[100]);
        n--;
        cmdNum--;
        return s + "\nNow you have " + cmdNum + " tasks in the list.";

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
        StringBuilder s = new StringBuilder("Here are the tasks in your list:");
        for (int n = 1; n <= cmdNum; n++) {
            s.append("\n").append(n).append(". ").append(get(n - 1));
        }
        return s.toString();
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
        if (num < cmdNum + 1) {
            get(num - 1).mark();
        } else {
            return "There is no task with the given task number.";
        }
        return "Nice! I've marked this task as done:\n " + get(num - 1);
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
        if (num < cmdNum + 1) {
            get(num - 1).unmark();
        } else {
            return "There is no task with the given task number.";
        }
        return "OK, I've marked this task as not done yet:\n "+ get(num - 1);
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
        for (int i = 0; i < cmdNum; i++) {
            Task t = tasks[i];
            if (t.getDescription().contains(keyword)) {
                foundTasks[numOfFoundTasks] = t;
                numOfFoundTasks++;
            }
        }
        if (numOfFoundTasks == 0) {
            return "No matching tasks in your list.";
        } else {
            StringBuilder s = new StringBuilder("Here are the matching tasks in your list:");
            for (int n = 1; n < numOfFoundTasks; n++) {
                s.append("\n").append(n).append(". ").append(foundTasks[n - 1]);
            }
            return s.toString();
        }
    }

    /**
     * Saves list of all current tasks.
     *
     * @throws DuckException if current list is corrupted.
     * */
    public void save() throws DuckException {
        Storage.save(allTasks(), cmdNum);
    }
    public int getCmdNum() {
        return cmdNum;
    }
}
