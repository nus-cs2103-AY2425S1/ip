package parser;

import commands.*;
import exceptions.DownyException;
import exceptions.InvalidCommandException;
import exceptions.InvalidFormatException;
import exceptions.MissingArgumentException;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Parser {

    private static boolean isExit = false;

    public static boolean exitStatus() {
        return isExit;
    }
    public static Command parse(String fullCommand) throws DownyException { // Exception to be changed to specific one
        String[] parts = fullCommand.split(" ", 2);
        String command = parts[0];
        switch (command) {
            case "bye" -> {
                return new ByeCommand();
            }
            case "list" -> {
                return new ListCommand();
            }
            case "mark" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Mark command requires a task number.\n" +
                            "   Usage: mark <taskNumber> ");
                }
                return new MarkCommand(parts[1]);
            }
            case "unmark" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Unmark command requires a task number.\n" +
                            "   Usage: unmark <taskNumber> ");
                }
                return new UnmarkCommand(parts[1]);
            }
            case "delete" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Delete command requires a task number.\n" +
                            "   Usage: delete <taskNumber>");
                }
                return new DeleteCommand(parts[1]);
            }
            case "todo" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Todo command requires a task description.\n" +
                            "   todo <taskDescription>");
                }
                return new TodoCommand(parts[1]);

            }
            case "deadline" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Deadline command requires a task description " +
                            "and a due date.\n" +
                            "   deadline <taskDescription> /by <dueDate>");
                }
                String remainder = parts[1];
                String[] splitParts = remainder.split("/by", 2);
                if (splitParts.length < 2) {
                    throw new InvalidFormatException("Deadline command must follow the format: " +
                            "<task> /by <dueDate>.");
                }
                String name = splitParts[0].trim();
                String time = splitParts[1].trim();
                LocalDateTime formattedTime;
                try {
                    formattedTime = DateTimeHandler.convertToDateTime(time);
                } catch (NumberFormatException | DateTimeException e) {
                    throw new InvalidFormatException("dueDate must follow the format: " +
                            "YYYY/MM/DD HHMM");
                }
                return new DeadlineCommand(name, formattedTime);
            }
            case "event" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Event command requires a task description, " +
                            "start time, and end time.\n   " +
                            "event <taskDescription> " +
                            "/from <startTime> /to <endTime>");
                }
                String remainder = parts[1];
                String[] splitParts = remainder.split("/from", 2);
                if (splitParts.length < 2 || !splitParts[1].contains("/to")) {
                    throw new InvalidFormatException("Event command must follow the format: " +
                            "<task> /from <startTime> /to <endTime>.");
                }
                String name = splitParts[0].trim();
                String[] time = splitParts[1].split("/to", 2);
                String startTime = time[0].trim();
                String endTime = time[1].trim();
                LocalDateTime formattedStartTime;
                LocalDateTime formattedEndTime;
                try {
                    formattedStartTime = DateTimeHandler.convertToDateTime(startTime);
                    formattedEndTime = DateTimeHandler.convertToDateTime(endTime);
                } catch (NumberFormatException | DateTimeException e) {
                    throw new InvalidFormatException("startTime and endTime must follow the format: " +
                            "YYYY/MM/DD HHMM");
                }
                return new EventCommand(name, formattedStartTime, formattedEndTime);
            }
            case "help" -> {
                return new HelpCommand();
            }
            case "find" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new MissingArgumentException("Find command requires a keyword.\n" +
                            "   Usage: find <keyword> ");
                }
                return new FindCommand(parts[1]);
            }
            default -> {
                throw new InvalidCommandException();
            }
        }
    }

}
