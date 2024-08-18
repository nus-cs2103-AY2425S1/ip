import java.util.ArrayList;
import java.util.Scanner;

/**
 * The chatbot class.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;
    /** List of tasks entered by the user */
    private ArrayList<Task> tasks;

    public Qwerty() {
        this.isChatting = true;
        this.tasks = new ArrayList<Task>();
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
     * @param description String describing the task.
     */
    public void add_task(String description) {
        Task task = new Task(description);
        this.tasks.add(task);
        System.out.println("\nadded: " + description);
    }

    /**
     * Prints the list of tasks.
     */
    public void list_tasks() {
        System.out.println();
        int taskNumber = 1;
        for (Task task: tasks) {
            System.out.println(taskNumber + "." + task);
            taskNumber++;
        }
    }

    public void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("\nNice! I've marked this task as done:\n" + task);
    }

    public void markTaskAsNotDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        System.out.println("\nOK, I've marked this task as not done yet:\n" + task);
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
            String rawInput = scanner.nextLine();

            // Parse user input into command and arguments
            String[] inputs = rawInput.split(" ", 2);
            String command = inputs[0];
            String args;

            switch (command) {
                case "bye":
                    isChatting = false;
                    say_goodbye();
                    break;
                case "list":
                    list_tasks();
                    break;
                case "mark":
                    args = inputs[1];
                    int indexToMark = Integer.parseInt(args);
                    markTaskAsDone(indexToMark);
                    break;
                case "unmark":
                    args = inputs[1];
                    int indexToUnmark = Integer.parseInt(args);
                    markTaskAsNotDone(indexToUnmark);
                    break;
                case "":
                    break;
                default:
                    add_task(rawInput);
            }
        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
