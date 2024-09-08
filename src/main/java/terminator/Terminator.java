package terminator;

import java.util.Scanner;

import terminator.cli.TerminalUi;
import terminator.command.Command;
import terminator.command.CommandParser;
import terminator.command.DeadlineCommand;
import terminator.command.DeleteCommand;
import terminator.command.EventCommand;
import terminator.command.TerminatorException;
import terminator.command.TodoCommand;
import terminator.task.Storage;
import terminator.task.TaskList;

/**
 * Main class for the chatbot application.
 */
public class Terminator {

    private final TaskList taskList;

    private final CommandParser parser;

    private final TerminalUi terminalUi;

    private final Storage storage;

    /**
     * Creates a new instance of the Terminator object, which is the main entry point of the application.
     */
    public Terminator() {
        this.taskList = new TaskList();
        this.parser = new CommandParser();
        this.terminalUi = new TerminalUi();
        this.storage = new Storage();
    }

    /**
     * Runs the chatbot application, which first attempts to read data from the local disk to populate
     * the task list. <br/><br/>
     *
     * If the data was read successfully, initiate the main event loop, which can
     * handle commands from the user through the command line. <br/><br/>
     *
     * The application exits when the user enters {@code bye} into the command line.
     */
    private void runTerminalUi() {
        // Load data from storage
        try {
            this.storage.loadDataFromFile(this.taskList);
        } catch (TerminatorException e) {
            System.out.println("Invalid data format.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            this.terminalUi.showErrorMsg();
            return;
        }

        // Start main event loop
        this.terminalUi.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            this.terminalUi.printHorizontalLine();
            try {
                Command command = this.parser.parse(input);
                String response = command.execute(this.taskList.getTaskList());
                if (command instanceof TodoCommand
                    || command instanceof EventCommand
                    || command instanceof DeadlineCommand
                    || command instanceof DeleteCommand) {
                    response += taskList.getTasksRemaining();
                }
                System.out.println(response);
            } catch (TerminatorException de) {
                System.out.println("Error detected: " + de.getMessage());
            }
            this.terminalUi.printHorizontalLine();
            input = sc.nextLine();
        }
        sc.close();

        // Save data before exit
        writeToStorage();

        this.terminalUi.showExitMsg();
    }

    private void loadStorage() {
        try {
            this.storage.loadDataFromFile(this.taskList);
        } catch (TerminatorException e) {
            System.out.println("Invalid data format.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Returns a response from executing a user command.
     *
     * @return The response from the command.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command command = this.parser.parse(input);
            response = command.execute(this.taskList.getTaskList());
            if (command instanceof TodoCommand
                    || command instanceof EventCommand
                    || command instanceof DeadlineCommand
                    || command instanceof DeleteCommand) {
                response += taskList.getTasksRemaining();
            }
        } catch (TerminatorException de) {
            response = "Error detected: " + de.getMessage();
        }
        return response;
    }

    private void writeToStorage() {
        try {
            taskList.writeToDisk(this.storage);
        } catch (TerminatorException de) {
            System.out.println(de.getMessage());
        }
    }
}
