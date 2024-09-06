package talker;

import java.util.Scanner;

import talker.task.Task;

/**
 * Represents an object to deal with printing output and scanning user input
 */
public class Ui {

    private static final String LINE = "____________________________________________________________";
    private final String name;
    private Scanner scanner;

    /**
     * Constructor of a new Ui object with name of chatbot
     *
     * @param name name of chatbot
     */
    public Ui(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
    }

    /**
     * Scans next line of scanner object as a command
     *
     * @return a string representing the command to be executed
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Returns the welcome message
     * @return String representing welcome message
     */
    public String printWelcome() {
        return String.format("Hello! I'm %s\n", name);
    }

    /**
     * Returns the goodbye message
     * @return String representing goodbye message
     */
    public String printGoodBye() {
        return String.format("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a divider line
     * @return String representing divider line
     */
    public String printLine() {
        return LINE;
    }

    /**
     * Prints error message of exception
     *
     * @param e exception to be handled
     */
    public String printError(Exception e) {
        return String.format("Error: " + e.getMessage() + "\n");
    }

    /**
     * Returns list of tasks
     *
     * @param tasks taks to be printed
     * @return string representing tasks
     */
    public String printTaskList(Task... tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.length; i++) {
            output.append(String.format("%d.%s\n", i + 1, tasks[i]));
        }
        return output.toString();
    }

    /**
     * Returns String representation of task
     *
     * @param task task object to be printed
     * @return String representation of task
     */
    public String printTask(Task task) {
        return task.toString();
    }

    /**
     * Returns representation of deletion of task
     *
     * @param task task to be deleted
     * @param size new size of task list
     * @return response when task has been deleted
     */
    public String printTaskDelete(Task task, int size) {
        StringBuilder output = new StringBuilder();
        output.append("Noted. I've removed this task:\n");
        output.append(task.toString() + "\n");
        output.append(String.format("Now you have %d tasks in the list.\n", size));
        return output.toString();
    }

    /**
     * Returns representation of adding of task
     *
     * @param task task to be added
     * @param size new size of task list
     * @return String response when task has been added
     */
    public String printTaskAdd(Task task, int size) {
        StringBuilder output = new StringBuilder();
        output.append("Got it. I've added this task:\n");
        output.append(task.toString() + "\n");
        output.append(String.format("Now you have %d tasks in the list.\n", size));
        return output.toString();
    }

    /**
     * Returns representation of searching of tasks on target date
     *
     * @param date target date
     * @return String response before list of tasks shown
     */
    public String printTasksOn(String date) {
        return "These are your tasks on " + date + ":\n";
    }

    /**
     * Returns representation of marking of task as complete
     *
     * @param mark String representation of marking event
     * @return String response when marked as complete
     */
    public String printTaskMarked(String mark) {
        return mark.toString();
    }

    /**
     * Prints representation of marking of task as incomplete
     *
     * @param unmark String representation of unmarking event
     * @return String response when marked as incomplete
     */
    public String printTaskUnmarked(String unmark) {
        return unmark.toString();
    }

    /**
     * Returns list of tasks that have matching keyword
     *
     * @param tasks list of tasks to be printed
     * @return String list of matching tasks
     */
    public String printMatchingTasks(Task... tasks) throws TalkerException {
        if (tasks.length == 0) {
            throw new TalkerException("No matching tasks found!");
        } else {
            StringBuilder output = new StringBuilder();
            output.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.length; i++) {
                output.append(String.format("%d.%s\n", i + 1, tasks[i]));
            }
            return output.toString();
        }
    }
}
