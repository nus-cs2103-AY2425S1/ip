package lexi.parser;

import lexi.command.*;
import lexi.exception.LexiException;
import lexi.task.Deadline;
import lexi.task.Event;
import lexi.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    public static Command parse(String response) throws LexiException {
        String[] parts = response.split(" ");
        try {
            Commands command = Commands.valueOf(parts[0].toUpperCase());
            switch (command) {
            case MARK:
            case UNMARK:
                return handleMark(parts);
            case TODO:
                return handleTodo(response);
            case DEADLINE:
                return handleDeadline(response);
            case EVENT:
                return handleEvent(response);
            case DELETE:
                return handleDelete(parts);
            case LIST:
                return listTasks();
            case BYE:
                return handleBye();
            default:
                throw new LexiException("Unrecognized command: " + parts[0]);
            }
        } catch (IllegalArgumentException e) {
            throw new LexiException("Please enter one of the following commands:\n" + Commands.printCommands());
        }
    }

    private static ByeCommand handleBye() {
        return new ByeCommand();
    }
    private static DeleteCommand handleDelete(String[] parts) throws LexiException {
        if(parts.length != 2) {
            throw new LexiException("Please key in the command in this format " +
                    "\"delete <task number>\"\n");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new DeleteCommand(taskNumber);
        } catch(NumberFormatException e) {
            throw new LexiException("Please enter a valid task number as follows:\n" +
                    "\"delete <task number>\"\n");
        }
    }

    private static AddCommand handleEvent(String response) throws LexiException {
        String[] parts = response.split(" /from ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"event <task> /from <date and time> /to <date and time>\"\n"
                + "\"<date> in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        // If only command "event" is present
        if(parts[0].equals(response)) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(6);
        if(parts.length < 2) {
            throw new LexiException(errorMessage);
        }
        String[] range = parts[1].split(" /to ");
        // No "to" command entered
        if(parts[1].equals(range[0])) {
            throw new LexiException(errorMessage);
        }
        String start  = range[0];
        String end = range[1];
        try {
            LocalDateTime from = LocalDateTime.parse(start, Parser.inputFormatter);
            LocalDateTime to = LocalDateTime.parse(end, Parser.inputFormatter);
            return new AddCommand(new Event(taskName, from, to));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
                    + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    private static AddCommand handleDeadline(String response) throws LexiException {
        String[] parts = response.split(" /by ");
        String errorMessage = "Please key in the command in this format\n"
                + "\"deadline <task> /by <date>\"\n"
                + "\"date and time in format DD/MM/YYYY HHmm like 13/01/2002 1700\"\n";
        if (parts[0].equals(response) || parts.length != 2) {
            throw new LexiException(errorMessage);
        }
        String taskName = parts[0].substring(9);
        String dateTime = parts[1];
        try {
            LocalDateTime by = LocalDateTime.parse(dateTime, Parser.inputFormatter);
            return new AddCommand(new Deadline(taskName, by));
        } catch (DateTimeParseException e) {
            throw new LexiException("You have entered the date/time in the incorrect format\n"
                    + "Please follow this format: DD/MM/YYYY HHmm like 13/01/2002 1700");
        }
    }

    private static AddCommand handleTodo(String response) throws LexiException{
        String errorMessage = "Sorry! The description of a todo cannot be empty\n" +
                "Please write in this format \"todo <description>\"\n";
        if(response.length() < 6) {
            throw new LexiException(errorMessage);
        }

        String taskName = response.substring(5);

        if(taskName.isBlank()) {
            throw new LexiException(errorMessage);
        }
        return new AddCommand((new Todo(taskName)));
    }

    private static MarkCommand handleMark(String[] parts) throws LexiException {

        if(parts.length != 2 || parts[1].isEmpty() || parts[1].isBlank()) {
            throw new LexiException("Please enter your command in this format\n" +
                    "\"mark <number>\"");
        }
        int taskNumber =  Integer.parseInt(parts[1]) - 1;
        if(parts[0].equals("unmark")) {
            return new MarkCommand(taskNumber, false);
        } else {
            return new MarkCommand(taskNumber, true);
        }
    }

    public static ListCommand listTasks() {
        return new ListCommand();
    }
}
