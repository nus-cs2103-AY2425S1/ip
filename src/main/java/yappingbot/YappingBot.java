package yappingbot;

import java.util.ArrayList;

import yappingbot.commands.Parser;
import yappingbot.commands.commands.ChangeTaskListStatusCommand;
import yappingbot.commands.commands.CreateDeadlineCommand;
import yappingbot.commands.commands.CreateEventCommand;
import yappingbot.commands.commands.CreateTodoCommand;
import yappingbot.commands.commands.DeleteTaskCommand;
import yappingbot.commands.commands.FindStringInTasksCommand;
import yappingbot.commands.commands.PrintUserTaskListCommand;
import yappingbot.commands.commands.ResetViewCommand;
import yappingbot.exceptions.YappingBotException;
import yappingbot.exceptions.YappingBotExceptionList;
import yappingbot.exceptions.YappingBotSaveFileNotFoundException;
import yappingbot.exceptions.YappingBotUnknownCommandException;
import yappingbot.storage.Storage;
import yappingbot.stringconstants.ReplyTextMessages;
import yappingbot.tasks.tasklist.TaskList;
import yappingbot.ui.Ui;


/**
 * YappingBot class, interactive task manager app.
 */
public class YappingBot {

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
        } catch (YappingBotException e) {
            ui.printError(e);
        }
    }

    /**
     * Saves the task list to disk using the already-created Storage object.
     */
    private void saveAndCleanup() {
        try {
            // REVERT LIST TO MAIN PARENT!
            userList = new ResetViewCommand()
                    .setEnvironment(ui, userList, true)
                    .runCommand()
                    .getNewUserList();
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

            try {
                switch (parser.parseCommand(userInputSlices[0])) {
                case EXIT:
                    // exits the function, ending the main loop
                    return;
                case ALIAS:
                    // creates new alias
                    // TODO: abstract this out to a Command object
                    parser.addAlias(userInputSlices[1], userInputSlices[2]);
                    ui.printf(ReplyTextMessages.ALIAS_ADDED_TEXT_2s,
                              userInputSlices[1],
                              userInputSlices[2]);
                    break;
                case RESET_LIST:
                    // resets any filter on the list
                    userList = new ResetViewCommand()
                            .setEnvironment(ui, userList, false)
                            .runCommand()
                            .getNewUserList();
                    break;
                case LIST:
                    // lists out all tasks that fits current filter (if any)
                    new PrintUserTaskListCommand()
                            .setEnvironment(ui, userList).runCommand();
                    break;
                case MARK:
                    // marks a task as Done, given the index
                    userList = new ChangeTaskListStatusCommand(userInputSlices)
                            .setEnvironment(ui, userList, true)
                            .runCommand()
                            .getNewUserList();
                    break;
                case UNMARK:
                    // unmarks a task as Done, given the index
                    userList = new ChangeTaskListStatusCommand(userInputSlices)
                            .setEnvironment(ui, userList, false)
                            .runCommand()
                            .getNewUserList();
                    break;
                case DELETE:
                    // deletes a task from list, given the index
                    userList = new DeleteTaskCommand(userInputSlices)
                            .setEnvironment(ui, userList)
                            .runCommand()
                            .getNewUserList();
                    break;
                case TODO:
                    // creates a new to-do task
                    userList = new CreateTodoCommand(userInputSlices)
                            .setEnvironment(ui, userList)
                            .runCommand()
                            .getNewUserList();
                    break;
                case EVENT:
                    // creates a new event task
                    userList = new CreateEventCommand(userInputSlices)
                            .setEnvironment(ui, userList)
                            .runCommand()
                            .getNewUserList();
                    break;
                case DEADLINE:
                    // creates a new deadline task
                    userList = new CreateDeadlineCommand(userInputSlices)
                            .setEnvironment(ui, userList)
                            .runCommand()
                            .getNewUserList();
                    break;
                case FIND:
                    // creates a filtered list view with tasks matching the given criteria
                    userList = new FindStringInTasksCommand(userInputSlices)
                            .setEnvironmen(ui, userList)
                            .runCommand()
                            .getNewUserList();
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
