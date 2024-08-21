import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Sentinel chatbot system.
 *
 * @author isaactodo (Isaac Lim Tzee Zac)
 */
public class Sentinel {
    private final TaskList taskList;
    private final Map<String, Commands> commands;

    /**
     * Constructs a new Sentinel and initializes it with commands and
     * a task list.
     * The task list is initially empty.
     */
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

        commands.put("delete", new deleteTask());
    }

    ////////////// COMMANDS FOR SENTINEL START //////////////
    /**
     * Makes Sentinel output the list of tasks.
     */
    public void outputTaskList() {
        say("Here are the list of your tasks \n" + this.taskList.toString());
    }

    /**
     * Makes Sentinel mark the given task number as done.
     *
     * @param taskNumber Task number to be marked.
     */
    public void markDone(int taskNumber) throws SentinelException {
        say("Marked the following task as done: \n " + taskList.markAsDone(taskNumber));
    }

    /**
     * Makes Sentinel mark the given task number as undone.
     *
     * @param taskNumber Task number to be unmarked.
     */
    public void markUndone(int taskNumber) throws SentinelException {
        say("Unmarked the following task: \n " + taskList.markAsUndone(taskNumber));
    }

    /**
     * Makes Sentinel add the given todo to the task list.
     *
     * @param description Description of the todo to be added.
     */
    public void addTodo(String description) {
        say("Added the following todo: \n" + taskList.addTodo(description));
    }

    /**
     * Makes Sentinel add the given event to the task list.
     *
     * @param description Description of the event to be added.
     */
    public void addEvent(String description, String startTime, String endTime) {
        say("Added the following event: \n" + taskList.addEvent(description, startTime, endTime));
    }

    /**
     * Makes Sentinel add the given deadline to the task list.
     *
     * @param description Description of the deadline to be added.
     */
    public void addDeadline(String description, String endTime) {
        say("Added the following deadline: \n" + taskList.addDeadline(description, endTime));
    }

    /**
     * Makes Sentinel delete the given task.
     *
     * @param taskNumber Task to be deleted.
     * @throws SentinelException if task does not exist.
     */
    public void deleteTask(int taskNumber) throws SentinelException {
        say("Deleted the following task: \n" + taskList.deleteTask(taskNumber));
    }
    ////////////// COMMANDS FOR SENTINEL END //////////////

    ////////////// SENTINEL METHODS START //////////////
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

    /**
     * Makes Sentinel listen for tasks.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            String[] parsedCommands = userInput.split("\\s+");

            // Check for empty inputs
            if (parsedCommands.length == 0) {
                userInput = scanner.nextLine();
                continue;
            }

            // Check if command exists, if so, run the command
            if (this.commands.get(parsedCommands[0]) == null) {
                say("Invalid command broski");
            } else {
                try {
                    this.commands.get(parsedCommands[0]).run(this, userInput);
                } catch (SentinelException exception) {
                    say(exception.getMessage());
                }
            }

            // Check if there is a next line (for file inputs)
            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            } else {
                break;
            }
        }
    }
    ////////////// SENTINEL METHODS END //////////////

    public static void main(String[] args) {
        Sentinel mySentinel = new Sentinel();
        mySentinel.greet();
        mySentinel.listen();
        mySentinel.goodbye();
    }
}
