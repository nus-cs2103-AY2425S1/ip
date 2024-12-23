package colress.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import colress.TaskType;
import colress.command.Command;
import colress.exception.EmptyInputException;
import colress.exception.UnknownCommandException;

/**
 * An interface for parsers of the Colress chatbot.
 */
public interface Parsable {
    String COMMAND_ADD = "add";
    String COMMAND_CHECK = "check";
    String COMMAND_DATE = "date";
    String COMMAND_DELETE = "delete";
    String COMMAND_EXIT = "exit";
    String COMMAND_FIND = "find";
    String COMMAND_LIST = "list";
    String COMMAND_TOGGLE = "toggle";
    String COMMAND_UNCHECK = "uncheck";

    Command getCommand(String input) throws UnknownCommandException;
    Command parseCommand(String input) throws UnknownCommandException;
    TaskType getTaskType(String input) throws IllegalArgumentException;
    LocalDate readDate(String input) throws DateTimeParseException;
    LocalTime readTime(String input) throws DateTimeParseException;
    int[] getTaskNumber(String input) throws NumberFormatException;
    String getString(String input) throws EmptyInputException;
}
