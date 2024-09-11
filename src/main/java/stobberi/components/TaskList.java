package stobberi.components;

import java.util.ArrayList;

import stobberi.stobberiexception.InvalidNumberStobberiException;
import stobberi.stobberiexception.StobberiException;
import stobberi.task.Deadline;
import stobberi.task.Event;
import stobberi.task.Task;

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
    public String markTask(int number) throws StobberiException {
        if (number > listOfTasks.size()) {
            throw new InvalidNumberStobberiException("I'm sorry. The number you gave is too big!");
        }
        if (number < 1) {
            throw new InvalidNumberStobberiException("I'm sorry. The number you gave is too small!");
        }
        listOfTasks.get(number - 1).setDone();
        String done = "Nice! I've marked this task as done:\n"
                + "  ";
        done += listOfTasks.get(number - 1).toString();
        return done;
    }

    /**
     * Marks a task as not done by its number in the list.
     *
     * @param number The number of the task to mark as not done.
     */
    public String unmarkTask(int number) throws StobberiException {
        if (number > listOfTasks.size()) {
            throw new InvalidNumberStobberiException("I'm sorry. The number you gave is too big!");
        }
        if (number < 1) {
            throw new InvalidNumberStobberiException("I'm sorry. The number you gave is too small!");
        }
        listOfTasks.get(number - 1).setNotDone();
        String done = "OK, I've marked this task as not done yet:\n"
                + "  ";
        done += listOfTasks.get(number - 1).toString();
        return done;
    }

    /**
     * Displays the list of all tasks.
     */
    public String displayList() {
        String list = "Here are the tasks in your list:";
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            list += "\n" + i + ". " + listOfTasks.get(i - 1);
        }
        return list;
    }

    /**
     * Deletes a task by its number in the list.
     *
     * @param number The number of the task to delete.
     */
    public String delete(int number) {
        Task temp = listOfTasks.get(number - 1);
        listOfTasks.remove(number - 1);
        String done = "Noted. I've removed this task:\n"
                + "  " + temp
                + "\nNow you have " + listOfTasks.size() + " tasks in the list.";
        return done;
    }

    /**
     * Displays the last added task.
     */
    public String displayLastAddedTask() {
        return "Got it. I've added this task:\n    "
                + listOfTasks.get(listOfTasks.size() - 1)
                + "\n"
                + "Now you have " + listOfTasks.size() + " in the list.";
    }

    /**
     * Filters and displays tasks that contain the specified word in their description.
     *
     * @param word the word to search for in the task descriptions
     */
    public String filterListByWord(String word) {
        String list = "Here are the matching tasks in your list:";
        int n = 1;
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            Task task = listOfTasks.get(i - 1);
            if (task.hasWord(word)) {
                list += "\n" + n + ". " + task;
                n++;
            }
        }
        return list;
    }
    /**
     * Filters and displays tasks by the specified date.
     *
     * @param date The date to filter tasks by.
     */
    public String filterListByDate(String date) {
        String list = "Here are the tasks in your list that you have to do on " + date + ":\n";
        int n = 1;
        for (int i = 1; i < listOfTasks.size() + 1; i++) {
            Task task = listOfTasks.get(i - 1);
            if (task instanceof Deadline deadline && deadline.isDuring(date)) {
                list += n + ". " + listOfTasks.get(i - 1) + "\n";
                n++;
            } else if (task instanceof Event event && event.isDuring(date)) {
                list += n + ". " + listOfTasks.get(i - 1) + "\n";
                n++;
            }
        }
        return list;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @throws StobberiException If an error occurs while adding the task.
     */
    public String addTask(Task task) {
        listOfTasks.add(task);
        return displayLastAddedTask();
    }

    /**
     * Updates the task list with a new list of tasks.
     *
     * @param taskList The new list of tasks to replace the current list.
     */
    public void updateTaskList(ArrayList<Task> taskList) {
        this.listOfTasks = taskList;
    }

    public boolean hasTask(String description) {
        return listOfTasks
                .stream()
                .map(task -> task.isSame(description))
                .reduce(false, (hasString, element) -> hasString || element);
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
