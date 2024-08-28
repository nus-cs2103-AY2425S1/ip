package barney;

import java.util.Scanner;

import barney.action.CommandManager;
import barney.action.commands.Command;
import barney.data.TaskList;
import barney.data.exception.BarneyException;
import barney.storage.Storage;
import barney.ui.Ui;

/**
 * Represents the Barney chatbot application. Barney is a command-line chatbot
 * that allows users to manage tasks. It provides features such as adding tasks,
 * marking tasks as done, and listing tasks. The application interacts with the
 * user through a command-line interface.
 * 
 * The main class of the application is Barney, which initializes the necessary
 * components such as the scanner, command manager, user interface, storage, and
 * task list. It also handles the main execution flow of the application.
 * 
 * The application reads and writes task data to a file specified by the user.
 * If no file path is provided, the default file path "list.txt" is used.
 * 
 * The main method of the Barney class is responsible for starting the
 * application. It creates an instance of Barney and calls the run method to
 * start the chat loop.
 * 
 * The run method is the entry point for the chat loop. It displays a welcome
 * message, reads user input, executes the corresponding command, and handles
 * any errors that occur during the execution. The chat loop continues until the
 * user decides to exit.
 * 
 * The application uses a TaskList to store and manage tasks. The TaskList is
 * initialized with task data loaded from the storage file. If loading the data
 * fails, an error message is displayed and an empty TaskList is created.
 * 
 * The application uses a CommandManager to parse user input and retrieve the
 * corresponding command. The CommandManager is responsible for creating the
 * appropriate command object based on the user input.
 * 
 * The application uses a Ui to interact with the user. The Ui is responsible
 * for displaying messages and receiving user input.
 * 
 * The application uses a Storage to read and write task data to the storage
 * file. The Storage class handles the file I/O operations.
 * 
 */
public class Barney {

    private Scanner scanner;
    private CommandManager commandManager;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

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

    public static void main(String[] args) {
        String saveFilePath = "list.txt";
        if (args.length > 0) {
            saveFilePath = args[0];
        }
        new Barney(saveFilePath).run();
    }

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
