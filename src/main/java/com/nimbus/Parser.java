package com.nimbus;

import com.commands.ByeCommand;
import com.commands.Command;
import com.commands.DeadlineCommand;
import com.commands.EventCommand;
import com.commands.FindCommand;
import com.commands.ListCommand;
import com.commands.MarkCommand;
import com.commands.RemoveCommand;
import com.commands.TodoCommand;
import com.commands.UnmarkCommand;
import com.exceptions.InvalidArgumentException;
import com.exceptions.InvalidCommandException;

/**
 * A command parser for the chatbot
 */
public final class Parser {


    /**
     * Given a command, return it's command type
     * @param line command
     * @return Command Type in String Format
     */
    public static String getCommandType(String line) {
        int index = line.indexOf(" ");
        String command = line;
        if (index != -1) {
            command = line.substring(0, index);
        }
        return command;
    }

    /**
     * Given an argument, extract it's description
     * @param argument argument of a command
     * @return description of the corresponding argument
     */
    public static String getDescription(String argument) {
        int index = argument.indexOf("/");
        if (index == -1) {
            return argument.trim();
        } else {
            return argument.substring(0, index).trim();
        }
    }

    /**
     * Given a command, return its argument (Excluding its command type)
     * @param command Command
     * @return Argument of a command
     * @throws InvalidArgumentException if the argument is empty
     */
    public static String getArgument(String command) throws InvalidArgumentException {
        command = command.trim();
        int index = command.indexOf(" ");
        if (index == -1) {
            throw new InvalidArgumentException("Error: Empty Argument!");
        }
        return command.substring(index + 1);
    }

    /**
     * Extract the substring after an option
     * @param argument argument of a command
     * @param target   which option to extract
     * @return the substring after the option
     * @throws InvalidArgumentException if option can't be found
     */
    public static String readOption(String argument, String target) throws InvalidArgumentException {
        int startIndex = argument.indexOf("/" + target);
        if (startIndex == -1) {
            throw new InvalidArgumentException("Error: Missing option: " + target);
        }
        String substringAfterOption = argument.substring(startIndex + target.length() + 1);
        int endIndex = substringAfterOption.indexOf("/");
        if (endIndex == -1) {
            return substringAfterOption.trim();
        } else {
            String substringBetweenOption = argument.substring(startIndex + target.length() + 1,
                    endIndex + startIndex + target.length() + 1);
            return substringBetweenOption.trim();
        }
    }

    /**
     * Takes a command a return corresponding Command object
     * @param line command
     * @return A Command object that represent the command
     * @throws InvalidCommandException if the command is not valid
     * @throws InvalidArgumentException if the argument of a command does not match its command type
     */
    public static Command parse(String line) throws InvalidCommandException, InvalidArgumentException {
        return switch (getCommandType(line)) {
        case "list" -> new ListCommand();
        case "remove" -> new RemoveCommand(getArgument(line));
        case "mark" -> new MarkCommand(getArgument(line));
        case "unmark" -> new UnmarkCommand(getArgument(line));
        case "todo" -> new TodoCommand(getArgument(line));
        case "deadline" -> new DeadlineCommand(getArgument(line));
        case "event" -> new EventCommand(getArgument(line));
        case "bye" -> new ByeCommand();
        case "find" -> new FindCommand(getArgument(line));
        default -> throw new InvalidCommandException(getCommandType(line));
        };
    }
}
