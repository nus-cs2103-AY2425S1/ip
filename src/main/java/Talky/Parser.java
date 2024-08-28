package Talky;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public static String commandType(String commandLine) {
        String commandType = commandLine.split(" ")[0];
        return commandType;
    }

    public static String[] commandArgs(String commandLine, String commandType) throws TalkyException {
        String args = "";
        if (commandLine.split(" ").length == 1) {
            if (!(commandType.equals("list") || commandType.equals("bye"))) {
                throw new TalkyException("Invalid Command");
            }
        } else {
            args = commandLine.split(" ", 2)[1];
        }
        switch (commandType) {
        case "list":
            break;
        case "bye":
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
            if (args.split(" ").length != 1) {
                throw new TalkyException("Follow this format: todo [name]");
            }
            return args.split(" ");
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
            if (args.length() != 1) {
                throw new TalkyException("Follow this format: delete [index]");
            }
            return args.split(" ");
        default:
            throw new TalkyException("Invalid Command");
        }
        String[] empty = {};
        return empty;
    }

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
