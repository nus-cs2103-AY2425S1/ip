package ai;

import ai.command.AddCommand;
import ai.command.ByeCommand;
import ai.command.Command;
import ai.command.DefaultCommand;
import ai.command.DeleteCommand;
import ai.command.DueCommand;
import ai.command.FindCommand;
import ai.command.ListCommand;
import ai.command.MarkCommand;
import ai.command.UnmarkCommand;
import ai.exception.AiException;
import ai.exception.EmptyArgumentAiException;
import ai.exception.WrongFormatAiException;
import ai.task.Deadline;
import ai.task.Event;
import ai.task.ToDo;

/**
 * Parses the user input.
 */
public class Parser {
    /**
     * Checks whether given arguments string is empty. If empty, throws EmptyArgumentAiException.
     *
     * @param arguments  String containing arguments for the command.
     * @param command    String of command type.
     * @param suggestion String of suggestion for correct input.
     * @throws EmptyArgumentAiException Throws exception if arguments is empty.
     */
    private static void checkArgumentNotEmpty(String arguments, String command,
                                              String suggestion) throws EmptyArgumentAiException {
        if (arguments.length() <= 0) {
            throw new EmptyArgumentAiException(command, suggestion);
        }
    }

    private static Command parseTodoCommand(String arguments) throws EmptyArgumentAiException {
        checkArgumentNotEmpty(arguments, "todo", "todo hangout with Ai");
        return new AddCommand(new ToDo(arguments));
    }

    private static Command parseDeadlineCommand(String arguments) throws AiException {
        try {
            checkArgumentNotEmpty(arguments, "deadline",
                    "deadline date w Ai <3 /by 30/08/2001 2359");
            String[] parsedInput = arguments.split(" /by ", 2);
            return new AddCommand(new Deadline(parsedInput[0], parsedInput[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatAiException("Ohnoess, you might have forgotten /by field :(((",
                    "deadline date w Ai <3 /by 30/08/2001 2359");
        }
    }

    private static Command parseEventCommand(String arguments) throws AiException {
        try {
            String[] parsedCommand = arguments.split("/from|/to");
            String description = (parsedCommand[0].trim());
            String from = parsedCommand[1].trim();
            String to = parsedCommand[2].trim();

            checkArgumentNotEmpty(description, "event", "event birthday w Ai <3333 /from 5am /to 6pm");
            return new AddCommand(new Event(description, from, to, false));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatAiException("Ohnoess, you might have forgotten /from and /to fields :(((",
                    "event birthday w Ai <3333 /from 5am /to 6pm");
        }
    }

    private static Command parseDueCommand(String arguments) throws AiException {
        checkArgumentNotEmpty(arguments, "due", "due 2019-12-02");
        return new DueCommand(arguments);
    }

    private static Command parseMarkCommand(String arguments) throws AiException {
        checkArgumentNotEmpty(arguments, "mark", "mark 1");
        return new MarkCommand(arguments);
    }

    private static Command parseUnmarkCommand(String arguments) throws AiException {
        checkArgumentNotEmpty(arguments, "unmark", "unmark 1");
        return new UnmarkCommand(arguments);
    }

    private static Command parseFindCommand(String arguments) throws EmptyArgumentAiException {
        checkArgumentNotEmpty(arguments, "find", "find my heart <333");
        return new FindCommand(arguments);
    }

    private static Command parseDeleteCommand(String arguments) throws AiException {
        checkArgumentNotEmpty(arguments, "delete", "delete 3");
        return new DeleteCommand(arguments);
    }

    /**
     * Parses the fullCommand and initialises the correct type of command.
     *
     * @param fullCommand String that contains unparsed command.
     * @return Command subtypes that can be executed.
     * @throws AiException if formatting is incorrect.
     */
    public static Command parse(String fullCommand) throws AiException {
        String[] parsedCommand = fullCommand.split(" ", 2);
        String command = parsedCommand[0];
        String arguments = (parsedCommand.length > 1) ? parsedCommand[1] : "";

        switch (command) {
        case "list":
            return new ListCommand();
        case "unmark":
            return parseUnmarkCommand(arguments);
        case "mark":
            return parseMarkCommand(arguments);
        case "todo":
            return parseTodoCommand(arguments);
        case "deadline":
            return parseDeadlineCommand(arguments);
        case "event":
            return parseEventCommand(arguments);
        case "due":
            return parseDueCommand(arguments);
        case "find":
            return parseFindCommand(arguments);
        case "delete":
            return parseDeleteCommand(arguments);
        case "bye":
            return new ByeCommand();
        default:
            return new DefaultCommand();
        }
    }
}
