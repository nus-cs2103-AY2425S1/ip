package genji;

import genji.task.Task;
import genji.task.TaskList;
import java.util.Scanner;

/**
 * A class to print messages
 */
public class Ui {
    private final String LINE = "________________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructor of Ui
     * Initializes the scanner
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message
     */
    public void welcome() {
        showLine();
        System.out.println("Hello! I'm Genji\nWhat can I do for you?  O.o?");
        showLine();
    }

    /**
     * Prints goodbye message
     */
    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints marking the task
     * @param t The task to be printed
     */
    public void mark(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Prints unmarking the task
     * @param t The task to be printed
     */
    public void unmark(Task t) {
        System.out.println("OK! I've marked this task as not done yet:");
        System.out.println(t);
    }

    /**
     * Prints adding the task into the task list
     * @param t Task to be printed
     * @param list Task list to be counted then printed out its size
     */
    public void add(Task t, TaskList list) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

    /**
     * Prints deleting the specific task
     * @param t Task to be printed
     * @param list Task list to be counted then printed out its size
     */
    public void delete(Task t, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.println("Now you have " + list.size() + " tasks in the list");
    }

<<<<<<< HEAD
    /**
     * Prints error message received
     * @param s The error message
     */
=======
    public void find(TaskList list) {
        if (list.size() == 0){
            System.out.println("No matching tasks in your list");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (Task t : list.getList()) {
                System.out.println(t);
            }
        }
    }

>>>>>>> branch-Level-9
    public void showError(String s) {
        System.out.println(s);
    }

    /**
     * Prints a line to separate sentences
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Reads user's command and passes it to the parser
     * @return The user's command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

}
