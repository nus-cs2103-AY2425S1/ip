package dudu.utils;

import dudu.command.*;
import dudu.exception.InvalidFormatException;
import dudu.exception.MissingDateTimeException;
import dudu.exception.MissingDescriptionException;
import dudu.task.Deadline;
import dudu.task.Event;
import dudu.task.Task;
import dudu.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    enum CommandType {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, HELP
    }
    public static Command parse(String command) {
        String[] input = command.split(" ", 2);
        CommandType commandType = CommandType.valueOf(input[0].trim().toUpperCase());

        try {
            switch (commandType) {
                case BYE: {
                    return new CommandBye();
                }
                case LIST: {
                    return new CommandList();
                }
                case HELP: {
                    return new CommandHelp();
                }
                case TODO: {
                    String description = getContent(input);
                    Task task = new ToDo(description);
                    return new CommandTodo(task);
                }
                case DEADLINE: {
                    String content = getContent(input);
                    if (!content.matches(".*/by.*")) {
                        throw new InvalidFormatException("WHAT Please use deadline [description] /by [time]");
                    }
                    String[] details = content.split("/by", 2);
                    if (details[0].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing description after deadline");
                    }
                    if (details.length == 1 || details[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing by time");
                    }
                    LocalDate by = LocalDate.parse(details[1].trim());
                    Deadline task = new Deadline(details[0].trim(), by);
                    return new CommandDeadline(task);
                }
                case EVENT: {
                    String content = getContent(input);
                    if (!content.matches(".*/from.*/to.*")) {
                        throw new InvalidFormatException("Please use event [description] /from [time] /to [time]");
                    }
                    String[] details = content.split("/from", 2);
                    if (details[0].trim().isEmpty()) {
                        throw new MissingDescriptionException("Missing description after event");
                    }
                    String description = details[0].trim();
                    if (details.length == 1 || details[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing from/to time");
                    }
                    String[] date = details[1].split("/to", 2);
                    if (date.length <= 1 || date[0].trim().isEmpty() || date[1].trim().isEmpty()) {
                        throw new MissingDateTimeException("Missing from/to time");
                    }
                    LocalDate from = LocalDate.parse(date[0].trim());
                    LocalDate to = LocalDate.parse(date[1].trim());
                    Event task = new Event(description, from, to);
                    return new CommandEvent(task);
                }
                case MARK: {
                    int index = getIndex(input);
                    return new CommandMark(index);
                }
                case UNMARK: {
                    int index = getIndex(input);
                    return new CommandUnmark(index);
                }
                case DELETE: {
                    int index = getIndex(input);
                    return new CommandDelete(index);
                }
                default: {
                    return new CommandHelp();
                }
            }
        } catch (MissingDescriptionException e) {
            // TO BE REPLACED WITH dudu.utils.UI
            System.out.println(e);
        } catch (InvalidFormatException e) {
            System.out.println(e);
        } catch (DateTimeParseException e) {
            System.out.println(e);
        } catch (MissingDateTimeException e) {
            System.out.println(e);
        }
        return new CommandHelp();
    }

    public static String getContent(String[] input) throws MissingDescriptionException {
        if (input.length <= 1 || input[1].trim().isEmpty()) {
            throw new MissingDescriptionException("Please include a description");
        }
        return input[1].trim();
    }

    public static int getIndex(String[] input) throws MissingDescriptionException {
        if (input.length <= 1 || input[1].replaceAll("\\D+", "").isEmpty()) {
            throw new MissingDescriptionException("Please input a number");
        }
        int index = Integer.parseInt(input[1].replaceAll("\\D+", "")) - 1;
//        if (index < 0 || index >= tasks.size()) {
        if (index < 0) {
            throw new IllegalArgumentException("Please input a valid count");
        }
        return index;
    }
}
