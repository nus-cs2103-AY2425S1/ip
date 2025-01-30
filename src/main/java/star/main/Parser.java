package star.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import star.command.*;
import star.exception.StarException;

/**
 * Deals with making sense of the user command.
 * Parses the input command to determine what actions need to be executed.
 */
public class Parser {

    /**
     * Parses the fullCommand input String and returns the corresponding Command object.
     * @param fullCommand which is the full user input String.
     * @return the matching Command object.
     * @throws StarException if the command is invalid or the input is incomplete.
     */
    static Command parse(String fullCommand) throws StarException {
        if (isExit(fullCommand)) {
            return new ExitCommand();
        }
        if (isDone(fullCommand)) {
            String[] userInput = fullCommand.split(" ");
            return new DoneCommand(Integer.parseInt(userInput[1]));
        }
        if (isUndone(fullCommand)) {
            String[] userInput = fullCommand.split(" ");
            return new UndoneCommand(Integer.parseInt(userInput[1]));
        }
        if (isFind(fullCommand)) {
            String[] userInput = fullCommand.split(" ");
            return new FindCommand(userInput[1]);
        }
        if (isList(fullCommand)) {
            return new ListCommand();
        }
        if (isDelete(fullCommand)) {
            String[] userInput = fullCommand.split(" ");
            return new DeleteCommand(Integer.parseInt(userInput[1]));
        }
        if (isTodo(fullCommand)) {
            String[] newInput = fullCommand.split(" ", 2);
            String[] userInput = validateTodo(newInput[1]);
            return parseTodo(userInput);
        }
        if (isDeadline(fullCommand)) {
            String[] userInput = validateDeadline(fullCommand);
            return parseDeadline(userInput);
        }
        if (isEvent(fullCommand)) {
            String[] userInput = validateEvent(fullCommand);
            return parseEvent(userInput);
        } else {
            throw StarException.unknownCommand();
        }
    }

    private static boolean isUndone(String input) {
        return input.startsWith("unmark");
    }

    private static boolean isDone(String input) {
        return input.startsWith("mark");
    }

    private static boolean isDelete(String input) {
        return input.startsWith("delete");
    }

    private static boolean isFind(String input) {
        return input.startsWith("find");
    }

    private static boolean isTodo(String input) {
        return input.startsWith("todo");
    }

    private static boolean isEvent(String input) {
        return input.startsWith("event");
    }

    private static boolean isDeadline(String input) {
        return input.startsWith("deadline");
    }

    private static boolean isExit(String input) {
        return Objects.equals(input, "bye");
    }

    private static boolean isList(String input) {
        return Objects.equals(input, "list");
    }

    /**
     * Parses an input String to identify if it is in a date format.
     * @param input which is the input String to be parsed.
     * @return True if the input String is in date format and False otherwise.
     */
    public static boolean isDate(String input) throws StarException {
        String[] splitInput = input.split("-");
        if (splitInput.length == 1) {
            throw new StarException("oopsie! that seems like a wrong date format :(");
        }
        boolean inputIsNotNumber = isNotNumber(splitInput[0]) || isNotNumber(splitInput[1])
                || !isNotNumber(splitInput[2]);
        return splitInput.length == 3 && !inputIsNotNumber;
    }

    /**
     * Converts an input String into a LocalDateTime object.
     * @param input which is the input String to be converted.
     * @return LocalDateTime object using the DateTimeFormatter object to convert the input String.
     */
    public static LocalDateTime parseDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        return LocalDateTime.parse(input, formatter);

    }

    private static boolean isNotNumber(String input) {
        return !input.matches("[-+]?\\d*\\.?\\d+");
    }

    private static String[] validateTodo(String input) throws StarException {
        if (input.isEmpty()) {
            throw StarException.emptyTodo();
        }
        return input.split(" /tag ");
    }

    private static String[] validateEventDeadline(String input, String replaceText,
                                                  String splitText, String type) throws StarException {
        String[] splitInput = input.replaceFirst(replaceText, "").trim().split(splitText);

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        boolean blankSplitInput = splitInput[0].isBlank() || splitInput[1].isBlank();

        if (splitInput.length < 2 || blankSplitInput) {
            if (type.equals("E")) {
                throw StarException.emptyEvent();
            } else {
                throw StarException.emptyDeadline();
            }
        }
        return splitInput;
    }

    private static String[] validateEvent(String input) throws StarException {
        String[] strArr = validateEventDeadline(input, "event", "/", "E");
        if (strArr.length < 3 && strArr[1].contains("at")) {
            strArr[1] = strArr[1].replace("at ", "");
            return strArr;
        }
        if (strArr.length < 4 && strArr[2].contains("tag") && strArr[1].contains("at")) {
            strArr[1] = strArr[1].replace("at ", "");
            strArr[2] = strArr[2].replace("tag ", "");
            return strArr;
        }
        strArr[1] = strArr[1].replace("from ", ""); // 12-05-2020 0300 /to 31-05-2020 0600
        strArr[2] = strArr[2].replace("to ", "");
        if (strArr.length == 4) {
            strArr[3] = strArr[3].replace("tag ", "");
        }
        return strArr;
    }

    private static String[] validateDeadline(String input) throws StarException {
        String[] strArr = validateEventDeadline(input, "deadline", "/", "D");
        strArr[1] = strArr[1].replace("by ", ""); // 12-05-2020 0300 /to 31-05-2020 0600
        if (strArr.length > 2) {
            strArr[2] = strArr[2].replace("tag ", "");
        }
        return strArr;
    }

    private static Command parseDeadline(String[] newInput) throws StarException {
        if (isDate(newInput[1])) {
            LocalDateTime date = parseDate(newInput[1]);
            if (newInput.length > 2) {
                return new AddDeadlineCommand(newInput[0], date, newInput[2]);
            } else {
                return new AddDeadlineCommand(newInput[0], date);
            }
        }
        if (newInput.length > 2) {
            return new AddDeadlineCommand(newInput[0], newInput[1], newInput[2]);
        } else {
            return new AddDeadlineCommand(newInput[0], newInput[1]);
        }
    }

    private static Command parseTodo(String[] arr) {
        if (arr.length == 2) {
            return new AddTodoCommand(arr[0], arr[1]);
        }
        return new AddTodoCommand(arr[0]);
    }

    private static Command parseEvent(String[] newInput) throws StarException {
        if (newInput.length < 3) {
            return new AddEventCommand(newInput[0], newInput[1]);
        }
        if (isDate(newInput[1]) && isDate(newInput[2])) {
            LocalDateTime date1 = parseDate(newInput[1]);
            LocalDateTime date2 = parseDate(newInput[2]);
            if (newInput.length > 3) {
                return new AddEventCommand(newInput[0], date1, date2, newInput[3]);
            }
            return new AddEventCommand(newInput[0], date1, date2);
        } else if (newInput[1].contains("-") && newInput[2].contains("-")) {
            throw new StarException("oopsie! that seems like the wrong date format (remember to add the time)!");
        }
        if (newInput.length > 2 && !newInput[2].contains("-")) {
            return new AddEventCommand(newInput[0], newInput[1], newInput[2]);
        }
        return new AddEventCommand(newInput[0], newInput[1]);
    }
}
