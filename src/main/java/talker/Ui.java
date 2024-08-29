package talker;

import talker.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Prints the welcome message
     */
    public void printWelcome() {
        System.out.println(LINE);
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    /**
     * Prints the goodbye message
     */
    public void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a divider line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints error message of exception
     *
     * @param e exception to be handled
     */
    public void printError(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    /**
     * Prints list of tasks
     *
     * @param list list of tasks to be printed
     */
    public void printTaskList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i));
        }
    }

    /**
     * Prints String representation of task
     *
     * @param task task object to be printed
     */
    public void printTask(Task task) {
        System.out.println(task);
    }

    /**
     * Prints String representation of deletion of task
     *
     * @param task task to be deleted
     * @param size new size of task list
     */
    public void printTaskDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Prints String representation of adding of task
     *
     * @param task task to be added
     * @param size new size of task list
     */
    public void printTaskAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    /**
     * Prints a String representation of searching of tasks on target date
     *
     * @param date target date
     */
    public void printTasksOn(String date) {
        System.out.println("These are your tasks on " + date + ":");
    }

    /**
     * Prints String representation of marking of task as complete
     *
     * @param mark String representation of marking event
     */
    public void printTaskMarked(String mark) {
        System.out.println(mark);
    }

    /**
     * Prints String representation of marking of task as incomplete
     *
     * @param unmark String representation of unmarking event
     */
    public void printTaskUnmarked(String unmark) {
        System.out.println(unmark);
    }

    /**
     * Prints list of tasks that have matching keyword
     *
     * @param outputList list of tasks to be printed
     */
    public void printMatchingTasks(ArrayList<Task> outputList) throws TalkerException {
        if (outputList.size() == 0) {
            throw new TalkerException("No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < outputList.size(); i++) {
                System.out.printf("%d.%s\n", i + 1, outputList.get(i));
            }
        }
    }
}
