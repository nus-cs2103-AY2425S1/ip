package socchat;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static socchat.Socchat.exit;

/**
 * Provides utility methods for parsing and formatting date and time,
 * as well as tokenizing command strings.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Enum representing all available commands in the Socchat application.
     */
    enum Command {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, FIND
    }

    /**
     * Splits a command string into tokens based on the delimiter " /".
     *
     * @param command the command string to be tokenized
     * @return an array of strings, where each element is a token extracted from the command
     */
    public static String[] tokenizeAdd(String command) {
        String[] strToken = command.split(" /");
        return strToken;
    }

    /**
     * Parses a date string into a LocalDateTime object based on the predefined format.
     *
     * @param date the date string to be parsed
     * @return the corresponding LocalDateTime object
     * @throws SocchatException if the date string does not match the expected format
     */
    public static LocalDateTime parseDate(String date) throws SocchatException {
        try {
            return LocalDateTime.parse(date, FORMATTER);
        } catch (DateTimeException e) {
            throw new SocchatException("Please enter your dateTime as this format --- yyyy-MM-dd HH:mm");
        }

    }

    /**
     * Converts a LocalDateTime object to a string representation based on the predefined format.
     *
     * @param date the LocalDateTime object to be converted
     * @return the string representation of the date in the format "yyyy-MM-dd HH:mm"
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(FORMATTER);
    }

    /**
     * Converts the input string to a Command enum value.
     * Throws an exception if the input does not match any command.
     *
     * @param input the command string input from the user
     * @return the corresponding Command enum value
     * @throws SocchatException if the input does not match a valid command
     */
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }

    public static String[] processInput(String input) {

        int firstSpaceIndex = input.indexOf(' ');
        String[] commandTokens = new String[2];

        if (firstSpaceIndex != -1) {

            String command = input.substring(0, firstSpaceIndex);
            commandTokens[0] = command;
            String remaining = input.substring(firstSpaceIndex + 1);
            commandTokens[1] = remaining;
        } else {
            commandTokens[0] = input;
        }
        return commandTokens;
    }

    public String getResponse(String... input) {
        String[] strToken;
        String respond = "";

        try {
            String commandInput = input[0];
            String remaining;
            Command command = getCommand(commandInput);
            switch (command) {
                case BYE:
                    return exit();
                case LIST:
                    respond = taskList.list();
                    break;
                case MARK:
                    remaining = input[1];
                    String taskIndexString = remaining.trim();
                    respond = taskList.setMark(taskIndexString, true);
                    break;
                case UNMARK:
                    remaining = input[1];
                    taskIndexString = remaining.trim();
                    respond = taskList.setMark(taskIndexString, false);
                    break;
                case TODO:
                    remaining = input[1];
                    if (remaining == null ||remaining.trim().isEmpty()) {
                        throw new SocchatException("Todo cannot have empty description");
                    }
                    String str = command + " " + remaining;
                    strToken = Parser.tokenizeAdd(str);
                    respond = taskList.addTodo(strToken);
                    break;
                case DEADLINE:
                    remaining = input[1];
                    str = command + " " + remaining;
                    strToken = Parser.tokenizeAdd(str);
                    respond = taskList.addDeadline(strToken);
                    break;
                case EVENT:
                    remaining = input[1];
                    str = command + " " + remaining;
                    strToken = Parser.tokenizeAdd(str);
                    respond = taskList.addEvent(strToken);
                    break;
                case DELETE:
                    remaining = input[1];
                    taskIndexString = remaining;
                    respond = taskList.delete(taskIndexString);
                    break;
                case FIND:
                    remaining = input[1];
                    str = remaining;
                    respond = taskList.find(str);
                    break;
                default:
                    respond = "Unrecognized command. Please try again.";
                    break;
            }

        } catch (NullPointerException e) {
            respond = "Empty description";

        } catch (SocchatException e) {
            respond = e.getMessage();
        }
        return respond;
    }
}
