package hypebot.parser.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import hypebot.exception.datetime.HypeBotDateTimeParseException;
import hypebot.exception.missing.MissingArgumentException;
import hypebot.parser.Parser;
import hypebot.parser.datetime.UiDateTimeParser;
import hypebot.parser.task.UiTaskParser;
import hypebot.task.Deadline;
import hypebot.task.Event;
import hypebot.task.Task;
import hypebot.tasklist.Tasklist;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code CommandParser} which makes sense of user input read by user interface
 * {@link UiGuiMainWindow} and returns the corresponding {@link Command} to be executed.
 * <p>A child of {@link Parser}.</p>
 * <p>Possesses a {@link UiTaskParser} and {@link UiDateTimeParser} to help parse {@link Task}s
 * and {@link LocalDate} or {@link LocalDateTime} entries.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class CommandParser extends Parser {
    /** Helper {@link Parser} to parse {@link Task}-related entries. */
    private final UiTaskParser uiTaskParser = new UiTaskParser();

    /** Helper {@link Parser} to parse {@link LocalDate} and {@link LocalDateTime} related entries. */
    private final UiDateTimeParser uiDateTimeParser = new UiDateTimeParser();

    /** All command types recognised. */
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

    /**
     * Creates a new {@code CommandParser}.
     */
    public CommandParser() {
        super();
    }

    /**
     * Extracts and returns the first word in the user entry,
     * recognised as the command keyword.
     *
     * @param line Full line of user input.
     */
    private String extractCommandWord(String line) {
        return line.trim().split(" ")[0].toLowerCase();
    }

    /**
     * Converts the {@link String} command keyword extracted from
     * full user input to the corresponding {@code CommandType}.
     *
     * @param line Full line of user input.
     * @return Corresponding {@code CommandType} of user input.
     */
    private CommandType parseCommand(String line) {
        String commandWord = extractCommandWord(line);
        return switch(commandWord) {
        case "s", "start" -> CommandType.START;
        case "h", "help" -> CommandType.HELP;
        case "b", "bye" -> CommandType.BYE;
        case "l", "list" -> CommandType.LIST;
        case "da", "deleteall" -> CommandType.DELETEALL;
        case "f", "find" -> CommandType.FIND;
        case "hp", "happening" -> CommandType.HAPPENING;
        case "td", "todo" -> CommandType.TODO;
        case "dl", "deadline" -> CommandType.DEADLINE;
        case "ev", "event" -> CommandType.EVENT;
        case "m", "mark" -> CommandType.MARK;
        case "u", "unmark" -> CommandType.UNMARK;
        case "d", "delete" -> CommandType.DELETE;
        default -> CommandType.UNKNOWN;
        };
    }

    /**
     * Takes in the line read from the user interface and returns appropriate {@code Command} to execute.
     *
     * @param fullCommand Line read from user interface from {@link UiGuiMainWindow}.
     * @return Corresponding {@link Command} object pending execution.
     * @throws MissingArgumentException      If a {@link Task}'s {name}, {@link Deadline}'s due date,
     *                                       {@link Event}'s start or end time,
     *                                       or date to search tasks in a {@link HappeningCommand} for is missing.
     * @throws NumberFormatException         If index of {@link Task} in {@link Tasklist} to
     *                                       mark, unmark, or delete is not a number.
     * @throws HypeBotDateTimeParseException If {@link Deadline} due date, {@link Event} start/end time, or search date
     *                                       for a {@link HappeningCommand} is not properly formatted.
     * @throws IndexOutOfBoundsException     If index of {@link Task} to mark, unmark, or delete too low/high.
     */
    @Override
    public Command parse(String fullCommand) throws MissingArgumentException,
            NumberFormatException, HypeBotDateTimeParseException, IndexOutOfBoundsException {
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
            FindQueryParser keywordsParser = new FindQueryParser();
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
