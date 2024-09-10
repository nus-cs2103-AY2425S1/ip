package LunaBot;

import LunaBot.command.Command;
import LunaBot.command.ExitCommand;
import LunaBot.storage.Storage;
import LunaBot.ui.Ui;
import LunaBot.task.TaskList;
import LunaBot.parser.Parser;
import LunaBot.exception.LunaBotException;

import java.util.Scanner;

/**
 * Represents the main bot for managing tasks with a command-line interface.
 * The LunaBot class is responsible for initializing the bot, handling user input, executing commands,
 * and managing tasks. It utilizes a {@link Storage} object to load and save tasks, a {@link TaskList}
 * to keep track of tasks, and a {@link Ui} object for user interaction.
 *
 */
public class LunaBot {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a LunaBot with the specified file path for storing tasks.
     * Initializes the user interface, storage, and task list.
     * If loading tasks from storage fails, initializes an empty task list and prints an error message.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public LunaBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (LunaBotException e) {
            ui.printError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the bot and enters a loop to handle user commands.
     * The loop continues until the exit command is received.
     * Each command is parsed and executed, and any errors encountered are reported to the user.
     */
    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Infinite loop, break on "bye" command
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);
                command.execute(taskList, ui, storage);

                // Check if the command is an LunaBot.command.ExitCommand, break the loop if so
                if (command instanceof ExitCommand) {
                    break;
                }
            } catch (LunaBotException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the application.
     * Creates an instance of LunaBot with a predefined file path and starts it.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String filePath = "data/tasks.txt";
        new LunaBot(filePath).run();
    }
}
