import java.util.ArrayList;
import java.util.Scanner;

/**
 * The chatbot class.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    /** List of tasks entered by the user */
    private ArrayList<String> tasks;

    public Qwerty() {
        this.isChatting = true;
        this.tasks = new ArrayList<String>(100);
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println("""

                Hello! I'm Qwerty.
                What can I do for you?""");
    }

    /**
     * Prints a goodbye message.
     */
    public void say_goodbye() {
        System.out.println("""
                
                Bye. Hope to see you again soon!""");
    }

    /**
     * Adds a task to the tasklist.
     *
     * @param task String describing the task.
     */
    public void add_task(String task) {
        this.tasks.add(task);
        System.out.println("\nadded: " + task);
    }

    /**
     * Prints the list of tasks.
     */
    public void list_tasks() {
        System.out.println();
        int taskNumber = 1;
        for (String task: tasks) {
            System.out.println(taskNumber + ". " + task);
            taskNumber++;
        }
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        greet();

        while (isChatting) {
            System.out.println(); // blank line before user input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isChatting = false;
                say_goodbye();
            } else if (input.equals("list")) {
                list_tasks();
            } else {
                add_task(input);
            }

        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
