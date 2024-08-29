package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private Task[] tasks;
    public int cmdNum = 0;
    TaskList(Task[] t, int  n){
        tasks = t;
        cmdNum = n;
    }

    /**
     * Adds new task to list.
     *
     * @param t Task to be added to the list.
     * */
    public void add(Task t) {
        tasks[cmdNum] = t;
        Ui.printText("Got it. I've added this task:\n  " + get(cmdNum));
        cmdNum++;
        Ui.printText("Now you have " + cmdNum + " tasks in the list.");
    }

    /**
     * Returns task at the specified position in the list.
     *
     * @param n Index of the task to be returned.
     * */
    public Task get(int n) {
        return tasks[n];
    }

    /**
     * Deletes task at that index in the list.
     *
     * @param n Index of the task to be deleted.
     * @throws DuckException if there is no task at the given index in the list.
     * */
    public void delete(int n) throws DuckException {
        if (n >= cmdNum + 1) {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("Noted. I've removed this task:\n "+ get(n-1));
        ArrayList<Task> newCmds = new ArrayList<Task>(Arrays.asList(tasks));
        newCmds.remove(n-1);
        tasks = newCmds.toArray(new Task[100]);
        n--;
        cmdNum--;
        Ui.printText("Now you have " + cmdNum + " tasks in the list.");
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
     * Prints all the tasks in the list.
     * */
    public void printTasks() {
        Ui.printText("Here are the tasks in your list:");
        for (int n = 1; n<=cmdNum; n++) {
            System.out.println(n + ". " + get(n - 1));
        }
    }

    /**
     * Marks task at that index in the list as done.
     *
     * @param num Index of the task to be marked as done.
     * @throws DuckException if there is no task at the given index in the list.
     * */
    public void mark(int num) throws DuckException {
        if (num < cmdNum + 1) {
            get(num-1).mark();
        } else {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("Nice! I've marked this task as done:\n "+ get(num-1));
    }

    /**
     * Marks task at that index in the list as not done.
     *
     * @param num Index of the task to be marked as not done.
     * @throws DuckException if there is no task at the given index in the list.
     * */
    public void unmark(int num) throws DuckException {
        if (num < cmdNum + 1) {
            get(num-1).unmark();
        } else {
            throw new DuckException("There is no task with the given task number.");
        }
        Ui.printText("OK, I've marked this task as not done yet:\n "+ get(num-1));
    }

    /**
     * Saves list of all current tasks.
     *
     * @throws DuckException if current list is corrupted.
     * */
    public void save() throws DuckException {
        Storage.save(allTasks(), cmdNum);
    }
}
