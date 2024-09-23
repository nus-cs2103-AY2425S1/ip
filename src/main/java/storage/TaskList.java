package storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

/**
 * Manages a list of tasks, including adding, removing, and marking tasks as done or undone.
 * The TaskList class is responsible for handling all operations on the tasks stored in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MM uuuu");

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param list the list of tasks to be managed
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Adds a task to the task list.
     * The task can be a Todo, Deadline, or Event depending on the length of the input array.
     * Validations include duplicate checks and date format parsing for Deadline and Event types.
     *
     * @param task an array representing the task details; the first element is the task name,
     *             followed by optional dates depending on task type
     * @return a response message indicating the result of the addition, including success or error messages
     */
    public String addTask(String[] task) {
        String response = "";
        if (isDuplicate(task[0])) {
            response = "Warning! You already have a task named: " + task[0] + "\n";
        }
        if (task.length == 1) {
            this.tasks.add(new Todo(task[0]));
            return response + "Okay, I've added a todo: " + task[0];
        }
        try {
            if (task.length == 2) {
                this.tasks.add(new Deadline(task[0], getDate(task[1])));
                return response + "Okay, I've added a deadline: " + task[0];
            }
            if (task.length == 3) {
                LocalDate start = getDate(task[1]);
                LocalDate end = getDate(task[2]);
                if (start.isAfter(end)) {
                    return "The start date is after the end date! Did you key in incorrectly?";
                }
                this.tasks.add(new Event(task[0], start, end));
                return response + "Okay, I've added an event: " + task[0];
            }
        } catch (DateTimeParseException e) {
            return "Invalid date format! Please follow dd mm yyyy format! e.g 26 06 2002";
        }
        return "Error adding task! Did you make sure to key in the right format?";
    }

    /**
     * Removes a task from the task list based on its index.
     * The task is removed from the list, and a confirmation message is displayed.
     *
     * @param task the index of the task to be removed
     * @return a response message confirming successful removal of the task
     */
    public String removeTask(int task) {
        Task temp = tasks.get(task);
        this.tasks.remove(task);

        String response = "Successfully removed: " + temp.getName();
        return response + "\nYou now have " + getSize() + " total tasks left.";
    }

    /**
     * Returns a string representation of the task list, including the number of completed,
     * incomplete, and total tasks.
     *
     * @return a string representation of the task list
     */
    @Override
    public String toString() {
        String ans = "";
        ans += String.format("Completed: %d tasks | Incomplete: %d tasks | Total: %d tasks%n%n",
                countCompleted(true), countCompleted(false), tasks.size());
        assert countCompleted(true) + countCompleted(false) == tasks.size() : "Invalid completion breakdown";
        for (int i = 1; i <= tasks.size(); i++) {
            ans += String.format("%d: %s%n", i, tasks.get(i - 1).toString());
        }
        return ans;
    }

    /**
     * Returns the size of the task list.
     *
     * @return the number of tasks in the list
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the list of tasks managed by this TaskList.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Marks a task as done or undone based on the specified action.
     * The user can choose to mark a task as done or undone, and a confirmation message is displayed.
     *
     * @param action the action to perform ("mark" for done, any other value for undone)
     * @param task   the index of the task to be marked
     * @return a response message confirming the task's status change
     */
    public String setTaskCompletion(String action, int task) {
        Task temp = tasks.get(task);
        if (action.equalsIgnoreCase("mark")) {
            if (!temp.isDone()) {
                temp.setDone();
            }
            return "Good job! Marked as done :)";
        } else {
            if (temp.isDone()) {
                temp.setUndone();
            }
            return "Oh man! Marked as undone :(";
        }
    }

    /**
     * Parses a user-input date string and returns it as a LocalDate object.
     * The input is expected in the format "dd MM uuuu".
     * If the input format is invalid, a DateTimeParseException is thrown.
     *
     * @param date the date string to be parsed
     * @return the parsed LocalDate object
     */
    public LocalDate getDate(String date) {
        return LocalDate.parse(date, this.inputFormatter);
    }

    /**
     * Counts the number of tasks that are either completed or incomplete.
     *
     * @param isComplete true to count completed tasks, false to count incomplete tasks
     * @return the number of tasks that match the specified completion status
     */
    public int countCompleted(boolean isComplete) {
        int count = 0;
        for (Task task : this.tasks) {
            if (task.isDone() == isComplete) {
                count++;
            }
        }
        assert count >= 0 : "Invalid number of completed tasks";
        return count;
    }

    /**
     * Checks if a task with the specified name already exists in the task list.
     * The method returns true if a duplicate task is found, false otherwise.
     *
     * @param target the name of the task to check for duplicates
     * @return true if a duplicate task is found, false otherwise
     */
    public boolean isDuplicate(String target) {
        for (Task task : this.tasks) {
            if (task.getName().equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
}
