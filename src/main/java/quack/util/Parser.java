package quack.util;

import quack.command.AddTagCommand;
import quack.command.AddTaskCommand;
import quack.command.Command;
import quack.command.DeleteTaskCommand;
import quack.command.ExitCommand;
import quack.command.FindTaskCommand;
import quack.command.ListCommand;
import quack.command.RemoveTagCommand;
import quack.command.UpdateTaskCommand;
import quack.exception.InvalidCommandException;

/**
 * This class is responsible for handling user input commands.
 */
public class Parser {

    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** To store all of the users tasks */
    private TaskList taskList;
    /** Sotrage object to load and save data */
    private Storage storage;

    /**
     * Creates a Paser object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param storage Storage object to save and load data from the save file.
     * @param ui The ui object that handles user interface requests.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Processes the command entered by the user.
     * @param userCommand The command in string form which the user inputed.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    public Command processCommand(String userCommand) {
        Command command;
        switch (userCommand) {
        case "list":
            command = new ListCommand(taskList, ui);
            return command;

        case "bye":
        case "exit":
            command = new ExitCommand(taskList, storage, ui);
            return command;

        case "add":
            command = new AddTaskCommand(taskList, ui);
            return command;

        case "mark":
        case "unmark":
            command = new UpdateTaskCommand(userCommand, taskList, ui);
            return command;

        case "delete":
            command = new DeleteTaskCommand(taskList, ui);
            return command;

        case "find":
            command = new FindTaskCommand(taskList, ui);
            return command;

        case "tag":
            command = new AddTagCommand(taskList, ui);
            return command;

        case "untag":
            command = new RemoveTagCommand(taskList, ui);
            return command;

        default:
            ui.printExceptionMessage(new InvalidCommandException(userCommand));
            return null;
        }
    }
}
