package cheese;

import java.util.Scanner;

import cheese.exception.CheeseException;
import cheese.task.Task;

/**
 * Takes in user input and formats the response
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ENDING = "Schwooo Weeeeee!!! Shutting down..... Window closing in 3s :)";
    private static final String ERROR = "Command not gouda.... ";
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads and returns user commands from standard input
     * @return user input as String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Returns the output of the bot
     * @param s String to say
     */
    public String say(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
        return s;
    }

    /**
     * Say all items in the task list
     */
    public String say(TaskList tasks) {
        StringBuilder allItems = new StringBuilder("Got your cheese:");
        for (int i = 0; i < tasks.size(); i++) {
            allItems.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return say(String.valueOf(allItems));
    }

    /**
     * Say a newly added task
     * @param t Cheese.Task
     * @param tasks TaskList
     */
    public String say(Task t, TaskList tasks) {
        String s = "Added new cheese ;)\n" + t.toString() + "\n" + tasks.size() + " cheese in the shelf";
        return say(s);
    }

    /**
     * Say updated task
     * @param t updated task
     * @param tasks task list
     */
    public String say(Task t, TaskList tasks, boolean isDelete) {
        String del = isDelete ? "Removed" : "Updated";
        String s = del + " cheese :)\n" + t.toString() + "\n" + tasks.size() + " cheese in the shelf";
        return say(s);
    }

    /**
     * Say error
     * @param e custom exception
     */
    public String say(CheeseException e) {
        return say(ERROR + "\n" + e.getMessage());
    }

    /**
     * Say greeting when bot starts
     */
    public String greet() {
        return say(GREETING);
    }

    /**
     * Say ending when bot closes
     */
    public String bye() {
        return say(ENDING);
    }
}
