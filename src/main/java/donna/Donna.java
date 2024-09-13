package donna;

import java.util.Scanner;

import donna.parse.ParsedCommand;
import donna.parse.Parser;
import donna.task.TaskList;


/**
 * Represents the main class for the Donna chatbot.
 */
public class Donna {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/donna-tasks.txt";
    private static Storage storage;
    private Ui ui;
    private TaskList tasks;
    private final CommandHandler commandHandler;
    private final Parser parser;


    /**
     * Constructs a Donna instance, initializing the user interface, storage,
     * parser, and loading previously saved tasks.
     */
    public Donna() {
        storage = new Storage(DIRECTORY_PATH, FILE_PATH);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
            ui = new Ui(!tasks.isEmpty());
        } catch (DonnaException e) {
            ui = new Ui(false);
            ui.display(ui.getErrorMessage(e.getMessage()));
            tasks = new TaskList();
        }
        commandHandler = new CommandHandler(ui, tasks, storage);
    }

    public Ui getDonnaUi() {
        return this.ui;
    }

    /**
     * Runs the main loop, processing user input and executing commands.
     * Used for a text UI
     * Handles exceptions and prints error messages as needed.
     */
    private void run() {
        ui.display(ui.getGreeting());
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            try {
                ParsedCommand result = parser.parse(input);
                ui.display(commandHandler.handleCommand(result));
                if ("exit".equals(result.getCommandType())) {
                    sc.close();
                    return;
                }
            } catch (DonnaException e) {
                ui.display(ui.getErrorMessage(e.getMessage()));
            }
        }
    }


    /**
     * Returns Donna's response based on the input.
     *
     * @return Donna's response
     */
    public String getResponse(String input) {
        try {
            ParsedCommand result = parser.parse(input);
            return commandHandler.handleCommand(result);
        } catch (DonnaException e) {
            return ui.getErrorMessage(e.getMessage());
        }
    }

    /**
     * Main method to start the Donna application.
     * Used for a text UI
     *
     * @param args Arguments.
     */
    public static void main(String[] args) {
        new Donna().run();
    }
}
