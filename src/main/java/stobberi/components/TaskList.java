package stobberi.components;

import java.util.ArrayList;

import stobberi.stobberiexception.InvalidNumberStobberiException;
import stobberi.stobberiexception.NoSuchTaskStobberiException;
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
     * Constructs a new {@code TaskList} with an empty list of tasks.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Marks a task as done by its number in the list.
     *
     * @param number The number of the task to mark as done.
     * @return A confirmation message indicating the task has been marked as done.
     * @throws StobberiException If the number is invalid (too large or too small).
     */
    public String markTask(int number) throws StobberiException {
        if (number > listOfTasks.size() || number < 1) {
            throw new InvalidNumberStobberiException("The number you gave is invalid!");
        }
        listOfTasks.get(number - 1).setDone();
        return "Yay! I've marked this task as done:\n" + "  " + listOfTasks.get(number - 1);
    }

    /**
     * Marks a task as not done by its number in the list.
     *
     * @param number The number of the task to mark as not done.
     * @return A confirmation message indicating the task has been marked as not done.
     * @throws StobberiException If the number is invalid (too large or too small).
     */
    public String unmarkTask(int number) throws StobberiException {
        if (number > listOfTasks.size() || number < 1) {
            throw new InvalidNumberStobberiException("The number you gave is invalid!");
        }
        listOfTasks.get(number - 1).setNotDone();
        return "OK, I've marked this task as not done:\n" + "  " + listOfTasks.get(number - 1);
    }

    /**
     * Displays the list of all tasks.
     *
     * @return A string listing all tasks.
     * @throws StobberiException If there are no tasks to display.
     */
    public String displayList() throws StobberiException {
        if (listOfTasks.isEmpty()) {
            throw new NoSuchTaskStobberiException("There are no tasks in the list.");
        }

        StringBuilder list = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i++) {
            list.append("\n").append(i + 1).append(". ").append(listOfTasks.get(i));
        }
        return list.toString();
    }

    /**
     * Deletes a task by its number in the list.
     *
     * @param number The number of the task to delete.
     * @return A confirmation message indicating the task has been deleted.
     * @throws StobberiException If the number is invalid (too large or too small).
     */
    public String delete(int number) throws StobberiException {
        if (number > listOfTasks.size() || number < 1) {
            throw new InvalidNumberStobberiException("The number you gave is invalid!");
        }
        Task removedTask = listOfTasks.remove(number - 1);
        return "Ookiiee! This task is now gone:\n" + "  " + removedTask + "\nNoww you have " + listOfTasks.size() + " tasks in the list.";
    }

    /**
     * Displays the last added task.
     *
     * @return A string confirming the last added task.
     */
    public String displayLastAddedTask() {
        if (listOfTasks.isEmpty()) {
            return "No tasks available.";
        }
        return "Yayyy! I've added a new task:\n    " + listOfTasks.get(listOfTasks.size() - 1) + "\nNoww you have " + listOfTasks.size() + " in the list.";
    }

    /**
     * Filters and displays tasks that contain the specified word in their description.
     *
     * @param word The word to search for in the task descriptions.
     * @return A string listing tasks that match the keyword.
     * @throws StobberiException If no tasks contain the specified word.
     */
    public String filterListByWord(String word) throws StobberiException {
        StringBuilder list = new StringBuilder("Here are the matching tasks in your list:");
        int count = 0;

        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if (task.hasWord(word)) {
                list.append("\n").append(++count).append(". ").append(task);
            }
        }

        if (count == 0) {
            throw new NoSuchTaskStobberiException("I can't find any tasks with: " + word);
        }

        return list.toString();
    }

    /**
     * Filters and displays tasks by the specified date.
     *
     * @param date The date to filter tasks by.
     * @return A string listing tasks that occur on the specified date.
     * @throws StobberiException If no tasks occur on the specified date.
     */
    public String filterListByDate(String date) throws StobberiException {
        StringBuilder list = new StringBuilder("Here are the tasks on " + date + ":");
        int count = 0;

        for (int i = 0; i < listOfTasks.size(); i++) {
            Task task = listOfTasks.get(i);
            if ((task instanceof Deadline deadline && deadline.isDuring(date)) ||
                    (task instanceof Event event && event.isDuring(date))) {
                list.append("\n").append(++count).append(". ").append(task);
            }
        }

        if (count == 0) {
            throw new NoSuchTaskStobberiException("I can't find any task on " + date);
        }
        return list.toString();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     * @return A confirmation message indicating the task has been added.
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

    /**
     * Checks if a task with the specified description exists in the list.
     *
     * @param description The description of the task to check.
     * @return True if a task with the description exists, otherwise false.
     */
    public boolean hasTask(String description) {
        return listOfTasks.stream().anyMatch(task -> task.isSame(description));
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
