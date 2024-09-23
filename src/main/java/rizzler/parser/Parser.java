package rizzler.parser;

import java.util.Arrays;

import rizzler.RizzlerException;
import rizzler.command.ByeCommand;
import rizzler.command.Command;
import rizzler.command.DeadlineCommand;
import rizzler.command.DeleteCommand;
import rizzler.command.EventCommand;
import rizzler.command.FindCommand;
import rizzler.command.HelpCommand;
import rizzler.command.ListCommand;
import rizzler.command.MarkCommand;
import rizzler.command.NullCommand;
import rizzler.command.TodoCommand;
import rizzler.command.UnmarkCommand;

/**
 * Represents a parser that takes in user input and processes it into the appropriate command type.
 */
public class Parser {
    private static final String MISSING_ARGUMENT_RESPONSE = "sorry dear, you're missing one or more arguments.";
    private static final String WRONG_ARGUMENT_RESPONSE = "sorry dear, one or more of your arguments are wrong.";
    private static final String UNKNOWN_ERROR_RESPONSE = "dreadfully sorry darlin', but there's an unexpected error.";
    private static final String UNRECOGNISED_COMMAND_RESPONSE = " is not a recognised command.";

    private static final String ERROR_MESSAGE_JOIN_CHARACTER = "\n\n";

    private static final String DEADLINE_KEYWORD = "/by";
    private static final String EVENT_START_KEYWORD = "/from";
    private static final String EVENT_END_KEYWORD = "/to";

    /**
     * Constructs a <code>Parser</code> object that can parse user input strings.
     */
    public Parser() {
    }

    /**
     * Takes in input as a given input <code>String</code>, returning the appropriate command.
     * @param userInput <code>String</code> entered by the user.
     * @return Command of varying types depending on user input.
     */
    public Command parseInput(String userInput) {
        String trimmedUserInput = userInput.trim();
        String[] userInputArr = trimmedUserInput.split(" ");
        Command outputCommand;
        String userInputFirstWord = userInputArr[0].trim();
        outputCommand = switch (userInputFirstWord) {
            case "bye" -> new ByeCommand();
            case "help" -> new HelpCommand();
            case "list" -> new ListCommand();
            case "find" -> parseFind(userInputArr);
            case "delete" -> parseDelete(userInputArr);
            case "mark" -> parseMark(userInputArr);
            case "unmark" -> parseUnmark(userInputArr);
            case "todo" -> parseTodo(userInputArr);
            case "deadline" -> parseDeadline(userInputArr);
            case "event" -> parseEvent(userInputArr);
            default -> parseUnknown(userInput);
        };
        return outputCommand;
    }

    private String joinErrorMessages(String... values) {
        return String.join(ERROR_MESSAGE_JOIN_CHARACTER, values);
    }

    private Command parseFind(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] stringToMatchArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            String stringToMatch = String.join(" ", stringToMatchArr).trim();
            outputCommand = new FindCommand(stringToMatch);
        } catch (IndexOutOfBoundsException | RizzlerException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, FindCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseDelete(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToDelete = Integer.parseInt(userInputArr[1]);
            outputCommand = new DeleteCommand(taskToDelete);
        } catch (IndexOutOfBoundsException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, DeleteCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (NumberFormatException e) {
            String response = joinErrorMessages(WRONG_ARGUMENT_RESPONSE, DeleteCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseMark(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToMark = Integer.parseInt(userInputArr[1]);
            outputCommand = new MarkCommand(taskToMark);
        } catch (IndexOutOfBoundsException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, MarkCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (NumberFormatException e) {
            String response = joinErrorMessages(WRONG_ARGUMENT_RESPONSE, MarkCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseUnmark(String[] userInputArr) {
        Command outputCommand;
        try {
            int taskToUnmark = Integer.parseInt(userInputArr[1]);
            outputCommand = new UnmarkCommand(taskToUnmark);
        } catch (IndexOutOfBoundsException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, UnmarkCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (NumberFormatException e) {
            String response = joinErrorMessages(WRONG_ARGUMENT_RESPONSE, UnmarkCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseTodo(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] todoDescArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            String todoDesc = String.join(" ", todoDescArr).trim();
            outputCommand = new TodoCommand(todoDesc);
        } catch (RizzlerException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, TodoCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseDeadline(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] deadlineInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            String deadlineInfo = String.join(" ", deadlineInfoArr);
            String[] deadlineInfoBySplit = deadlineInfo.split(DEADLINE_KEYWORD);
            String deadlineDesc = deadlineInfoBySplit[0].trim();
            String deadlineTime = deadlineInfoBySplit[1].trim();
            outputCommand = new DeadlineCommand(deadlineDesc, deadlineTime);
        } catch (IndexOutOfBoundsException | RizzlerException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, DeadlineCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    private Command parseEvent(String[] userInputArr) {
        Command outputCommand;
        try {
            String[] eventInfoArr = Arrays.copyOfRange(userInputArr, 1, userInputArr.length);
            String eventInfo = String.join(" ", eventInfoArr);
            String[] eventInfoBySplit = eventInfo.split(EVENT_START_KEYWORD);
            String eventDesc = eventInfoBySplit[0].trim();
            String eventDuration = eventInfoBySplit[1];
            String eventStart = eventDuration.split(EVENT_END_KEYWORD)[0].trim();
            String eventEnd = eventDuration.split(EVENT_END_KEYWORD)[1].trim();
            outputCommand = new EventCommand(eventDesc, eventStart, eventEnd);
        } catch (IndexOutOfBoundsException | RizzlerException e) {
            String response = joinErrorMessages(MISSING_ARGUMENT_RESPONSE, EventCommand.COMMAND_FORMAT);
            outputCommand = new NullCommand(response);
        } catch (Exception e) {
            outputCommand = new NullCommand(UNKNOWN_ERROR_RESPONSE);
        }
        return outputCommand;
    }

    /**
     * Parses user input and returns a command indicating the input is not understood.
     * @param userInput Exact string input by the user for output
     * @return NullCommand conveying to the user that the input is not understood.
     */
    private Command parseUnknown(String userInput) {
        String modifiedUserInput = "\"" + userInput + "\"";
        String response = joinErrorMessages(WRONG_ARGUMENT_RESPONSE, modifiedUserInput + UNRECOGNISED_COMMAND_RESPONSE);
        return new NullCommand(response);
    }
}
