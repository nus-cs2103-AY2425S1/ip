package panorama;

import java.time.LocalDate;

import panorama.command.ByeCommand;
import panorama.command.Command;
import panorama.command.DeadlineCommand;
import panorama.command.DeleteCommand;
import panorama.command.EventCommand;
import panorama.command.FindCommand;
import panorama.command.HelpCommand;
import panorama.command.ListCommand;
import panorama.command.MarkCommand;
import panorama.command.TodoCommand;
import panorama.command.UnmarkCommand;
import panorama.exception.EmptyDescriptionException;
import panorama.exception.EmptyKeywordException;
import panorama.exception.IdOutOfBoundsException;
import panorama.exception.InvalidDateException;
import panorama.exception.InvalidUsageException;
import panorama.exception.NonIntegerIdException;
import panorama.exception.UnknownCommandException;

/**
 * Parses user input.
 */
public class Parser {
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input. It expects a string containing a positive integer
     * represnting a 1-indexed ID. It also expects the parsed ID, id to be between
     * 0 and taskList.size() - 1 inclusive.
     *
     * @param input The user's input string to be parsed.
     * @return An integer representing the parsed ID in 0-index.
     */
    public int parseId(String input) throws NonIntegerIdException, IdOutOfBoundsException {
        int id;
        try {
            id = Integer.parseInt(input) - 1;

            int curSize = this.taskList.numTasks();
            if (id < 0 || id >= curSize) {
                throw new IdOutOfBoundsException(curSize);
            }
        } catch (NumberFormatException e) {
            throw new NonIntegerIdException();
        }
        return id;
    }

    /**
     * Parses the user input and returns an executable command based on the input.
     *
     * @param input The user's input string to be parsed.
     * @return A {@code Command} representing the parsed command.
     */
    public Command parseCommand(String input)
            throws EmptyDescriptionException, UnknownCommandException,
                              NonIntegerIdException, IdOutOfBoundsException,
                              EmptyKeywordException, InvalidDateException,
                              InvalidUsageException {
        String stripped = input.strip();
        String[] tokens = stripped.split(" ", 2);

        String command = tokens[0];
        String rest = tokens.length > 1 ? tokens[1] : "";

        switch (command) {

        case TodoCommand.COMMAND_SHORTHAND:
        case TodoCommand.COMMAND_WORD: {
            if (rest.equals("")) {
                throw new EmptyDescriptionException();
            }
            return new TodoCommand(taskList, rest);
        }

        case DeadlineCommand.COMMAND_SHORTHAND:
        case DeadlineCommand.COMMAND_WORD: {
            if (rest.equals("")) {
                throw new EmptyDescriptionException();
            }

            String[] contentTokens = rest.split(" /by ", 2);
            if (contentTokens.length != 2) {
                throw new InvalidUsageException(Message.CommandFormat.DEADLINE);
            }

            String name = contentTokens[0];
            LocalDate date = DateParser.parse(contentTokens[1]);
            return new DeadlineCommand(taskList, name, date);
        }

        case EventCommand.COMMAND_SHORTHAND:
        case EventCommand.COMMAND_WORD: {
            if (rest.equals("")) {
                throw new EmptyDescriptionException();
            }

            String[] contentTokens = rest.split(" /from ", 2);
            if (contentTokens.length != 2) {
                throw new InvalidUsageException(Message.CommandFormat.EVENT);
            }

            String name = contentTokens[0];

            String[] dateRangeTokens = contentTokens[1].split(" /to ", 2);
            if (dateRangeTokens.length != 2) {
                throw new InvalidUsageException(Message.CommandFormat.EVENT);
            }

            LocalDate from = DateParser.parse(dateRangeTokens[0]);
            LocalDate to = DateParser.parse(dateRangeTokens[1]);
            return new EventCommand(taskList, name, from, to);
        }

        case MarkCommand.COMMAND_SHORTHAND:
        case MarkCommand.COMMAND_WORD: {
            int id = parseId(rest);
            return new MarkCommand(taskList, id);
        }

        case UnmarkCommand.COMMAND_SHORTHAND:
        case UnmarkCommand.COMMAND_WORD: {
            int id = parseId(rest);
            return new UnmarkCommand(taskList, id);
        }

        case DeleteCommand.COMMAND_SHORTHAND:
        case DeleteCommand.COMMAND_WORD: {
            int id = parseId(rest);
            return new DeleteCommand(taskList, id);
        }

        case ListCommand.COMMAND_SHORTHAND:
        case ListCommand.COMMAND_WORD: {
            return new ListCommand(taskList);
        }

        case FindCommand.COMMAND_SHORTHAND:
        case FindCommand.COMMAND_WORD: {
            if (rest.equals("")) {
                throw new EmptyKeywordException();
            }
            return new FindCommand(taskList, rest);
        }

        case ByeCommand.COMMAND_SHORTHAND:
        case ByeCommand.COMMAND_WORD: {
            return new ByeCommand();
        }

        case HelpCommand.COMMAND_SHORTHAND:
        case HelpCommand.COMMAND_WORD: {
            return new HelpCommand();
        }

        default:
            throw new UnknownCommandException();

        }
    }
}
