package velma;
import java.time.LocalDate;
import java.util.ArrayList;

import velma.task.Deadline;
import velma.task.Task;

/**
 * Represents the user interface of Velma.
 */
public class Ui {

    /**
     * Returns welcome message when Velma is started.
     */
    public String showWelcome() {
        return "Hello! I'm Velma\nWhat can I do for you?";
    }

    /**
     * Returns goodbye message when Velma is exited.
     */
    public String showGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns error message when loading file fails.
     */
    public String showLoadingError() {
        return "Error loading file";
    }

    /**
     * Returns message when task is added.
     * @param task - specify which task
     * @param taskCount - specify count of tasks
     */
    public String showTaskAdded(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns message when task is deleted.
     * @param task - specify which task
     * @param taskCount - specify count of tasks
     */
    public String showTaskDeleted(Task task, int taskCount) {
        assert task != null : "Task cannot be null";
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns all tasks in the list.
     * @param tasks - the tasks in the list
     */
    public String showAllTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Returns tasks on a specified date.
     * @param tasks - the tasks in the list
     * @param date - specified date
     */
    public String showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        assert tasks != null : "Tasks cannot be null";
        assert date != null : "Date cannot be null";
        StringBuilder sb = new StringBuilder("Here are the tasks on " + date + ":\n");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.getBy().toLocalDate().isEqual(date)) {
                    sb.append((i + 1)).append(". ").append(task).append("\n");
                    found = true;
                }
            }
        }
        if (!found) {
            sb.append("No tasks found on this date.");
        }
        return sb.toString();
    }

    /**
     * Returns message when task is marked or unmarked.
     * @param task - the task that was marked/unmarked
     * @param isMarked - whether the task is marked
     */
    public String showMarkUnmarkTask(Task task, boolean isMarked) {
        assert task != null : "Task cannot be null";
        StringBuilder sb = new StringBuilder();
        if (isMarked) {
            sb.append("Nice! I have marked this task as done:\n");
        } else {
            sb.append("OK! I have marked this task as not done yet:\n");
        }
        sb.append("  ").append("[").append(task.getStatusIcon()).append("] ").append(task.getDescription());
        return sb.toString();
    }

    /**
     * Returns error message.
     * @param message - the error message to show
     */
    public String showError(String message) {
        assert message != null : "Error message cannot be null";
        return message;
    }

    /**
     * Returns message when task is found.
     * @param tasks - the tasks found
     */
    public String showFoundTasks(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks cannot be null";
        if (tasks.isEmpty()) {
            return "No tasks found.";
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
