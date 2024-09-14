package hypebot.parser;

import static hypebot.common.Messages.ERROR_SEARCH_QUERY_EMPTY;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import hypebot.command.AddCommand;
import hypebot.command.ByeCommand;
import hypebot.command.Command;
import hypebot.command.DeleteAllCommand;
import hypebot.command.DeleteCommand;
import hypebot.command.FindCommand;
import hypebot.command.GreetCommand;
import hypebot.command.HappeningCommand;
import hypebot.command.HelpCommand;
import hypebot.command.ListCommand;
import hypebot.command.MarkCommand;
import hypebot.command.UnknownCommand;
import hypebot.command.UnmarkCommand;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.ToDo;

/**
 * Represents the CommandParser which makes sense of user input read form the user interface
 * and returns the corresponding Command to be executed by HypeBot.
 *
 * @author YoungseoPark (@youngseopark05)
 */
public class CommandParser {
    private static String parseCommand(String line) {
        return line.split(" ")[0];
    }

    private static Pattern parseSearchQuery(String line) throws ParseException {
        String[] inputBeforeDates = line.split(" /")[0].split(" ");

        StringBuilder keywordsSb = new StringBuilder();
        for (int i = 1; i < inputBeforeDates.length - 1; i++) {
            keywordsSb.append(inputBeforeDates[i].toLowerCase()).append("|");
        }
        keywordsSb.append(inputBeforeDates[inputBeforeDates.length - 1]);
        String keywordsRegex = keywordsSb.toString().trim();

        if (keywordsRegex.isEmpty()) {
            throw new ParseException(ERROR_SEARCH_QUERY_EMPTY, 0);
        }

        return Pattern.compile(keywordsRegex, Pattern.CASE_INSENSITIVE);
    }

    /**
     * Takes in the line read from the user interface and returns appropriate Command to execute.
     *
     * @param fullCommand Line read from user interface, given by UiCli.readCommand().
     * @return Corresponding Command object pending execution.
     * @throws ParseException If a Task's name, Deadline's due date, Event's start or end time,
     *                        or date to search tasks for is missing.
     * @throws NumberFormatException If index of Task to mark, unmark, or delete is not a number.
     * @throws DateTimeParseException If Deadline due date, Event start/end time, or search date not properly formatted.
     * @throws IndexOutOfBoundsException If index of Task to mark, unmark, or delete too low/high.
     */
    public static Command parse(String fullCommand) throws ParseException,
            NumberFormatException, DateTimeParseException, IndexOutOfBoundsException {
        String command = parseCommand(fullCommand);

        return switch (command) {
        case "start" -> new GreetCommand();
        case "help" -> new HelpCommand();
        case "bye" -> new ByeCommand();
        case "list" -> new ListCommand();
        case "deleteall" -> new DeleteAllCommand();
        case "todo" -> {
            ToDo newTodo = TaskParser.parseToDoUi(fullCommand);
            yield new AddCommand(newTodo);
        }
        case "deadline" -> {
            Deadline newDeadline = TaskParser.parseDeadlineUi(fullCommand);
            yield new AddCommand(newDeadline);
        }
        case "event" -> {
            Event newEvent = TaskParser.parseEventUi(fullCommand);
            yield new AddCommand(newEvent);
        }
        case "find" -> {
            Pattern keywordsRegex = parseSearchQuery(fullCommand);
            yield new FindCommand(keywordsRegex);
        }
        case "mark" -> {
            int idxToMark = IndexParser.parseMarkIndex(fullCommand);
            yield new MarkCommand(idxToMark);
        }
        case "unmark" -> {
            int idxToMark = IndexParser.parseUnmarkIndex(fullCommand);
            yield new UnmarkCommand(idxToMark);
        }
        case "delete" -> {
            int idxToMark = IndexParser.parseDeleteIndex(fullCommand);
            yield new DeleteCommand(idxToMark);
        }
        case "happening" -> {
            LocalDate searchDate = DateTimeParser.parseHappeningDate(fullCommand);
            yield new HappeningCommand(searchDate);
        }
        default -> new UnknownCommand(command);
        };
    }
}
