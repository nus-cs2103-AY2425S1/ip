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
import yappingbot.ui.UiCli;


/**
 * YappingBot class, interactive task manager app.
 */
public class YappingBot {

    private TaskList userList;
    private final String savefilePath;
    private Storage storage;
    private Ui ui;

    /**
     * Sets the savefilePath that this bot will load from
     * and save to. Saved item includes the task list.
     *
     * @param savefilePath the relative or absolute filepath
     * @param ui Ui interface that will handle input and output
     */
    YappingBot(String savefilePath, Ui ui) {
        this.ui = ui;
        this.savefilePath = savefilePath;
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
        this.storage = new Storage(savefilePath);
        init();
        ui.print(ReplyTextMessages.GREETING_TEXT);
        mainLoop();
        ui.print(ReplyTextMessages.EXIT_TEXT);
        saveAndExit();
    }
}
