package Stobberi.components;

import Stobberi.StobberiException.StobberiException;
import Stobberi.Task.Deadline;
import Stobberi.Task.Event;
import Stobberi.Task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks. Provides methods to manage, display, and filter tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Marks a task as done by its number in the list.
     *
     * @param number The number of the task to mark as done.
     */
    public void markTask(int number) {
        listOfTasks.get(number - 1).setDone();
        String done = "Nice! I've marked this task as done:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        Ui.displayForm(done);
    }

    /**
     * Marks a task as not done by its number in the list.
     *
     * @param number The number of the task to mark as not done.
     */
    public void unmarkTask(int number) {
        listOfTasks.get(number - 1).setNotDone();
        String done = "OK, I've marked this task as not done yet:\n" +
                "  ";
        done += listOfTasks.get(number - 1).toString();
        Ui.displayForm(done);
    }

    /**
     * Displays the list of all tasks.
     */
    public void displayList() {
        String list = "Here are the tasks in your list:";
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            list += "\n" + i + ". " + listOfTasks.get(i - 1);
        }
        Ui.displayForm(list);
    }

    /**
     * Deletes a task by its number in the list.
     *
     * @param number The number of the task to delete.
     */
    public void delete(int number) {
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
        String done = "Noted. I've removed this task:\n" +
                "  " + temp
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        Ui.displayForm(done);
    }

    /**
     * Displays the last added task.
     */
    public void displayLastAdded() {
        Ui.displayForm(
                "Got it. I've added this task:\n    "
                        + listOfTasks.get(listOfTasks.size() - 1)
                        + "Now you have " + listOfTasks.size() + " in the list.");
    }
<<<<<<< HEAD
    public void filterListByWord(String word) {
        String list = "Here are the matching tasks in your list:";
        int n = 1;
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            Task task = listOfTasks.get(i - 1);
            if (task.hasWord(word)) {
                list += "\n" + n + ". " + task;
                n++;
            }
        }
        Ui.displayForm(list);
    }
=======

    /**
     * Filters and displays tasks by the specified date.
     *
     * @param date The date to filter tasks by.
     */
>>>>>>> branch-A-JavaDoc
    public void filterListByDate(String date) {
        String list = "Here are the tasks in your list that you have to do on " + date + ":\n";
        int n = 1;
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            Task task = listOfTasks.get(i - 1);
            if (task instanceof Deadline deadline) {
                if (deadline.isDuring(date)) {
                    list += n + ". " + listOfTasks.get(i - 1) + "\n";
                    n++;
                }
            } else if (task instanceof Event event) {
                if (event.isDuring(date)) {
                    list += n + ". " + listOfTasks.get(i - 1) + "\n";
                    n++;
                }
            }
        }
        Ui.displayForm(list);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @throws StobberiException If an error occurs while adding the task.
     */
    public void addTask(Task task) throws StobberiException {
        listOfTasks.add(task);
        displayLastAdded();
    }

    /**
     * Updates the task list with a new list of tasks.
     *
     * @param taskList The new list of tasks to replace the current list.
     */
    public void updateTaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    /**
     * Returns the current list of tasks.
     *
     * @return The current list of tasks.
     */
    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }
}