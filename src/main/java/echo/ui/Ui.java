package echo.ui;

import echo.task.Task;
import echo.tasklist.TaskList;

public class Ui {
    private static final String LINE_BREAK = "-------------------------------------";

    /**
     * Prints a greeting
     */
    public void greet() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello: I'm Echo\nWhat can I do for you?");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a goodbye message
     */
    public void bye() {
        System.out.println(LINE_BREAK);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints out the list of all tasks stored in taskList
     *
     * @param taskList taskList containing all the tasks stored
     */
    public void listAllTask(TaskList taskList) {
        System.out.println(LINE_BREAK);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.sizeOfTaskList(); i++) {
            System.out.println((i + 1) + "." + taskList.getTask(i).toString());
        }
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a display message about successful marking of task
     *
     * @param task task to be displayed in the message
     */
    public void printMarkMessage(Task task) {
        System.out.println(LINE_BREAK);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a display message about successful unmarking of task
     *
     * @param task task to be displayed in the message
     */
    public void printUnmarkMessage(Task task) {
        System.out.println(LINE_BREAK);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a display message of the task being added and the number of task in the list
     *
     * @param task task to be added
     * @param taskList taskList that the task is being added to
     */
    public void printAddTaskMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        System.out.println(LINE_BREAK);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints a display message of the task being deleted and the number of task left in the list
     *
     * @param task task to be deleted
     * @param taskList taskList that the task is being deleted from
     *
     */
    public void printDeleteMessage(Task task, TaskList taskList) {
        int numOfTask = taskList.sizeOfTaskList();
        System.out.println(LINE_BREAK);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + numOfTask + " tasks in the list.");
        System.out.println(LINE_BREAK);
    }

    /**
     * Prints the error message of the respective exceptions being caught
     *
     * @param exception exceptions that are caught while running the program
     */
    public void printErrorMessage(Exception exception) {
        switch (exception.getClass().getSimpleName()) {
        case "DateTimeParseException":
            System.out.println(LINE_BREAK);
            System.out.println("Sorry! You have provided an invalid date and time.");
            System.out.println("Try again in this format dd-MM-yyyy hhmm");
            System.out.println(LINE_BREAK);
            break;
        case "FileNotFoundException":
            System.out.println(LINE_BREAK);
            System.out.println("Sorry! File is not found, please check your file path again.");
            System.out.println(LINE_BREAK);
            break;
        case "IOException":
            System.out.println(LINE_BREAK);
            System.out.println("Sorry! There has been and invalid input or output found.");
            System.out.println(LINE_BREAK);
            break;
        case "EchoException":
            System.out.println(LINE_BREAK);
            System.out.println(exception.getMessage());
            System.out.println(LINE_BREAK);
            break;
        case "NumberFormatException":
            System.out.println(LINE_BREAK);
            System.out.println("Sorry! You have provided an invalid number.");
            System.out.println(LINE_BREAK);
            break;
        default:
            System.out.println(LINE_BREAK);
            System.out.println(exception.getMessage());
            System.out.println(LINE_BREAK);
            break;
        }
    }
}
