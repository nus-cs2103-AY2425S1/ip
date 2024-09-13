package tecna.command;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import tecna.command.ByeCommand;
import tecna.command.ListCommand;
import tecna.command.DeleteCommand;
import tecna.command.MarkCommand;
import tecna.command.UnmarkCommand;
import tecna.command.ToDoCommand;
import tecna.command.DeadlineCommand;
import tecna.command.EventCommand;
import tecna.command.InvalidCommand;

import tecna.task.Deadline;
import tecna.task.Event;
import tecna.task.Task;
import tecna.task.ToDo;

/**
 * Scans and processes the user's input.
 * An <code>inputIndex</code> is the index of the corresponding task if the command is <i>mark, unmark, or delete</i>.
 * An <code>inputTask</code> is the task required to be added via <i>todo, deadline, or event</i> commands.
 *
 * @author DennieDan.
 */
public class CommandScanner {
    private final Scanner SCANNER;
    private String input;
    private int inputIndex;
    private Task inputTask;
    private String keyword;

    public CommandScanner() {
        this.SCANNER = new Scanner(System.in);
    }

    public int getInputIndex() {
        return this.inputIndex;
    }

    public Task getInputTask() {
        return this.inputTask;
    }

    public String getKeyword() {
        return this.keyword;
    }

    /**
     * Returns the latest input scanned by the scanner
     */
    public String getInput() {
        return this.input;
    }

    public Command getCommand(String input) {
        CommandType commandType = readRequest(input);

        return switch (commandType) {
            case BYE -> new ByeCommand(input);
            case LIST -> new ListCommand(input);
            case MARK -> new MarkCommand(input);
            case UNMARK -> new UnmarkCommand(input);
            case DELETE -> new DeleteCommand(input);
            case FIND -> new FindCommand(input);
            case TODO -> new ToDoCommand(input);
            case DEADLINE -> new DeadlineCommand(input);
            case EVENT -> new EventCommand(input);
            default -> new InvalidCommand(input);
        };
    }

    public CommandType readRequest(String input) {
        this.input = input;
        String[] input_words = this.input.split("\\s+");

        return switch(input_words[0]) {
            case "bye" -> CommandType.BYE;
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "find" -> CommandType.FIND;
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            default -> CommandType.INVALID;
        };
    }

    /**
     * Generates the required index and stores in the <code>inputIndex</code> attribute.
     * @return <code>true</code> if the parsing process is success and <code>false</code> otherwise.
     */
    public boolean generateIndex() {
        String[] input_words = this.input.split(" ");
        try {
            this.inputIndex = Integer.parseInt(input_words[1]) - 1;
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void close() {
        this.SCANNER.close();
    }

}
