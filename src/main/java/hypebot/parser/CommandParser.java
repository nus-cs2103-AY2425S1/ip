package hypebot.parser;

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
import hypebot.exception.missing.MissingArgumentException;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.parser.task.UiTaskParser;
import hypebot.task.Task;

/**
 * Represents the CommandParser which makes sense of user input read form the user interface
 * and returns the corresponding Command to be executed by HypeBot.
 *
 * @author YoungseoPark (@youngseopark05)
 */
public class CommandParser extends Parser {
    private final UiTaskParser uiTaskParser = new UiTaskParser();
    private final UiDateTimeParser uiDateTimeParser = new UiDateTimeParser();
    private enum CommandType {
        START,
        HELP,
        BYE,
        LIST,
        DELETEALL,
        FIND,
        HAPPENING,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        UNKNOWN
    }

    public CommandParser() {
        super();
    }

    private String extractCommandWord(String line) {
        return line.split(" ")[0];
    }

    private CommandType parseCommand(String line) {
        String commandWord = extractCommandWord(line);
        return switch(commandWord) {
        case "start" -> CommandType.START;
        case "help" -> CommandType.HELP;
        case "bye" -> CommandType.BYE;
        case "list" -> CommandType.LIST;
        case "deleteall" -> CommandType.DELETEALL;
        case "find" -> CommandType.FIND;
        case "happening" -> CommandType.HAPPENING;
        case "todo" -> CommandType.TODO;
        case "deadline" -> CommandType.DEADLINE;
        case "event" -> CommandType.EVENT;
        case "mark" -> CommandType.MARK;
        case "unmark" -> CommandType.UNMARK;
        case "delete" -> CommandType.DELETE;
        default -> CommandType.UNKNOWN;
        };
    }

    /**
     * Takes in the line read from the user interface and returns appropriate Command to execute.
     *
     * @param fullCommand Line read from user interface, given by UiCli.readCommand().
     * @return Corresponding Command object pending execution.
     * @throws MissingArgumentException If a Task's name, Deadline's due date, Event's start or end time,
     *                        or date to search tasks for is missing.
     * @throws NumberFormatException If index of Task to mark, unmark, or delete is not a number.
     * @throws DateTimeParseException If Deadline due date, Event start/end time, or search date not properly formatted.
     * @throws IndexOutOfBoundsException If index of Task to mark, unmark, or delete too low/high.
     */
    @Override
    public Command parse(String fullCommand) throws MissingArgumentException,
            NumberFormatException, DateTimeParseException, IndexOutOfBoundsException {
        String commandWord = extractCommandWord(fullCommand);
        CommandType command = parseCommand(fullCommand);

        return switch (command) {
        case START -> new GreetCommand();
        case HELP -> new HelpCommand();
        case BYE -> new ByeCommand();
        case LIST -> new ListCommand();
        case DELETEALL -> new DeleteAllCommand();
        case TODO, DEADLINE, EVENT -> {
            Task newTask = uiTaskParser.parse(fullCommand);
            yield new AddCommand(newTask);
        }
        case FIND -> {
            FindKeywordsParser keywordsParser = new FindKeywordsParser();
            Pattern keywordsRegex = keywordsParser.parse(fullCommand);
            yield new FindCommand(keywordsRegex);
        }
        case HAPPENING -> {
            LocalDate searchDate = uiDateTimeParser.parseHappeningDate(fullCommand);
            yield new HappeningCommand(searchDate);
        }
        case MARK -> {
            int idxToMark = IndexParser.parseMarkIndex(fullCommand);
            yield new MarkCommand(idxToMark);
        }
        case UNMARK -> {
            int idxToMark = IndexParser.parseUnmarkIndex(fullCommand);
            yield new UnmarkCommand(idxToMark);
        }
        case DELETE -> {
            int idxToMark = IndexParser.parseDeleteIndex(fullCommand);
            yield new DeleteCommand(idxToMark);
        }
        case UNKNOWN -> new UnknownCommand(commandWord);
        };
    }
}
