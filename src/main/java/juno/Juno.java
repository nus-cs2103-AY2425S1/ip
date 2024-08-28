package juno;

import juno.command.Command;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.parser.CommandParser;
import juno.task.Task;
import juno.ui.JunoUi;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Juno} class serves as the main entry point for Juno chat bot.
 * It initialises the core components of the chat bot such as the UI, file management, task management,
 * and command parsing. These components instances are then passed around whenever the command or implementation
 * require its functionality.
 * The chat bot also interacts with users, processing their inputs and executing appropriate commands.
 */
public class Juno {
    private TaskManager taskManager;
    private FileManager fileManager;
    private JunoUi junoUi;
    private CommandParser commandParser;

    /**
     * Constructs a new {@code Juno} instance.
     */
    public Juno() {
    }

    /**
     * Starts the Juno chat bot by initialising all necessary instances, including the UI,
     * file manager, task manager, and command parser. It also loads stored tasks from a file
     * and detect what user has inputted.
     */
    public void startBot() {

        // start the UI
        this.junoUi = new JunoUi();

        // file manager to handle all file related method calls
        this.fileManager = new FileManager();

        // check if the tasks.json file exist
        this.fileManager.ensureFileExist();

        this.commandParser = new CommandParser();

        // read the data from the file
        ArrayList<Task> storedTasks = this.fileManager.readTasksFromFile();

        // task manager to handle all the task related method calls
        this.taskManager = new TaskManager(storedTasks);

        // display welcome message for users
        this.junoUi.displayWelcomeMessage();

        // detect what user inputs with a scanner
        this.detectUserInput();
    }

    /**
     * Detects user input through the command line.
     * User input is parsed into commands, which are then executed. The loop continues until
     * the user inputs a command "bye" to terminate the chat bot.
     */
    public void detectUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean isInWhileLoop = true;

        while (isInWhileLoop) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = this.commandParser.parse(userInput, this.junoUi, this.fileManager, this.taskManager);
                command.runCommand();
                isInWhileLoop = command.isInWhileLoop();
            } catch (TaskManagerException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Juno junoChatBot = new Juno();
        junoChatBot.startBot();
    }
}
