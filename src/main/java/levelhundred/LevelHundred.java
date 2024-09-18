package levelhundred;

import java.util.ArrayList;
import java.util.Scanner;

import command.UserCommand;
import exception.InvalidStorageFileException;
import exception.LevelHundredException;
import task.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;
import utility.Parser;

/**
 * The LevelHundred class is the main class responsible for
 * the overall logic of the project
 */
public class LevelHundred {
    private final String name = "LevelHundred";
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;
    private boolean isRunning;

    /**
     * Constructor for a LevelHundred bot
     */
    public LevelHundred() {
        this.isRunning = true;
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.initialiseTaskList();
    }

    private void initialiseTaskList() {
        try {
            ArrayList<Task> tasks = this.storage.load();
            tasks.forEach(t -> this.taskList.addTask(t));
        } catch (InvalidStorageFileException e) {
            this.ui.printException(e);
        }
    }

    private void run() {
        this.ui.greet(this.name);

        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        String userInput;

        while (isRunning) {
            userInput = sc.nextLine();
            try {
                UserCommand c = Parser.parseUserCommand(userInput);
                c.execute(userInput, this.ui, this.storage, this.taskList);
                isRunning = c.continueRunning();
            } catch (LevelHundredException e) {
                this.ui.printException(e);
            }
        }

        sc.close();
    }

    /**
     * Generates the greet message for the chatbot
     * @return String representing the greeting message
     */
    public String getGreeting() {
        return "Hello, I'm " + this.name + "\n" + "What can I do for you?";
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String userInput) {
        try {
            UserCommand c = Parser.parseUserCommand(userInput);
            c.execute(userInput, this.ui, this.storage, this.taskList);
            this.isRunning = c.continueRunning();

            return c.getResponse();
        } catch (LevelHundredException e) {
            return "Error: " + e.toString();
        }
    }

    /**
     * Checks whether the bot has exited
     * @return Boolean representing whether the bot has exited
     */
    public boolean isExited() {
        return !this.isRunning;
    }

    public static void main(String[] args) {
        // For the CLI version of chatbot
        LevelHundred chatbot = new LevelHundred();
        chatbot.run();
    }
}
