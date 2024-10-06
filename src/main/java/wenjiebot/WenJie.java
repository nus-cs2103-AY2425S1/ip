package wenjiebot;

import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

import wenjiebot.commands.Command;
import wenjiebot.exceptions.WenJieException;

/**
 * WenJie class represents the main application for the wenjiebot individual project.
 * It initializes the user interface, storage, and task list, and controls
 * the execution flow of the bot by processing user commands.
 */
public class WenJie {
    private static final String hardDisk = "./wenjie.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a WenJie instance with the specified file path for storage.
     * Initializes the user interface, storage, and task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public WenJie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Starts the WenJie application, displaying a welcome message and
     * continuously processing user commands until the exit condition is met.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        Supplier<String> commandSupplier = () -> ui.readCommand(scanner);
        Stream.generate(commandSupplier)
                .map(this::processCommand)
                .takeWhile(result -> !result)
                .forEach(result -> {});
        scanner.close();
    }

    /**
     * Processes a single user command.
     * Reads the command, parses it, executes it.
     * Handles any WenJieException that occurs during command execution.
     *
     * @param fullCommand the full command string entered by the user.
     * @return true if the command is an exit command, false otherwise.
     */
    private boolean processCommand(String fullCommand) {
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            return c.isExit();
        } catch (WenJieException e) {
            ui.showError(e.getMessage());
            return false;
        }
    }

    /**
     * The main entry point of the WenJie application.
     * Creates a WenJie instance with the specified file path and starts the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        WenJie wenJie = new WenJie(hardDisk);
        wenJie.run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (WenJieException e) {
            ui.showError(e.getMessage());
        }
        return ui.getOutput();
    }

    /**
     * Greets the user with a list of commands for them to try out
     */
    public void greeting() {
        ui.showWelcome();
    }
}
