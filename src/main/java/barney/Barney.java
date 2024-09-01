package barney;

import java.util.Scanner;

import barney.action.CommandManager;
import barney.action.commands.Command;
import barney.data.TaskList;
import barney.data.exception.BarneyException;
import barney.data.exception.InvalidArgumentException;
import barney.storage.Storage;
import barney.ui.Ui;

/**
 * The Barney class represents a chatbot application that allows users to
 * interact with it through a command-line interface. It provides functionality
 * for managing tasks and saving/loading data.
 */
public class Barney {

    private final Scanner scanner;
    private final CommandManager commandManager;
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a Barney object with the specified file path.
     *
     * @param filePath The file path to save/load data from.
     */
    public Barney(String filePath) {
        scanner = new Scanner(System.in);
        commandManager = new CommandManager();
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
            ui.printLoadData(tasks);
        } catch (BarneyException e) {
            ui.printLoadingError(e.getMessage());
            tasks = new TaskList();
        }

    }

    /**
     * The main entry point for the Barney application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        String saveFilePath = "list.txt";
        if (args.length > 0) {
            saveFilePath = args[0];
        }
        new Barney(saveFilePath).run();
    }

    /**
     * Runs the Barney application.
     */
    public void run() {
        ui.printWelcome();

        boolean isChatting = true;
        while (isChatting) {
            ui.printInput();
            try {
                String line = scanner.nextLine();
                if (line.matches("^\\s*$")) {
                    continue;
                }
                Command command = commandManager.getCommand(line);
                isChatting = command.execute(tasks, ui);
            } catch (InvalidArgumentException e) {
                ui.printInvalidCommand();
            } catch (BarneyException e) {
                ui.printChatError(e.getMessage());
            } catch (Exception e) {
                ui.printChatError("An unknown error occurred. Please try again. " + e.getMessage());
            }

            try {
                storage.writeData(tasks);
            } catch (BarneyException e) {
                ui.printSaveError(e.getMessage());
            }
        }
        ui.printBye();
        scanner.close();
    }
}
