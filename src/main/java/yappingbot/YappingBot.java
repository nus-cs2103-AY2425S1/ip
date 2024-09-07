package yappingbot;

import java.util.ArrayList;
import java.util.Scanner;

import yappingbot.commands.Commands;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.storage.Storage;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskListFilterView;
import yappingbot.tasks.tasklist.TaskTypes;
import yappingbot.ui.Ui;


/**
 * YappingBot class, interactive task manager app.
 */
public class YappingBot {

    private TaskList userList;
    private final String savefilePath;

    /**
     * Sets the savefilePath that this bot will load from
     * and save to. Saved item includes the task list.
     *
     * @param savefilePath the relative or absolute filepath
     */
    YappingBot(String savefilePath) {
        this.savefilePath = savefilePath;
    }

    /**
     * Initializes the bot. This takes a Storage object to load the tasks from,
     * or creates a new task list if none is found or any error is encountered when trying to
     * load the list from disk.
     *
     * @param storage the Storage object that interfaces with the disk.
     */
    private void init(Storage storage) {
        try {
            ArrayList<String> s = storage.loadListFromFile();
            userList = TaskList.generateFromRaw(s);
        } catch (YappingBotSaveFileNotFoundException e) {
            Ui.printError(e);
            userList = new TaskList();
        }
    }

    /**
     * Saves the task list to disk using the already-created Storage object.
     *
     * @param storage Storage object interfacing with the disk.
     */
    private void saveAndExit(Storage storage) {
        // REVERT LIST TO MAIN PARENT!
        resetView();
        try {
            storage.saveListToFile(userList.toRawFormat());
        } catch (YappingBotException e) {
            Ui.printError(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage()));
        }
    }

    /**
     * The main loop that receives and executes commands.
     */
    private void mainLoop() {
        Scanner userInputScanner = new Scanner(System.in);
        Parser parser = new Parser();
        while (userInputScanner.hasNextLine()) {
            String userInput = userInputScanner.nextLine().trim();
            String[] userInputSlices = userInput.split(" ");
            try {
                int taskListIndexPtr; // task list pointer
                switch (parser.parseCommand(userInputSlices[0])) {
                case EXIT:
                    return;
                case RESET_LIST:
                    resetView();
                    break;
                case LIST:
                    Commands.printUserList(userList);
                    break;
                case MARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.changeTaskListStatus(taskListIndexPtr, true, userList);
                    break;
                case UNMARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.changeTaskListStatus(taskListIndexPtr, false, userList);
                    break;
                case DELETE:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    Commands.deleteTask(taskListIndexPtr, userList);
                    break;
                case TODO:
                    Commands.createNewTask(userInputSlices, TaskTypes.TODO, userList);
                    break;
                case EVENT:
                    Commands.createNewTask(userInputSlices, TaskTypes.EVENT, userList);
                    break;
                case DEADLINE:
                    Commands.createNewTask(userInputSlices, TaskTypes.DEADLINE, userList);
                    break;
                case FIND:
                    String searchString = userInput.substring(userInput.indexOf(" ") + 1);
                    userList = Commands.findStringInTasks(searchString, userList);
                    break;
                case UNKNOWN:
                default:
                    throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e) {
                Ui.printError(e);
            }
        }
    }

    private void resetView() {
        // reset the view to main parent
        while (userList instanceof TaskListFilterView) {
            userList = ((TaskListFilterView) userList).getParent();
        }
        // TODO: add message
    }

    /**
     * Executes the initialization,
     * Entry point into the bot. This
     * main loop, and exiting.
     */
    void start() {
        Storage storage = new Storage(savefilePath);
        init(storage);
        Ui.print(ReplyTextMessages.GREETING_TEXT);
        mainLoop();
        Ui.print(ReplyTextMessages.EXIT_TEXT);
        saveAndExit(storage);
    }

    /**
     * Creates a YappingBot and runs it.
     * Main entry point.
     *
     * @param args if args[1] exists, it will be used for the savefile path instead of the
     *             default ./savefile.
     */
    public static void main(String[] args) {
    }
}
