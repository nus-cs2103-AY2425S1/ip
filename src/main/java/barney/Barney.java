package barney;

import java.util.Scanner;

import barney.action.CommandManager;
import barney.action.commands.Command;
import barney.data.TaskList;
import barney.data.exception.BarneyException;
import barney.data.exception.InvalidArgumentException;
import barney.storage.Storage;
import barney.ui.Gui;
import barney.ui.SystemOutUI;
import barney.ui.Ui;

/**
 * The Barney class represents a chatbot application that allows users to
 * interact with it through a command-line interface. It provides functionality
 * for managing tasks and saving/loading data.
 */
public class Barney {

    private final Scanner scanner;
    private final CommandManager commandManager;
    private final Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructs a Barney object with the specified file path.
     *
     * @param filePath The file path to save/load data from.
     */
    public Barney(String filePath) {
        scanner = new Scanner(System.in);
        commandManager = new CommandManager();
        storage = new Storage(filePath);


    }

    /**
     * Initialises the GUI for Barney.
     *
     * @return The output message.
     */
    public String initialiseGui() {
        this.ui = new Gui();
        String output = "";
        try {
            tasks = new TaskList(storage.loadData());
            output += ui.printLoadData(tasks);
        } catch (BarneyException e) {
            output += ui.printLoadingError(e.getMessage());
            tasks = new TaskList();
        }
        output += ui.printWelcome();

        return output;
    }

    public String getResponse(String input) {
        return "testing";
    }

    /**
     * Runs the Barney application for System.out.
     */
    public void runSystemOut() {
        this.ui = new SystemOutUI();
        try {
            tasks = new TaskList(storage.loadData());
            ui.printLoadData(tasks);
        } catch (BarneyException e) {
            ui.printLoadingError(e.getMessage());
            tasks = new TaskList();
        }

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
