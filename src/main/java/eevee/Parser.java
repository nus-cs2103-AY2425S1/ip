package eevee;

import eevee.task.Task;

/**
 * Parses user input to interpret the extracted commands and task numbers.
 */
public class Parser {
    /**
     * Enumerates the possible commands that can be executed by the Eevee program.
     */
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, FIND, PRIORITIZE;
    }

    /**
     * Parses user input to find the command.
     *
     * @param input The user input to be parsed.
     * @return The extracted command.
     * @throws EeveeException If command is invalid.
     */
    public Command parseCommand(String input) throws EeveeException {
        try {
            return Command.valueOf(input.toUpperCase().split(" ")[0]);
        } catch (IllegalArgumentException e) {
            throw new EeveeException("You seemed to have typed wrong. This is not a valid command.");
        }
    }

    /**
     * Parses user input to find the task number.
     *
     * @param input The user input to be parsed.
     * @return The extracted task number.
     * @throws EeveeException If the task number is invalid.
     */
    public int parseTaskNumber(String input) throws EeveeException {
        try {
            return Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new EeveeException("No task under the given task number. "
                    + "Did you type the wrong number?");
        }
    }

    public Task.Priority parsePriority(String input) throws EeveeException {
        input = input.trim().toUpperCase();

        if (input.contains("HIGH")) {
            return Task.Priority.HIGH;
        } else if (input.contains("MEDIUM")) {
            return Task.Priority.MEDIUM;
        } else if (input.contains("LOW")) {
            return Task.Priority.LOW;
        } else {
            throw new EeveeException("This is not a valid priority level. "
                    + "Priority level must be low, medium or high.");
        }
    }
}
