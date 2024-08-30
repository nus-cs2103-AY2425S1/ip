package cheese;

import java.util.Scanner;

import cheese.task.Task;

/**
 * Takes in user input and formats the response
 */
public class Ui {
    private static final String GREETING = "Hello! I'm Wheely Big Cheese\nWhat can I do for you?";
    private static final String ENDING = "Schwooo Weeeeee!!! Shutting down..... Window closing in 3s :)";
    private static final String ERROR = "Cheese.Command not gouda.... ";
    private final Scanner sc;

    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * read user commands from standard input
     * @return user input as String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Format the output of the bot
     * @param s String to say
     */
    public String say(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("____________________________________________________________");
        return s;
    }

    /**
     * Say the task list
     */
    public String say(TaskList tasks) {
        StringBuilder allItems = new StringBuilder("Got your cheese:");
        for (int i = 0; i < tasks.size(); i++) {
            allItems.append("\n").append(i + 1).append(". ").append(tasks.get(i).toString());
        }
        return say(String.valueOf(allItems));
    }

    /**
     * Say a newly added/deleted task
     * @param t Cheese.Task
     * @param tasks tasklist
     * @param delete note delete/add task
     */
    public String say(Task t, TaskList tasks, boolean delete) {
        String del;
        if (delete) {
            del = "Removed cheese :(\n";
        } else {
            del = "Added new cheese ;)\n";
        }
        String s = del + t.toString() + "\n" + tasks.size() + " cheese in the shelf";
        return say(s);
    }

    /**
     * Say updated task
     * @param t updated task
     * @param tasks task list
     */
    public String say(Task t, TaskList tasks) {
        String s = "Updated cheese :)\n" + t.toString() + "\n" + tasks.size() + " cheese in the shelf";
        return say(s);
    }

    /**
     * Say error
     * @param e custom exception
     */
    public String say(CheeseException e) {
        return say(ERROR + e.getMessage());
    }

    /**
     * Say greeting
     */
    public String greet() {
        return say(GREETING);
    }

    /**
     * Say ending
     */
    public String bye() {
        return say(ENDING);
    }
}
