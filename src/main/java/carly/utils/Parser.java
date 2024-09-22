package carly.utils;

import java.util.Arrays;

import carly.exception.CarlyNoTaskDescription;
import carly.exception.CarlyUnknownInputException;


/**
 * Responsible for parsing user input and extracting commands and task description.
 * It supports various command types listed in Command enum.
 */
public class Parser {
    private final String input;
    private final Integer firstSpaceIndex;
    private final String action;

    /**
     * Constructs a Parser object with the given user input.
     * This constructor splits the input into parts based on spaces and extracts the first word as the action (command).
     *
     * @param input The user's input string to be parsed.
     */
    public Parser(String input) {
        this.input = input;
        this.firstSpaceIndex = input.indexOf(" ");
        this.action = input.split(" ")[0];
    }

    /**
     * Converts the user's input action string to its corresponding {@link Command} enum value.
     * If the exact match is not found, attempts to find the closest matching command using
     * fuzzy matching and suggests the best match.
     *
     * @return the corresponding {@link Command} enum value for the user's action.
     * @throws CarlyUnknownInputException if the user input doesn't match any valid command and
     * suggests the closest match.
     */
    public Command getCommand() throws CarlyUnknownInputException {
        try {
            return Command.valueOf(this.action.toUpperCase());
        } catch (IllegalArgumentException e) {
            Command bestMatch = FuzzyMatch.getBestMatch(this.action);

            throw new CarlyUnknownInputException(this.action,
                    String.format("Did you mean '%s'?", bestMatch));
        }
    }

    /**
     * Extracts the task description from the user's input after the command.
     *
     * @throws CarlyNoTaskDescription if the command requires a task description but none is provided.
     */
    public String getDetailsAfterCommand(Command command) throws CarlyNoTaskDescription {
        Command[] noDescriptionCommands = {Command.BYE, Command.LIST, Command.SORT};
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
