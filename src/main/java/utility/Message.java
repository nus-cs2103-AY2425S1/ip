package utility;

import task.Task;
import task.TaskType;

/**
 * The Message class is responsible for generating response messages
 * for various user interactions within the task management application.
 */
public class Message {

    /**
     * Returns a message indicating that no tasks were found.
     *
     * @return A string message stating that no task was found.
     */
    public String printNoTask() {
        return "No Task Found.";
    }

    /**
     * Returns a message indicating that a task already exists.
     *
     * @return A string message stating that the task exists.
     */
    public String printExists() {
        return "This Task Exists.";
    }

    /**
     * Returns a message confirming that a new task has been added,
     * along with the task details and the current task count.
     *
     * @param newTask The new task that was added.
     * @param count   The current count of tasks.
     * @return A string message confirming the addition of the task.
     */
    public String printAddTask(Task newTask, int count) {
        return printHeader("add") + newTask + printTaskCount(count);
    }

    /**
     * Returns a default message for unrecognized commands.
     *
     * @return A string message indicating the command is not recognized.
     */
    public String printDefault() {
        return "I'm sorry, but I don't know what that means.";
    }

    /**
     * Returns an error message for empty task descriptions
     * based on the task type.
     *
     * @param type The type of task (ToDo, Deadline, or Event).
     * @return A string error message related to empty task descriptions.
     */
    public String printEmptyError(TaskType type) {
        switch (type) {
        case TODO -> {
            return "The description of a todo cannot be empty.";
        }
        case DEADLINE -> {
            return "The description and date of a deadline cannot be empty.";
        }
        case EVENT -> {
            return "The description and date of an event cannot be empty.";
        }
        default -> {
            return "";
        }
        }
    }

    /**
     * Returns a message indicating that the task number cannot be empty.
     *
     * @return A string message regarding empty task number input.
     */
    public String printEmptyID() {
        return "The task number cannot be empty.";
    }

    /**
     * Returns a message indicating that the keyword for searching cannot be empty.
     *
     * @return A string message regarding empty keyword input.
     */
    public String printEmptyKey() {
        return "The keyword to look for cannot be empty.";
    }

    /**
     * Returns a message indicating that the provided date is invalid.
     *
     * @return A string message regarding invalid date format.
     */
    public String printInvalidDate() {
        return "Invalid Datetime format.";
    }

    /**
     * Returns a header message based on the action type.
     *
     * @param type The type of action (e.g., add, list, mark, unmark, delete, find).
     * @return A string header message related to the action type.
     */
    public String printHeader(String type) {
        switch (type) {
        case "add" -> {
            return "Got it. I've added this task:\n  ";
        }
        case "list" -> {
            return "Here are the tasks in your list:\n";
        }
        case "mark" -> {
            return "Nice! I've marked this task as done:\n  ";
        }
        case "unmark" -> {
            return "OK, I've marked this task as not done:\n  ";
        }
        case "delete" -> {
            return "Noted. I've removed this task:\n  ";
        }
        case "find" -> {
            return "Here are the matching tasks in your list:\n";
        }
        default -> {
            return "";
        }
        }
    }

    /**
     * Returns a message indicating the current task count.
     *
     * @param count The current count of tasks.
     * @return A string message stating the current number of tasks.
     */
    public String printTaskCount(int count) {
        return "\nNow you have " + count + " tasks in the list.";
    }
}
