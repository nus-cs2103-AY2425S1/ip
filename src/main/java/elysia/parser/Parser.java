package elysia.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import elysia.command.ClearCommand;
import elysia.command.Command;
import elysia.command.DeadlineCommand;
import elysia.command.DeleteCommand;
import elysia.command.ElysiaCommand;
import elysia.command.EventCommand;
import elysia.command.ExitCommand;
import elysia.command.FindCommand;
import elysia.command.MarkCommand;
import elysia.command.PrintListCommand;
import elysia.command.ToDoCommand;
import elysia.command.UnmarkCommand;
import elysia.exception.EmptyDescriptionException;
import elysia.exception.InvalidDateFormatException;
import elysia.exception.InvalidDateTimeFormatException;
import elysia.exception.InvalidInputCommandException;
import elysia.exception.OutOfValidRangeException;
import elysia.exception.UnknownCommandException;

/**
 * Parses the user's commands into respective commands.
 */
public class Parser {
    private Command command;

    public Command parseCommand(String input, int size)
            throws EmptyDescriptionException, InvalidDateFormatException, UnknownCommandException,
            InvalidDateTimeFormatException, InvalidInputCommandException, OutOfValidRangeException {
        String[] str = input.split(" ");
        String token = str[0].toLowerCase();

        switch (token) {
        case "elysia":
            parseElysiaCommand();
            break;
        case "list":
            parsePrintListCommand();
            break;
        case "todo":
            parseTodo(input);
            break;
        case "deadline":
            parseDeadLine(input);
            break;
        case "event":
            parseEvent(input);
            break;
        case "mark":
            parseMarkEvent(input, size);
            break;
        case "unmark":
            parseUnmarkEvent(input, size);
            break;
        case "delete":
            parseDeleteCommand(input, size);
            break;
        case "find":
            parseFindCommand(input);
            break;
        case "bye":
            parseExitCommand();
            break;
        case "clear":
            parseClearCommand();
            break;
        default:
            throw new UnknownCommandException();
        }

        return command;
    }

    private void parseClearCommand() {
        command = new ClearCommand();
    }

    private void parseElysiaCommand() {
        command = new ElysiaCommand();
    }

    private void parseExitCommand() {
        command = new ExitCommand();
    }

    private void parseFindCommand(String input) {
        this.command = new FindCommand(input.substring(5).trim());
    }

    private void parsePrintListCommand() {
        this.command = new PrintListCommand();
    }

    private void parseTodo(String str) throws EmptyDescriptionException {

        String input = str.substring(4).trim();

        checkEmptyDescription("todo", input);

        this.command = new ToDoCommand(input);
    }

    private void parseDeadLine(String str)
            throws EmptyDescriptionException, InvalidDateFormatException, InvalidInputCommandException {
        String trimmed = str.substring(8).trim();
        String[] inputArray = trimmed.split("/by ");
        String description = inputArray[0].trim();

        checkEmptyDescription("deadline", trimmed);
        checkValidInputFormat(inputArray, 2);

        LocalDate date = DateParser.parseDate(inputArray[1]);


        this.command = new DeadlineCommand(description, date);
    }

    private void parseEvent(String str)
            throws EmptyDescriptionException, InvalidDateFormatException, InvalidDateTimeFormatException,
            InvalidInputCommandException {

        String trimmed = str.substring(5).trim();

        checkEmptyDescription("event", trimmed);

        String[] inputArray = trimmed.split("/from|/to");
        checkValidInputFormat(inputArray, 3);

        String description = inputArray[0].trim();

        String[] dateTimeArray = inputArray[1].trim().split("\\\\");

        if (dateTimeArray.length != 2) {
            throw new InvalidDateTimeFormatException("\\\\");
        }

        LocalDate date = DateParser.parseDate(dateTimeArray[0].trim());
        LocalTime time1 = TimeParser.parseTime(dateTimeArray[1].trim());
        LocalTime time2 = TimeParser.parseTime(inputArray[2].trim());

        LocalDateTime startTime;
        LocalDateTime endTime;

        startTime = LocalDateTime.of(date, time1);
        endTime = LocalDateTime.of(date, time2);

        this.command = new EventCommand(description, startTime, endTime);

    }

    private void parseMarkEvent(String input, int size) throws OutOfValidRangeException {
        int index = getValidIndex(input, "mark");

        checkValidIndex(index, size);

        command = new MarkCommand(index);
    }

    private void parseUnmarkEvent(String input, int size) throws OutOfValidRangeException {
        int index = getValidIndex(input, "unmark");
        checkValidIndex(index, size);
        command = new UnmarkCommand(index);
    }

    private void parseDeleteCommand(String input, int size) throws OutOfValidRangeException {
        int index = getValidIndex(input, "delete");
        checkValidIndex(index, size);
        command = new DeleteCommand(index);
    }

    private void checkEmptyDescription(String eventType, String input) throws EmptyDescriptionException {
        if (input.isEmpty()) {
            throw new EmptyDescriptionException(eventType);
        }
    }

    private void checkValidInputFormat(String[] arr, int num) throws InvalidInputCommandException {
        if (arr.length != num) {
            throw new InvalidInputCommandException();
        }
    }

    private int getValidIndex(String input, String taskType) {
        int result = -1;
        try {
            switch (taskType) {
            case "mark":
                result = Integer.parseInt(input.substring(4).trim()) - 1;
                break;
            case "unmark":
                result = Integer.parseInt(input.substring(6).trim()) - 1;
                break;
            case "delete":
                result = Integer.parseInt(input.substring(6).trim()) - 1;
                break;
            default:
                result = -1;
                break;
            }
        } catch (Exception e) {
            // leave it blank to throw invalid number error
        }

        return result;
    }

    private void checkValidIndex(int num, int size) throws OutOfValidRangeException {
        if (num < 0 || num >= size) {
            throw new OutOfValidRangeException();
        }
    }
}
