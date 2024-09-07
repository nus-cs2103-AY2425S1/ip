package yappingbot;

import java.util.ArrayList;

import yappingbot.commands.CommandDispatcher;
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

    private final Ui ui;
    private final Storage storage;
    private TaskList userList;

    /**
     * Sets the savefilePath that this bot will load from
     * and save to. Saved item includes the task list.
     *
     * @param ui Ui interface that will handle input and output
     * @param storage Storage interface to save and retrieve savefiles
     */
    YappingBot(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Initializes the bot. This takes a Storage object to load the tasks from,
     * or creates a new task list if none is found or any error is encountered when trying to
     * load the list from disk.
     */
    private void init() {
        try {
            ArrayList<String> s = storage.loadListFromFile();
            userList = TaskList.generateFromRaw(s);
        } catch (YappingBotSaveFileNotFoundException e) {
            ui.printError(e);
            userList = new TaskList();
        }
    }

    /**
     * Saves the task list to disk using the already-created Storage object.
     */
    private void saveAndExit() {
        // REVERT LIST TO MAIN PARENT!
        resetView();
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
        Parser parser = new Parser();
        CommandDispatcher commandDispatch = new CommandDispatcher(ui);
        while (ui.hasNextLine()) {
            String userInput = ui.getNextLine().trim();
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
                    commandDispatch.printUserList(userList);
                    break;
                case MARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, true, userList);
                    break;
                case UNMARK:
                    taskListIndexPtr = Parser.parseTaskNumberSelected(userInputSlices[1]);
                    commandDispatch.changeTaskListStatus(taskListIndexPtr, false, userList);
                    break;
                case DELETE:
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

    private void resetView() {
        // reset the view to main parent
        while (userList instanceof TaskListFilterView) {
            userList = ((TaskListFilterView) userList).getParent();
        }
        ui.println(ReplyTextMessages.RESET_TEXT);
    }

    /**
     * Executes the initialization,
     * Entry point into the bot. This
     * main loop, and exiting.
     */
    void start() {
        init();
        ui.print(ReplyTextMessages.GREETING_TEXT);
        mainLoop();
        ui.print(ReplyTextMessages.EXIT_TEXT);
        saveAndExit();
    }
}
