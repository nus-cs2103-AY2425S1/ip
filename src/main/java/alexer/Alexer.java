package alexer;

import alexer.command.Command;
import alexer.command.CommandHandler;
import alexer.task.Deadline;
import alexer.task.Event;
import alexer.task.Task;
import alexer.task.TaskManager;
import alexer.task.Todo;
import alexer.ui.Response;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * lexer (ˈleksər) - A computer program that performs lexical analysis.
 *
 * @author sayomaki
 */
public class Alexer {
    /** Name of the chatbot **/
    public static final String NAME = "Alexer";

    private static Alexer alexer;

    private final Scanner scanner;
    private final TaskManager tasks;
    private final CommandHandler commandHandler;

    /**
     * Returns an instance of the chatbot.
     *
     * @return the current bot instance
     */
    public static Alexer getInstance() {
        return alexer;
    }

    /**
     * Creates a new chatbot, and instantiate the relevant fields.
     * Also populates the instance of the chatbot.
     */
    public Alexer() {
        alexer = this;
        scanner = new Scanner(System.in);
        tasks = new TaskManager();
        commandHandler = new CommandHandler();
    }

    /**
     * Returns the task manager instance for the bot, which
     * handles all task-related actions and operations.
     *
     * @return An instance of the task manager
     */
    public TaskManager getTaskManager() {
        return tasks;
    }

    /**
     * Processes the user input provided, and executes the
     * relevant commands/operations
     * @param input the user input string
     */
    public Response processInput(String input) {
        List<String> arguments = new ArrayList<>(List.of(input.split(" ")));
        String command = arguments.remove(0);

        Command cmd = commandHandler.getCommand(command.toLowerCase());
        if (cmd != null) {
            return cmd.run(arguments.toArray(String[]::new));
        }

        if (command.equals("bye")) {
            Prompter.buildGoodbye().printToConsole();
            System.exit(0);
            return null;
        } else {
            return new Response(Prompter.MESSAGE_COMMAND_NOT_FOUND, Response.ResponseType.ERROR);
        }
    }

    /**
     * Prompts the user for input (in the form of commands)
     * This function will repeatedly call itself until a
     * terminating command (e.g. bye) is invoked.
     */
    public void promptLoop() {
        String input = scanner.nextLine();

        assert input != null; // we should always get some kind of input back

        Response response = processInput(input);
        response.printToConsole();
        promptLoop();
    }

    /**
     * Starts the console chatbot routine for text-based usage
     */
    public void promptConsole() {
        Prompter.buildLogo().printToConsole();
        Prompter.buildGreeting().printToConsole();
        promptLoop();
    }

    public static void main(String[] args) {
        Alexer alexer = new Alexer();
        alexer.promptConsole();
    }
}
