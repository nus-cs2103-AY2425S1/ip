package sentinel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import sentinel.command.Commands;
import sentinel.customer.CustomerList;
import sentinel.storage.Storage;
import sentinel.task.TaskList;
import sentinel.ui.Ui;

/**
 * Sentinel chatbot system.
 *
 * @author isaactodo (Isaac Lim Tzee Zac)
 */
public class Sentinel {
    private TaskList taskList;
    private TaskList searchTaskList;
    private CustomerList customerList;
    private CustomerList customerSearchList;
    private final Ui ui;

    private boolean isSearch;

    /**
     * Constructs a new Sentinel and initializes it with commands and
     * a task list.
     * The task list is initially empty.
     */
    public Sentinel() {
        this.ui = new Ui();
        this.isSearch = false;

        loadTaskList();
        loadCustomerList();
    }

    /**
     * Loads task list from Storage into Sentinel's memory.
     */
    private void loadTaskList() {
        // Load save file
        try {
            this.taskList = Storage.loadTaskList();
        } catch (IOException exception) {
            ui.showError("OOPS NO FILE???");
            this.taskList = new TaskList();
        }
    }

    /**
     * Saves task list into Storage.
     */
    private void saveTaskList() {
        try {
            Storage.saveTaskList(this.taskList.toString());
        } catch (IOException exception) {
            say("Oops I had trouble saving your tasks...");
        }
    }

    /**
     * Loads task list from Storage into Sentinel's memory.
     */
    private void loadCustomerList() {
        // Load customer file
        try {
            this.customerList = Storage.loadCustomerList();
        } catch (IOException exception) {
            ui.showError("OOPS NO FILE???");
            this.customerList = new CustomerList();
        }
    }

    /**
     * Saves task list into Storage.
     */
    private void saveCustomerList() {
        try {
            Storage.saveCustomerList(this.customerList.toString());
        } catch (IOException exception) {
            say("Oops I had trouble saving your customers...");
        }
    }

    ////////////// COMMANDS FOR SENTINEL START //////////////
    /**
     * Makes Sentinel output the list of tasks.
     */
    public void outputTaskList() {
        isSearch = false;

        assert(this.taskList != null) : "Task list does not exist";
        say("Here are the list of your tasks \n" + this.taskList.toString()
                + String.format("\n You have %d tasks in total.", this.taskList.getTotal()));
    }

    /**
     * Makes Sentinel output the list of tasks that match the search string.
     */
    public void outputMatchingTaskList(String searchTerm) {
        searchTaskList = this.taskList.getMatching(searchTerm);
        isSearch = true;

        assert(searchTaskList != null) : "Search task list does not exist";
        say("Here are the list of your matching tasks \n" + this.searchTaskList.toString()
                + String.format("\n You have %d tasks matching that name.", this.searchTaskList.getTotal())
                + "\n\n You are now indexing the search list.\n Type list to back to indexing the original list.");
    }

    /**
     * Makes Sentinel output the list of customers.
     */
    public void outputCustomerList() {
        isSearch = false;

        assert(this.customerList != null) : "Customer list does not exist";
        say("Here are the list of your customers \n" + this.customerList.toString()
                + String.format("\n You have %d customers in total.", this.customerList.getTotal()));
    }

    /**
     * Makes Sentinel output the list of customers that match the search string.
     */
    public void outputMatchingCustomerList(String searchTerm) {
        customerSearchList = this.customerList.getMatching(searchTerm);
        isSearch = true;

        assert(customerSearchList != null) : "Search customer list does not exist";
        say("Here are the list of your matching customers \n" + this.customerSearchList.toString()
                + String.format("\n You have %d customers matching that name.", this.customerSearchList.getTotal())
                + "\n\n You are now indexing the search list.\n Type list to back to indexing the original list.");
    }

    /**
     * Makes Sentinel mark the given task number as done.
     *
     * @param taskNumber Task number to be marked.
     * @throws SentinelException if an error occurs.
     */
    public void markDone(int taskNumber) throws SentinelException {
        TaskList selectedTaskList = this.taskList;

        if (this.isSearch) {
            selectedTaskList = this.searchTaskList;
        }

        assert(selectedTaskList != null) : "Selected task list does not exist";
        say("Marked the following task as done: \n " + selectedTaskList.markAsDone(taskNumber));
    }

    /**
     * Makes Sentinel mark the given task number as undone.
     *
     * @param taskNumber Task number to be unmarked.
     * @throws SentinelException if an error occurs.
     */
    public void markUndone(int taskNumber) throws SentinelException {
        TaskList selectedTaskList = this.taskList;

        if (this.isSearch) {
            selectedTaskList = this.searchTaskList;
        }

        assert(selectedTaskList != null) : "Selected task list does not exist";
        say("Unmarked the following task: \n " + selectedTaskList.markAsUndone(taskNumber));
    }

    /**
     * Makes Sentinel add the given customer to the task list.
     *
     * @param name Name of the customer to be added.
     * @param phoneNumber Phone number of the customer to be added.
     */
    public void addCustomer(String name, String phoneNumber) {
        say("Added the following customer: \n" + customerList.addCustomer(name, phoneNumber));
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
     * @param taskNumber Task to be deleted.
     * @throws SentinelException if task does not exist.
     */
    public void deleteTask(int taskNumber) throws SentinelException {
        say("Deleted the following task: \n" + taskList.deleteTask(taskNumber));
    }

    /**
     * Makes Sentinel delete the given customer.
     *
     * @param customerNumber Customer to be deleted.
     * @throws SentinelException if task does not exist.
     */
    public void deleteCustomer(int customerNumber) throws SentinelException {
        say("Deleted the following customer: \n" + customerList.deleteCustomer(customerNumber));
    }
    ////////////// COMMANDS FOR SENTINEL END //////////////

    ////////////// SENTINEL METHODS START //////////////
    /**
     * Makes Sentinel say a greeting message.
     *
     * @return Greeting message.
     */
    public String greet() {
        String greetMessage = "Greetings! I'm Sentinel. \n" + "What can I do for you?";
        say(greetMessage);
        return greetMessage;
    }

    /**
     * Makes Sentinel say a goodbye message.
     */
    public void goodbye() {
        String goodbyeMessage = "It was a pleasure conversing with you. Goodbye!";
        say(goodbyeMessage);
        saveTaskList();
        saveCustomerList();
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
     * Makes Sentinel run a command.
     *
     * @param parsedCommands Array of parsed command strings.
     * @param userInput Original input given by the user.
     */
    private void runCommand(String[] parsedCommands, String userInput) {
        // Check if command exists
        if (Commands.getCommand(parsedCommands[0]) == null) {
            ui.showError("Invalid command broski");
            return;
        }
        try {
            Commands.getCommand(parsedCommands[0]).run(this, userInput);
        } catch (SentinelException exception) {
            ui.showError(exception.getMessage());
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
            runCommand(parsedCommands, userInput);

            // Check if there is a next line (for file inputs to work correctly)
            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
            } else {
                break;
            }
        }
    }

    /**
     * Makes Sentinel respond to an input.
     */
    public String getResponse(String input) {
        if (input.equals("bye")) {
            goodbye();
            return ui.getCurrentMessage();
        }

        String[] parsedCommands = input.split("\\s+");
        runCommand(parsedCommands, input);

        return ui.getCurrentMessage();
    }
    ////////////// SENTINEL METHODS END //////////////

    public static void main(String[] args) {
        Sentinel mySentinel = new Sentinel();
        mySentinel.greet();
        mySentinel.listen();
        mySentinel.goodbye();
    }
}
