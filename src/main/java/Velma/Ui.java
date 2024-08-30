package Velma;

import java.util.ArrayList;
import Velma.task.Task;
import Velma.task.TaskList;
import Velma.task.Event;
import Velma.task.Deadline;
import java.time.LocalDate;

public class Ui {
    /**
     * Prints welcome message when Velma is started.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Velma\nWhat can I do for you?");
    }

    /**
     * Prints goodbye message when Velma is exited.
     */
    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints error message when loading file fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    /**
     *  Prints message when task is added.
     * @param task - specify which task
     * @param taskCount - specify count of tasks
     */
    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints message when task is deleted.
     * @param task - specify which task
     * @param taskCount - specify count of tasks
     */
    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints all tasks in the list.
     * @param tasks - the tasks in the list
     */
    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Prints tasks on a specified date.
     * @param tasks
     * @param date - specified date
     */
    public void showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        System.out.println("Here are the tasks on " + date + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getBy().toLocalDate().isEqual(date)) {
                    System.out.println((i + 1) + ". " + task);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks found on this date.");
        }
    }

    /**
     * Prints message when task is marked or unmarked.
     * @param task
     * @param isMarked
     */
    public void showMarkUnmarkTask(Task task, boolean isMarked) {
        if (isMarked) {
            System.out.println("Nice! I have marked this task as done:");
        } else {
            System.out.println("OK! I have marked this task as not done yet:");
        }
        System.out.println("  " + "[" + task.getStatusIcon() + "] " + task.getDescription());
    }

    /**
     * Prints error message.
     * @param message
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
