package yapper.commands;

import java.util.HashMap;

import yapper.exceptions.YapperException;

/**
 * A class to contain all default available commands for the Yapper application.
 */
public class CommandList {
    private static final String DUNNO = "¯\\\\_(ツ)_/¯";
    private static HashMap<String, Command> availableCommands;

    /**
     * Constructor for CommandList class.
     */
    public CommandList() {
        availableCommands = new HashMap<>();
        availableCommands.put("bye", new TerminationCommand());
        availableCommands.put("list", new ListOutTasksCommand());
        availableCommands.put("mark", new MarkCommand());
        availableCommands.put("unmark", new UnmarkCommand());
        availableCommands.put("find", new FindTaskCommand());
        availableCommands.put("delete", new DeleteTaskCommand());
        availableCommands.put("todo", new AddToDoCommand());
        availableCommands.put("deadline", new AddDeadlineCommand());
        availableCommands.put("event", new AddEventCommand());
    }

    /**
     * Retrieves the corresponding command relating to the user's input.
     *
     * @param commandName      a string representing the name of the command
     * @return                 a command from the hashmap value set
     * @throws YapperException if the command is not found within the hashmap
     */
    public Command getCommandToExecute(String commandName) throws YapperException {
        Command commandToExecute = availableCommands.get(commandName);
        if (commandToExecute == null) {
            throw new YapperException(DUNNO);
        }
        return commandToExecute;
    }
}
