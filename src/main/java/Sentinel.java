import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Sentinel chatbot system.
 */
public class Sentinel {
    private final TaskList taskList;
    private final Map<String, Commands> commands;

    public Sentinel() {
        this.taskList = new TaskList();
        this.commands = new HashMap<>();

        // Initialize Commands
        commands.put("list", new ListTasks());

        commands.put("mark", new markDone());
        commands.put("unmark", new markUndone());

        commands.put("todo", new addTodo());
        commands.put("deadline", new addDeadline());
        commands.put("event", new addEvent());
    }

    // Commands
    /**
     * Makes Sentinel say the list of tasks.
     */
    public void outputTaskList() {
        say("Here are the list of your tasks \n" + this.taskList.toString());
    }

    /**
     * Makes Sentinel mark the given task number as done.
     *
     * @param taskNumber Task number to be marked.
     */
    public void markDone(int taskNumber) {
        say("Marked the following task as done: \n " + taskList.markAsDone(taskNumber));
    }

    /**
     * Makes Sentinel mark the given task number as undone.
     *
     * @param taskNumber Task number to be unmarked.
     */
    public void markUndone(int taskNumber) {
        say("Unmarked the following task: \n " + taskList.markAsUndone(taskNumber));
    }

    public void addTodo(String description) {
        say("Added the following todo: \n" + taskList.addTodo(description));
    }

    public void addEvent(String description, String startTime, String endTime) {
        say("Added the following event: \n" + taskList.addEvent(description, startTime, endTime));
    }

    public void addDeadline(String description, String endTime) {
        say("Added the following deadline: \n" + taskList.addDeadline(description, endTime));
    }

    // Sentinel methods
    /**
     * Makes Sentinel say a greeting message.
     */
    public void greet() {
        String greetMessage = "Greetings! I'm Sentinel. \n" + "What can I do for you?";
        say(greetMessage);
    }

    /**
     * Makes Sentinel say a goodbye message.
     */
    public void goodbye() {
        String goodbyeMessage = "It was a pleasure conversing with you. Goodbye!";
        say(goodbyeMessage);
    }

    /**
     * Makes Sentinel say a message.
     *
     * @param message Message for Sentinel to say.
     */
    public void say(String message) {
        System.out.println(message);
    }

    /**
     * Makes Sentinel echo a message until a bye command is received.
     */
    public void echo() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            say(userInput);
            userInput = scanner.nextLine();
        }
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    /**
     * Makes Sentinel listen for tasks.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] parsedCommands = userInput.split("\\s+");

            if (parsedCommands.length == 0) {
                userInput = scanner.nextLine();
                continue;
            }

            if (this.commands.get(parsedCommands[0]) == null) {
                say("Invalid command broski");
            } else {
                this.commands.get(parsedCommands[0]).run(this, userInput);
            }

            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Sentinel mySentinel = new Sentinel();
        mySentinel.greet();
        mySentinel.listen();
        mySentinel.goodbye();
    }
}
