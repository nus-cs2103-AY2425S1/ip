package Talky;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class to parse the inputs into format that the chatbot can understand
 */
public class Parser {
    /**
     * Returns the command type of given command.
     *
     * @param commandLine
     * @return Command Type
     */
    public static String commandType(String commandLine) {
        String commandType = commandLine.split(" ")[0];
        return commandType;
    }

    /**
     * Return the arguments of given command.
     *
     * @param commandLine
     * @param commandType
     * @return command arguments
     * @throws TalkyException
     */
    public static String[] commandArgs(String commandLine, String commandType) throws TalkyException {
        String args = "";
        if (commandLine.split(" ").length != 1) {
            args = commandLine.split(" ", 2)[1];
        }
        switch (commandType) {
        case "list":
            break;
        case "bye":
            break;
        case "help":
            break;
        case "mark":
            if (args.split(" ").length != 1) {
                throw new TalkyException("Follow this format: mark [index]");
            }
            return args.split(" ");
        case "unmark":
            if (args.split(" ").length != 1) {
                throw new TalkyException("Follow this format: unmark [index]");
            }
            return args.split(" ");
        case "todo":
            if (args.split(" ").length == 0) {
                throw new TalkyException("Follow this format: todo [name]");
            }
            return args.split("todo");
        case "deadline":
            String[] deadlineArgs = args.split(" /by ");
            if (deadlineArgs.length != 2) {
                throw new TalkyException("Follow this format: deadline [name] /by [date] [time]");
            }
            return deadlineArgs;
        case "event":
            String[] eventArgs = args.split(" /from | /to ");
            if (eventArgs.length != 3) {
                throw new TalkyException("Follow this format: event [name] /from [date] [time] /to [date] [time]");
            }
            return eventArgs;
        case "delete":
            if (args.split(" ").length != 1) {
                throw new TalkyException("Follow this format: delete [index]");
            }
            return args.split(" ");
        case "find":
            if (args.split(" ").length != 1) {
                throw new TalkyException("Follow this format: find [keyword]");
            }
            return args.split(" ");
        default:
            throw new TalkyException("Invalid Command");
        }
        String[] empty = {};
        return empty;
    }

    /**
     * Returns ArrayList of type LocalDateTime from Strings of dates.
     *
     * @param dates
     * @return ArrayList of dates of type LocalDateTime
     * @throws TalkyException
     */
    public static ArrayList<LocalDateTime> parseDate(String... dates) throws TalkyException {
        ArrayList<LocalDateTime> formattedDates = new ArrayList<>();
        for (int i = 0; i < dates.length; i++) {
            LocalDate date;
            LocalTime time;
            String[] format = dates[0].split(" ");
            try {
                date = LocalDate.parse(format[0], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                time = LocalTime.parse(format[1], DateTimeFormatter.ofPattern("HHmm"));
                LocalDateTime formatted = LocalDateTime.of(date, time);
                formattedDates.add(formatted);
            } catch (DateTimeParseException err) {
                throw new TalkyException("DateTime Format invalid, input in the format dd/MM/yyy HHmm");
            }
        }
        return formattedDates;
    }
}
