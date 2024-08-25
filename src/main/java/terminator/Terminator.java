package terminator;

import terminator.command.CommandParser;
import terminator.command.Command;
import terminator.command.DeadlineCommand;
import terminator.command.DeleteCommand;
import terminator.command.TerminatorException;
import terminator.command.EventCommand;
import terminator.command.TodoCommand;
import terminator.task.Storage;
import terminator.task.TaskList;

import java.util.Scanner;

/**
 * Main class for the chatbot application.
 */
public class Terminator {

    private final TaskList taskList;

    private final CommandParser parser;

    private final Ui ui;

    private final Storage storage;

    public Terminator() {
        this.taskList = new TaskList();
        this.parser = new CommandParser();
        this.ui = new Ui();
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
    private void run() {
        // Load data from storage
        try {
            this.storage.loadDataFromFile(this.taskList);
        } catch (TerminatorException e) {
            System.out.println("Invalid data format.");
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            this.ui.showErrorMsg();
            return;
        }

        // Start main event loop
        this.ui.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            this.ui.printHorizontalLine();
            try {
                Command command = this.parser.parse(input);
                command.execute(this.taskList.getTaskList());
                if (command instanceof TodoCommand
                    || command instanceof EventCommand
                    || command instanceof DeadlineCommand
                    || command instanceof DeleteCommand) {
                    taskList.printTasksRemaining();
                }
            } catch (TerminatorException de) {
                System.out.println("Error detected: " + de.getMessage());
            }
            this.ui.printHorizontalLine();
            input = sc.nextLine();
        }
        sc.close();

        // Save data before exit
        try {
            taskList.writeToDisk(this.storage);
        } catch (TerminatorException de) {
            System.out.println(de.getMessage());
        }

        this.ui.showExitMsg();
    }

    public static void main(String[] args) {
        Terminator tChatbot = new Terminator();
        tChatbot.run();
    }
}
