import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class encapsulates the chatbot.
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
     * Parses user raw input into a map and returns it.
     *
     * @param rawInput The raw command line input from the user.
     * @return A hashmap containing parameters and their arguments
     */
    private HashMap<String, String> parse(String rawInput) {
        HashMap<String, String> map = new HashMap<>();
        Scanner scanner = new Scanner(rawInput).useDelimiter(" /");

        // default empty in case of empty input
        map.put("command", "");
        map.put("command_args", "");

        // extract main command and its argument, if any
        if (scanner.hasNext()) {
            String[] mainCommand = scanner.next().split(" ", 2);
            map.put("command", mainCommand[0]);
            if (mainCommand.length == 1) {
                map.put("command_args", "");
            } else {
                map.put("command_args", mainCommand[1]);
            }
        }

        // extract additional parameters and their arguments
        while (scanner.hasNext()) {
            String[] params = scanner.next().split(" ", 2);
            if (params.length == 1) {
                map.put(params[0], "");
            } else {
                map.put(params[0], params[1]);
            }
        }

        return map;
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
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void add_task(Task task) {
        this.tasks.add(task);
        System.out.println("\nGot it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints the list of tasks.
     */
    public void list_tasks() {
        System.out.println("\nHere are the tasks in your list:");
        int taskNumber = 1;
        for (Task task: tasks) {
            System.out.println(taskNumber + "." + task);
            taskNumber++;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsDone();
        System.out.println("\nNice! I've marked this task as done:\n" + task);
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsNotDone(int index) {
        Task task = tasks.get(index - 1);
        task.markAsNotDone();
        System.out.println("\nOK, I've marked this task as not done yet:\n" + task);
    }

    /**
     * Starts the chatbot and run the main chat loop.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        greet();

        while (isChatting) {
            System.out.println(); // blank line before user input
            String rawInput = scanner.nextLine();
            HashMap<String, String> map = parse(rawInput);

            switch (map.get("command")) {
                case "bye":
                    isChatting = false;
                    say_goodbye();
                    break;
                case "list":
                    list_tasks();
                    break;
                case "mark":
                    markTaskAsDone(
                            Integer.parseInt(map.get("command_args"))
                    );
                    break;
                case "unmark":
                    markTaskAsNotDone(
                            Integer.parseInt(map.get("command_args"))
                    );
                    break;
                case "todo":
                    Task todoTask = new Todo(
                            map.get("command_args")
                    );
                    add_task(todoTask);
                    break;
                case "deadline":
                    Task deadlineTask = new Deadline(
                            map.get("command_args"),
                            map.get("by")
                    );
                    add_task(deadlineTask);
                    break;
                case "event":
                    Task eventTask = new Event(
                            map.get("command_args"),
                            map.get("from"),
                            map.get("to")
                    );
                    add_task(eventTask);
                    break;
                default:
                    System.out.println("\nCommand unknown.");
            }
        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
