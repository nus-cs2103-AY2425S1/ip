package sentinel;

import sentinel.command.Commands;
import sentinel.storage.Storage;
import sentinel.task.TaskList;
import sentinel.ui.Ui;

import java.io.IOException;

import java.util.Scanner;

import java.time.LocalDate;

/**
 * Sentinel chatbot system.
 *
 * @author isaactodo (Isaac Lim Tzee Zac)
 */
public class Sentinel {
    private TaskList taskList;
    private final Ui ui;

    /**
     * Constructs a new Sentinel and initializes it with commands and
     * a task list.
     * The task list is initially empty.
     */
    public Sentinel() {
        this.ui = new Ui();

        // Load save file
        try {
            this.taskList = Storage.load();
        } catch(IOException e) {
            ui.showError("OOPS NO FILE???");
            this.taskList = new TaskList();
        }

        // Initialize sentinel.commands.Commands

    }

    ////////////// COMMANDS FOR SENTINEL START //////////////
    /**
     * Makes Sentinel output the list of tasks.
     */
    public void outputTaskList() {
        say("Here are the list of your tasks \n" + this.taskList.toString() +
            String.format("\n You have %d tasks in total.", this.taskList.getTotal()));
    }

    /**
     * Makes Sentinel mark the given task number as done.
     *
     * @param taskNumber sentinel.task.Task number to be marked.
     */
    public void markDone(int taskNumber) throws SentinelException {
        say("Marked the following task as done: \n " + taskList.markAsDone(taskNumber));
    }

    /**
     * Makes Sentinel mark the given task number as undone.
     *
     * @param taskNumber sentinel.task.Task number to be unmarked.
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
    public void addEvent(String description, LocalDate startTime, LocalDate endTime) {
        say("Added the following event: \n" + taskList.addEvent(description, startTime, endTime));
    }

    /**
     * Makes Sentinel add the given deadline to the task list.
     *
     * @param description Description of the deadline to be added.
     */
    public void addDeadline(String description, LocalDate endTime) {
        say("Added the following deadline: \n" + taskList.addDeadline(description, endTime));
    }

    /**
     * Makes Sentinel delete the given task.
     *
     * @param taskNumber sentinel.task.Task to be deleted.
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
        try {
            Storage.save(this.taskList.toString());
        } catch (IOException e) {
            say("Oops I had trouble saving your tasks...");
        }
    }

    /**
     * Makes Sentinel say a message.
     *
     * @param message Message for sentinel.Sentinel to say.
     */
    public void say(String message) {
        ui.output(message);
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
            if (Commands.getCommand(parsedCommands[0]) == null) {
                ui.showError("Invalid command broski");
            } else {
                try {
                    Commands.getCommand(parsedCommands[0]).run(this, userInput);
                } catch (SentinelException exception) {
                    ui.showError(exception.getMessage());
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
