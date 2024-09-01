package carly.utils;

import carly.exception.CarlyNoTaskDescription;
import carly.exception.CarlyUnknownIInputException;

import java.util.Arrays;

/**
 * Responsible for parsing user input and extracting commands and task descriptions.
 * It supports various command types such as BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, and EVENT.
 */
public class Parser {
    private final String input;
    private final Integer firstSpaceIndex;
    private final String[] parts;
    private final String action;

    public enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, FIND, TODO, DEADLINE, EVENT
    }


    public Parser(String input) {
        this.input = input;
        this.firstSpaceIndex = input.indexOf(" ");
        this.parts = input.split(" ");
        this.action = parts[0];
    }

    /**
     * Converts the user's input action string to a corresponding enum value.
     *
     * @return the corresponding enum value.
     * @throws CarlyUnknownIInputException if the input action does not match any known command.
     */
    public Command getCommand() throws CarlyUnknownIInputException {
        try {
            return Command.valueOf(this.action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CarlyUnknownIInputException(this.action);
        }
    }

    /**
     * Extracts the task description from the user's input after the command.
     *
     * @throws CarlyNoTaskDescription if the command requires a task description but none is provided.
     */
    public  String getDetailsAfterCommand(Command command)
            throws CarlyNoTaskDescription {
        Command[] noDescriptionCommands = {Command.BYE, Command.LIST};
        boolean requiresDescription = !Arrays.asList(noDescriptionCommands).contains(command);

        if (requiresDescription) {
            String taskDescription = input.substring(firstSpaceIndex + 1).trim();
            if ((!this.input.contains(" ")) || (taskDescription.isEmpty())) {
                throw new CarlyNoTaskDescription();
            }
            return taskDescription;
        }
        return input.trim();
    }
}
