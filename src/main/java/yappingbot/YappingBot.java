package yappingbot;

import java.util.ArrayList;

import yappingbot.commands.CommandDispatcher;
import yappingbot.commands.Parser;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotInvalidSaveFileException;
import yappingbot.exceptions.YappingBotOobException;
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
        userList = new TaskList();
        try {
            ArrayList<String> s = storage.loadListFromFile();
            YappingBotInvalidSaveFileException e = userList.generateFromRaw(s);
            // Lord forgive me for returning execptions without throwing them is so
            // illegal.
            if (e != null) {
                ui.printError(e);
            }
        } catch (YappingBotSaveFileNotFoundException e) {
            ui.printError(e);
        }
    }

    /**
     * Saves the task list to disk using the already-created Storage object.
     */
    private void saveAndCleanup() {
        // REVERT LIST TO MAIN PARENT!
        userList = commandDispatch.resetView(userList);
        try {
            storage.saveListToFile(userList.toRawFormat());
        } catch (YappingBotException e) {
            ui.printError(String.format(ReplyTextMessages.SAVE_FILE_ERROR_1s, e.getMessage()));
        }
    }

    /**
     * The main loop that receives and executes commands.
     */
    private void mainLoop() {
        while (ui.hasNextLine()) {
            String userInput = ui.getNextLine().trim();
            String[] userInputSlices = userInput.split(" ");
            try {
                int taskListIndexPtr; // task list pointer
                switch (parser.parseCommand(userInputSlices[0])) {
                case EXIT:
                    return;
                case RESET_LIST:
                    userList = commandDispatch.resetView(userList);
                    break;
                case LIST:
                    commandDispatch.printUserList(userList);
                    break;
                case MARK:
                    checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, true, userList);
                    break;
                case UNMARK:
                    checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, false, userList);
                    break;
                case DELETE:
                    checkMinimumArgsAvailable(userInputSlices, 1);
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.deleteTask(taskListIndexPtr, userList);
                    break;
                case TODO:
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.TODO, userList);
                    break;
                case EVENT:
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.EVENT, userList);
                    break;
                case DEADLINE:
                    commandDispatch.createNewTask(userInputSlices, TaskTypes.DEADLINE, userList);
                    break;
                case FIND:
                    String searchString = userInput.substring(userInput.indexOf(" ") + 1);
                    userList = commandDispatch.findStringInTasks(searchString, userList);
                    break;
                case UNKNOWN:
                default:
                    throw new YappingBotUnknownCommandException();
                }
            } catch (YappingBotException e) {
                ui.printError(e);
            }
        }
    }

    private void checkMinimumArgsAvailable(String[] userInputSlices, int i)
    throws YappingBotOobException {
        if ((userInputSlices.length - 1) < i) {
            throw new YappingBotOobException("", i);
        }
    }

    /**
     * Executes the initialization,
     * Entry point into the bot. This
     * main loop, and exiting.
     */
    public void start() {
        init();
        ui.print(ReplyTextMessages.GREETING_TEXT);
        mainLoop();
        saveAndCleanup();
        ui.print(ReplyTextMessages.EXIT_TEXT);
    }
}
