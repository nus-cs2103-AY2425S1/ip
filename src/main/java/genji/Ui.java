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
     * @return Formatted string
     */
    public static String welcome() {
        return "Hello! I'm Genji\nWhat can I do for you?  O.o?";
    }

    /**
     * Prints goodbye message
     * @return Formatted string
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints help page
     * @return Formatted string
     */
    public String help() {
        return "bye : end the program\n" +
                "list : list out tasks\n" +
                "todo XXX : add todo task to task list\n" +
                "deadline XXX /by yyyy-mm-ddThh:mm : add deadline task to task list\n" +
                "event XXX /from yyyy-mm-ddThh:mm /to yyyy-mm-ddThh:mm : " +
                "add event task to task list\n" +
                "mark i : mark the i-th task as done\n" +
                "unmark i : mark the i-th task as undone\n" +
                "delete i : delete the i-th task\n" +
                "date yyyy-mm-dd : check tasks on a specific day\n" +
                "find XXX : search a task that contains the input\n";
    }

    /**
     * Prints marking the task
     * @param t The task to be printed
     * @return Formatted string
     */
    public String mark(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    /**
     * Prints unmarking the task
     * @param t The task to be printed
     * @return Formatted string
     */
    public String unmark(Task t) {
        return "OK! I've marked this task as not done yet:\n" + t.toString();
    }

    /**
     * Prints adding the task into the task list
     * @param t Task to be printed
     * @param list Task list to be counted then printed out its size
     * @return Formatted string
     */
    public String add(Task t, TaskList list) {
        return "Got it. I've added this task:\n" + t.toString() +
                "\nNow you have " + list.size() + " tasks in the list";
    }

    /**
     * Prints deleting the specific task
     * @param t Task to be printed
     * @param list Task list to be counted then printed out its size
     * @return Formatted string
     * */
    public String delete(Task t, TaskList list) {
        return "Noted. I've removed this task:\n" + t.toString() +
                "\nNow you have " + list.size() + " tasks in the list";
    }

    /**
     * Prints tasks that match the description
     * @param list Task list to be printed
     * @return Formatted string
     */
    public String find(TaskList list) {
        if (list.size() == 0){
            return "No matching tasks in your list";
        } else {
            String result = "";
            for (Task t : list.getList()) {
                result = result + t.toString() +"\n";
            }
            return "Here are the matching tasks in your list:\n" + result;
        }
    }

    /**
     * Prints error message received
     * @param s The error message
     * @return Formatted string
     */
    public String showError(String s) {
        return s;
    }

    /**
     * Prints a line to separate sentences
     * @return Formatted string
     */
    public String showLine() {
        return LINE;
    }

    /**
     * Reads user's command and passes it to the parser
     * @return The user's command
     */
    public String readCommand() {
        return scanner.nextLine();
    }

}
