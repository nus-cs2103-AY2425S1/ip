package monique.parser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import monique.command.AddCommand;
import monique.command.ByeCommand;
import monique.command.Command;
import monique.command.DeleteCommand;
import monique.command.FindCommand;
import monique.command.GuideCommand;
import monique.command.ListCommand;
import monique.command.MarkCommand;
import monique.command.UnknownCommand;
import monique.command.UnmarkCommand;
import monique.exception.IllegalDateFormatException;
import monique.exception.ParseException;
import monique.exception.UnknownCommandException;
import monique.task.Deadline;
import monique.task.Event;
import monique.task.Task;
import monique.task.ToDo;

/**
 * The <code>Parser</code> class processes user input and returns the corresponding <code>Command</code> object.
 * It handles various commands and task types, parsing the input to create appropriate command objects.
 */
public class Parser {
    public static final String NUMBER_FORMAT_ERROR_MESSAGE = "you have tried to use an invalid number";
    public static final String MISSING_ARGUMENT_ERROR_MESSAGE = "you have not provided any arguments";
    public static final String MISSING_DESCRIPTION_ERROR_MESSAGE = "you have not provided any description";
    public static final String MISSING_SEARCH_KEYS_ERROR_MESSAGE = "you have not provided any search keys";
    public static final String INVALID_DEADLINE_FORMAT_ERROR_MESSAGE = "invalid format for Deadline command";
    public static final String INVALID_EVENT_FORMAT_ERROR_MESSAGE = "invalid format for Event command";
    public static final String UNEXPECTED_VALUE_ERROR_MESSAGE = "Unexpected value: ";
    private static final Set<String> commands = Set.of("list", "mark", "unmark", "bye", "/commands", "delete", "find");
    private static final Set<String> taskTypes = Set.of("todo", "deadline", "event");
    private static final int INDEX_OFFSET = 1;
    /**
     * Parses the given command string and returns the corresponding <code>Command</code> object.
     * The method identifies the command type and creates the appropriate command object with the provided parameters.
     *
     * @param fullCommand The full command string to be parsed.
     * @return The <code>Command</code> object corresponding to the parsed command.
     */
    public static Command parse(String fullCommand) {
        String firstWord = fullCommand.split(" ")[0];
        boolean hasSecondWord = fullCommand.split(" ").length > 1;
        Command command = null;

        if (commands.contains(firstWord)) {
            switch (firstWord) {
            case "bye": {
                command = new ByeCommand();
                break;
            }
            case "list": {
                command = new ListCommand();
                break;
            }
            case "mark": {
                try {
                    if (!hasSecondWord) {
                        throw new ParseException();
                    }
                    int taskNum = Integer.parseInt(fullCommand.split("mark ")[1]) - INDEX_OFFSET;
                    command = new MarkCommand(taskNum);
                    break;
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException());
                    break;
                } catch (NumberFormatException nfe) {
                    command = new UnknownCommand(new ParseException(NUMBER_FORMAT_ERROR_MESSAGE));
                }
                break;
            }
            case "unmark": {
                try {
                    if (!hasSecondWord) {
                        throw new ParseException();
                    }
                    int taskNum = Integer.parseInt(fullCommand.split("unmark ")[1]) - INDEX_OFFSET;
                    command = new UnmarkCommand(taskNum);
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(MISSING_ARGUMENT_ERROR_MESSAGE));
                    break;
                } catch (NumberFormatException nfe) {
                    command = new UnknownCommand(new ParseException(NUMBER_FORMAT_ERROR_MESSAGE));
                }
                break;
            }
            case "/commands": {
                command = new GuideCommand();
                break;
            }
            case "delete": {
                try {
                    if (!hasSecondWord) {
                        throw new ParseException();
                    }
                    int taskNum = Integer.parseInt(fullCommand.split("delete ")[1]) - INDEX_OFFSET;
                    command = new DeleteCommand(taskNum);
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(MISSING_ARGUMENT_ERROR_MESSAGE));
                    break;
                } catch (NumberFormatException nfe) {
                    command = new UnknownCommand(new ParseException(NUMBER_FORMAT_ERROR_MESSAGE));
                }
                break;
            }
            case "find": {
                try {
                    if (!hasSecondWord) {
                        throw new ParseException();
                    }
                    String[] searchKeys = fullCommand.split("find ")[1].split(" ");
                    command = new FindCommand(searchKeys);
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(MISSING_SEARCH_KEYS_ERROR_MESSAGE));
                }
            }
            break;
            default:
                command = new UnknownCommand(new UnknownCommandException(UNEXPECTED_VALUE_ERROR_MESSAGE + firstWord));
            }
        } else if (taskTypes.contains(firstWord)) {
            //add to taskList
            switch (firstWord) {
            case "todo": {
                try {
                    String[] words = fullCommand.split(" ");
                    if (words.length <= 1) {
                        throw new ParseException();
                    }
                    assert words.length >= 1;
                    String description = String.join(" ", Arrays.copyOfRange(words, 1, words.length));
                    Task taskToAdd = new ToDo(description);
                    command = new AddCommand(taskToAdd);
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(MISSING_DESCRIPTION_ERROR_MESSAGE));
                }
                break;
            }
            case "deadline": {
                try {
                    String[] parts = fullCommand.split("/by");
                    if (parts.length <= 1) {
                        throw new ParseException();
                    }
                    String byString = parts[1].trim();
                    LocalDateTime by = DateParser.getDateTimeString(byString);
                    String[] commandAndDescription = parts[0].trim().split(" ", 2);
                    String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                    Task taskToAdd = new Deadline(description, false, by, DateParser.hasTime(byString));
                    command = new AddCommand(taskToAdd);
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(INVALID_DEADLINE_FORMAT_ERROR_MESSAGE));
                    break;
                } catch (IllegalDateFormatException idee) {
                    command = new UnknownCommand(new IllegalDateFormatException());
                }
                break;
            }
            case "event": {
                try {
                    String[] fromSplit = fullCommand.split("/from");
                    if (fromSplit.length != 2) {
                        throw new ParseException();
                    }
                    String[] toSplit = fromSplit[1].split("/to");
                    if (toSplit.length != 2) {
                        throw new ParseException();
                    }
                    String[] commandAndDescription = fromSplit[0].trim().split(" ", 2);
                    String description = commandAndDescription.length > 1 ? commandAndDescription[1] : "";
                    String fromDateString = toSplit[0].trim();
                    LocalDateTime fromDate = DateParser.getDateTimeString(fromDateString);
                    String toDateString = toSplit[1].trim();
                    LocalDateTime toDate = DateParser.getDateTimeString(toDateString);
                    Task taskToAdd = new Event(description, false, fromDate, toDate,
                            DateParser.hasTime(fromDateString));
                    command = new AddCommand(taskToAdd);
                    break;
                } catch (ParseException pe) {
                    command = new UnknownCommand(new ParseException(INVALID_EVENT_FORMAT_ERROR_MESSAGE));
                } catch (IllegalDateFormatException idee) {
                    command = new UnknownCommand(new IllegalDateFormatException());
                }
            }
            break;
            default:
                command = new UnknownCommand(new UnknownCommandException(UNEXPECTED_VALUE_ERROR_MESSAGE + firstWord));
            }
        }
        return command != null ? command : new UnknownCommand();
    }
}

