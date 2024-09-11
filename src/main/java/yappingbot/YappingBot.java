package yappingbot;

import java.util.ArrayList;

import yappingbot.commands.CommandDispatcher;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotExceptionList;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.storage.Storage;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.tasks.tasklist.TaskTypes;
import yappingbot.ui.Ui;


/**
 * YappingBot class, interactive task manager app.
 */
public class YappingBot {

    private final CommandDispatcher commandDispatch;
    private final Parser parser;
    private final Storage storage;
    private final Ui ui;
    private TaskList userList;

    /**
     * Sets the savefilePath that this bot will load from
     * and save to. Saved item includes the task list.
     *
     * @param ui Ui interface that will handle input and output
     * @param storage Storage interface to save and retrieve savefiles
     */
    public YappingBot(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
        this.parser = new Parser();
        this.commandDispatch = new CommandDispatcher(ui);
    }

    /**
     * Initializes the bot. This takes a Storage object to load the tasks from,
     * or creates a new task list if none is found or any error is encountered when trying to
     * load the list from disk.
     */
    private void init() {
        ui.print(ReplyTextMessages.GREETING_TEXT);
        userList = new TaskList();
        try {
            ArrayList<String> s = storage.loadListFromFile();
            YappingBotExceptionList e = userList.generateFromRaw(s);
            e.checkExceptions();
        } catch (YappingBotSaveFileNotFoundException e) {
            ui.printError(e);
        }
    }

    /**
     * Saves the task list to disk using the already-created Storage object.
     */
    private void saveAndCleanup() {
        // REVERT LIST TO MAIN PARENT!
        userList = commandDispatch.resetView(userList, true);
        try {
            storage.saveListToFile(userList.toRawFormat());
        } catch (YappingBotException e) {
            ui.printError(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage()));
        }
        ui.print(ReplyTextMessages.EXIT_TEXT);
    }

    /**
     * The main loop that receives and executes commands.
     */
    private void mainLoop() {
        while (ui.hasInputLines()) {
            String userInput = ui.getNextInputLine().trim();
            String[] userInputSlices = userInput.split(" ");
            int taskListIndexPtr; // task list pointer

            try {
                switch (parser.parseCommand(userInputSlices[0])) {
                case EXIT:
                    // exits the function, ending the main loop
                    return;
                case RESET_LIST:
                    // resets any filter on the list
                    userList = commandDispatch.resetView(userList, false);
                    break;
                case LIST:
                    // lists out all tasks that fits current filter (if any)
                    commandDispatch.printUserList(userList);
                    break;
                case MARK:
                    // marks a task as Done, given the index
                    Parser.checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, true, userList);
                    break;
                case UNMARK:
                    // unmarks a task as Done, given the index
                    Parser.checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, false, userList);
                    break;
                case DELETE:
                    // deletes a task from list, given the index
                    Parser.checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.deleteTask(taskListIndexPtr, userList);
                    break;
                case TODO:
                    // creates a new to-do task
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.TODO, userList);
                    break;
                case EVENT:
                    // creates a new event task
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.EVENT, userList);
                    break;
                case DEADLINE:
                    // creates a new deadline task
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.DEADLINE, userList);
                    break;
                case FIND:
                    // creates a filtered list view with tasks matching the given criteria
                    Parser.checkMinimumArgsAvailable(userInputSlices, 1);
                    String searchString = userInput.substring(userInput.indexOf(" ") + 1);
                    userList = commandDispatch.findStringInTasks(searchString, userList);
                    break;
                case UNKNOWN:
                default:
                    // catch-all for malformed commands marked UNKNOWN, or any uncaught and
                    // unimplemented comannd
                    throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e) {
                ui.printError(e);
            }
        }
    }

    /**
     * Executes the initialization,
     * Entry point into the bot. This
     * main loop, and exiting.
     */
    public void start() {
        init();
        mainLoop();
        saveAndCleanup();
    }
}
