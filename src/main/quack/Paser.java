package quack;

import quack.command.Command;
import quack.command.AddTaskCommand;
import quack.command.DeleteTaskCommand;
import quack.command.ExitCommand;
import quack.command.ListCommand;
import quack.command.UpdateTaskCommand;
import quack.exception.InvalidCommandException;

/**
 * This class is responsible for handling user input commands.
 */
public class Paser {
    
    /**
     * Creates a Paser object.
     */
    public Paser() {

    }

    /**
     * Retrieves the next input command by the user.
     * @param sc A scanner object used to get the user input.
     * @throws InvalidCommandException Signals that the command given is invalid.
     */
    public Command getUserInput(Ui ui) throws InvalidCommandException{
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
            command = new ListCommand();
            return command;

        case "bye":
        case "exit":
            command = new ExitCommand();
            return command;
        
        case "add":
            command = new AddTaskCommand();
            return command;

        case "mark":
        case "unmark":
            command = new UpdateTaskCommand(userCommand);
            return command;
        
        case "delete":
            command = new DeleteTaskCommand();
            return command;
        
        default:
            throw new InvalidCommandException(userCommand);
        }
    }
}
