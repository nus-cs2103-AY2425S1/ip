package lawrence.app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import lawrence.command.Command;
import lawrence.command.CommandType;
import lawrence.database.TaskFileManager;
import lawrence.parser.CommandParser;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * The Lawrence chatbot that is able to keep track of tasks and their
 * respective completion statuses.
 * <p>
 * The tasks are saved automatically after every action.
 * This ensures that all changes are preserved and will be available
 * the next time the program is run.
 * </p>
 * @author Kok Bo Chang
 */
public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Path SAVE_LOCATION = Paths.get(".", "data", "tasks.txt");

    private final TaskFileManager manager;
    private TaskList tasks;
    private final UserInterface ui;

    private Command previousCommand;

    /**
     * Default constructor.
     */
    public Lawrence() {
        ui = new UserInterface(NAME);
        manager = new TaskFileManager(SAVE_LOCATION);
        try {
            Task[] existingTasks = manager.readTasksFromFile();
            tasks = new TaskList(existingTasks);
        } catch (IOException e) {
            // initialise with no tasks instead
            tasks = new TaskList(new Task[0]);
        }
    }

    /**
     * Initialises and runs the Lawrence chatbot.
     *
     * @param args optional startup arguments
     */
    public static void main(String[] args) {
        Lawrence primeMinister = new Lawrence();
        primeMinister.run();
    }

    /**
     * Runs the chatbot to start listening for user commands
     * in the console.
     */
    public void run() {
        ui.greetUser();

        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean shouldContinue = true;
        while (shouldContinue) {
            userInput = sc.nextLine(); // Get next user input
            try {
                Command c = CommandParser.createCommand(userInput);
                c.execute(tasks, manager, ui);
                shouldContinue = c.shouldContinue();
            } catch (IllegalArgumentException | IllegalStateException e) {
                ui.showMessage(String.format("%s Please try again.", e.getMessage()));
            }
        }
    }

    /**
     * Returns the text response of the bot after parsing the input string and executing the relevant command.
     *
     * @param input the input string containing instructions on what command to run
     * @return a string containing the bot's response
     */
    public String getResponse(String input) {
        try {
            Command c = CommandParser.createCommand(input);
            c.execute(tasks, manager, ui);
            previousCommand = c;
            return c.getResponse();
        } catch (IllegalArgumentException | IllegalStateException e) {
            return String.format("%s Please try again.", e.getMessage());
        }
    }

    /**
     * Returns the type of command previously executed. If no command was executed, returns null.
     *
     * @return the type of the previous command, null if no previous command exists
     */
    public CommandType getPreviousCommandType() {
        if (previousCommand == null) {
            return null;
        }

        return previousCommand.getType();
    }

    /**
     * Returns a boolean indicating whether the program should continue running.
     * <p>
     * Changes with the execution of different commands.
     * </p>
     *
     * @return a boolean indicating whether the program should continue running
     */
    public boolean shouldContinue() {
        if (previousCommand == null) {
            return true;
        }

        return previousCommand.shouldContinue();
    }
}
