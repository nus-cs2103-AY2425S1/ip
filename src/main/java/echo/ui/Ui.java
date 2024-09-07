package echo.ui;

import java.util.ArrayList;

import echo.task.Task;
import echo.tasklist.TaskList;

/**
 * Represents user's user interface when using the Chat Bot Echo
 *
 * @author Ernest Lim
 */
public class Ui {
    /**
     * Returns a string of greeting message
     *
     * @return a string of greeting message
     */
    public String greet() {
        String output = "Meow, I'm Echo\n";
        output += "What can I do for you?\n";
        return output;
    }

    /**
     * Returns a goodbye message
     *
     * @return a string of message saying goodbye
     */
    public String bye() {
        String output = "The tasks in the list have been saved successfully!\n";
        output += "Goodnight! Hope to see you again soon!\n";
        return output;
    }

    /**
     * Returns the list of all tasks stored in taskList as a string
     *
     * @param taskList taskList containing all the tasks stored
     * @return string od list of all tasks in taskList
     */
    public String listAllTask(TaskList taskList) {
        String output = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.sizeOfTaskList(); i++) {
            String task = (i + 1) + ". " + taskList.getTask(i).toString() + "\n";
            output += task;
        }
        return output;
    }

    /**
     * Returns a string of message about successful marking of task
     *
     * @param task task to be displayed in the message
     * @return string of message about successful marking of task
     */
    public String printMarkMessage(Task task) {
        String output = "Meow-velous! I've marked this task as done:\n";
        output += task.toString();
        return output;
    }

    /**
     * Returns a string of message about successful unmarking of task
     *
     * @param task task to be displayed in the message
     * @return string of message about successful unmarking of task
     */
    public String printUnmarkMessage(Task task) {
        String output = "Meow-nificent! I've marked this task as not done yet:\n";
        output += task.toString();
        return output;
    }

    /**
     * Returns a string of message of the task being added and the number of task in the list
     *
     * @param task task to be added
     * @param taskList taskList that the task is being added to
     * @return string of message after successful adding of ToDos task
     */
    public String printAddTaskMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        String output = "Got it! I've added this task:\n";
        output += (task.toString() + "\n");
        output += ("Now you have " + numOfTask + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns a string of message of the task being deleted and the number of task left in the list
     *
     * @param task task to be deleted
     * @param taskList taskList that the task is being deleted from
     * @return a string of message after successfully deleting task
     */
    public String printDeleteMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        String output = "Got it!. I've removed this task:";
        output += (task.toString() + "\n");
        output += ("Now you have " + numOfTask + " tasks in the list.\n");
        return output;
    }

    /**
     * Returns a string of list of task found in an arrayList of Task
     *
     * @param arrayList arrayList containing a list of task
     * @return string of list of task found in an arrayList of Task
     */
    public String printFoundTask(ArrayList<Task> arrayList) {
        if (arrayList.isEmpty()) {
            return "There are no task found with the keyword.\n";
        } else {
            String output = "Here are the matching tasks in your list:\n";
            for (int i = 0; i < arrayList.size(); i++) {
                String task = (i + 1) + "." + arrayList.get(i).toString() + "\n";
                output += task;
            }
            return output;
        }
    }

    /**
     * Return string of error message of the respective exceptions being caught
     *
     * @param exception exceptions that are caught while running the program
     * @return string of error message of the respective exceptions being caught
     */
    public String printErrorMessage(Exception exception) {
        switch (exception.getClass().getSimpleName()) {
        case "DateTimeParseException":
            String dateTimeParseOutput = "Sorry! You have provided an invalid date and time.\n";
            dateTimeParseOutput += "Try again in this format dd-MM-yyyy HHmm";
            return dateTimeParseOutput;
        case "FileNotFoundException":
            return "Sorry! File is not found, please check your file path again.";
        case "IOException":
            return "Sorry! There has been an invalid input or output found.";
        case "EchoException":
            return exception.getMessage();
        case "NumberFormatException":
            return "Sorry! You have provided an invalid number.";
        default:
            return exception.getMessage();
        }
    }

    /**
     * Returns a string of list of valid commands and how to use them
     *
     */
    public String showValidCommands() {
        return ("List of valid commands:\n"
                + "list --> list\n"
                + "mark --> mark [index]\n"
                + "unmark --> unmark [index]\n"
                + "todo --> todo [task description]\n"
                + "deadline --> deadline [task description] /by[dd-MM-yyyy HHmm]\n"
                + "event --> event [task description] /from [dd-MM-yyyy HHmm] /to [dd-MM-yyyy HHmm]\n"
                + "delete --> delete [index]\n"
                + "find --> find [keyword]\n"
                + "bye --> bye");
    }
}
