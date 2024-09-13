package bob;

import java.util.ArrayList;
import java.util.Scanner;

import bob.command.Command;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * This is a chatBot class named Bob.
 */
public class Bob {
    private ArrayList<Task> records;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private String commandType;
    /**
     * Initialises an instance of the chatBot Bob.
     * Bob's ui, storage and taskList is also initialised.
     *
     * @param filePath Relative file path of where the records will be saved.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        ArrayList<Task> records = storage.loadTaskList();
        if (records == null) {
            taskList = new TaskList();
        } else {
            taskList = new TaskList(records); //records are loaded
        }
    }

    /**
     * Runs the main method of the program.
     */
    public static void main(String[] args) {
        Bob bob = new Bob("src/main/java/bob/data/tasks.txt");
        bob.run1();
    }

    /**
     * Starts the main program for the chatBot.
     */
    public void run() {
        ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim(); //input with NO whitespace in front/back
        while (!input.equals("bye")) {
            String[] inputWords = input.split("\s+");
            String keyword = inputWords[0];

            // update taskList, storage reads from file
            // Command command = Parser.parseCommand(input); //get the specific command type
            // String output = command.execute(taskList, storage) //executes. storage will save to file

            switch (keyword) {
            case "list":
                taskList.listRecords();
                break;
            case "mark":
                taskList.updateMark(input, inputWords, true);
                break;
            case "unmark":
                taskList.updateMark(input, inputWords, false);
                break;
            case "delete":
                taskList.delete(input);
                break;
            case "event":
                taskList.addTask(input, inputWords);
                break;
            case "deadline":
                taskList.addTask(input, inputWords);
                break;
            case "todo":
                taskList.addTask(input, inputWords);
                break;
            case "find":
                taskList.find(input);
                break;
            default:
                Ui.requestValidCommand();
            }
            taskList.saveRecords(storage);
            input = scanner.nextLine().trim();
        }
        Ui.showGoodBye();

    }

    /**
     * Runs the main program.
     */
    public void run1() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            taskList = storage.loadUpdatedTaskList(); //Updates taskList based on previous input.
            String fullCommand = ui.readCommand();
            ui.showLine();
            Command c = Parser.parseCommand(fullCommand);
            c.execute(taskList, storage, ui);
            isExit = c.isExit();
            ui.showLine();
        }
        Ui.showGoodBye();
    }

    /**
     * Generates a response for the user's chat message.
     *
     * @param input
     */
    public String getResponse(String input) {
        taskList = storage.loadUpdatedTaskList(); //Updates taskList based on previous input.
        Command c = Parser.parseCommand(input);
        String output = c.execute(taskList, storage, ui);
        commandType = c.getClass().getSimpleName();
        return output;
    }

    /**
     * Returns the command type.
     */
    public String getCommandType() {
        return commandType;
    }
}
