package quack;

import quack.command.AddTaskCommand;
import quack.command.Command;
import quack.command.DeleteTaskCommand;
import quack.command.ExitCommand;
import quack.command.FindTaskCommand;
import quack.command.ListCommand;
import quack.command.UpdateTaskCommand;

import quack.exception.InvalidCommandException;

/**
 * This class is responsible for handling user input commands.
 */
public class Paser {
    
    /** Quack chatbot object */
    private Quack quack;
    /** Ui object to handle UI interface tasks */
    private Ui ui;
    /** To store all of the users tasks */
    private TaskList taskList;
    /** Sotrage object to load and save data */
    private Storage storage;

    /**
     * Creates a Paser object.
     * @param quack The chatbot object quack.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param storage Storage object to save and load data from the save file.
     * @param ui The ui object that handles user interface requests.
     */
    public Paser(Quack quack, TaskList taskList, Storage storage, Ui ui) {
        this.quack = quack;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Retrieves the next input command by the user.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    public Command getUserInput() throws InvalidCommandException {
        try {
            String userCommand = ui.requestUserCommand();
            return this.processCommand(userCommand.toLowerCase());
        } catch (InvalidCommandException commandError) {
            throw commandError;
        }
    }

    /**
     * Processes the command entered by the user.
     * @param input The command in string form which the user inputed.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    private Command processCommand(String userCommand) throws InvalidCommandException {
        Command command;
        switch (userCommand) {
        case "list":
            command = new ListCommand(taskList, ui);
            return command;

        case "bye":
        case "exit":
            command = new ExitCommand(quack, taskList, storage);
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
        
        default:
            throw new InvalidCommandException(userCommand);
        }
    }
}
