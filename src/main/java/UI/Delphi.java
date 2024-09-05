package UI;

import java.util.Scanner;

import Commands.Command;
import Exceptions.DelphiException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;

/**
 * The main class for running the UI.Delphi application.
 * It manages the core components such as the task list, storage, parser, and user interface.
 */
public class Delphi {
    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs a UI.Delphi instance with the specified file path for storage.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public Delphi(String filePath) {
        storage = new Storage(filePath);
        taskList = new TaskList();
        parser = new Parser();
        ui = new Ui();
    }

    /**
     * Starts the application, handles user input, and manages the task list.
     * Displays the welcome message, processes commands, and exits upon receiving the exit command.
     */
    public void run() {
        boolean isExitCommand = false;
        ui.welcomeMessage();
        System.out.println("current tasks:");
        taskList.loadStorageToTasks(this.storage);
        taskList.printTasks();
        Scanner scanner = new Scanner(System.in);
        while (!isExitCommand) {
            try {
                Command c = parser.parseInput(scanner.nextLine());
                c.execute(taskList, storage, ui);
                isExitCommand = c.exitBot();
            } catch (DelphiException e) {
                System.out.println(e.getMessage());
            }
        }
        ui.goodbyeMessage();
    }

    /**
     *
     * @return the tasks stored in the hard drive at the time of starting the bot
     */
    public String currentTasks() {
        taskList.loadStorageToTasks(this.storage);
        String res = "current tasks: \n" + taskList.printTasks();
        return res;
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parseInput(input);
            //c.execute(taskList, storage, ui);
            return c.execute(taskList, storage, ui);
        } catch (DelphiException e) {
            return e.getMessage();
        }
    }

    /**
     * The entry point of the application.
     * Creates an instance of UI.Delphi and starts it with the specified storage file path.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Delphi("../ip/src/main/HardDisk.txt").run();
    }
}

