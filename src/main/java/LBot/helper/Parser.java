package LBot.helper;

import LBot.command.Command;
import LBot.command.ByeCommand;
import LBot.command.DeleteCommand;
import LBot.command.DeadlineCommand;
import LBot.command.EventCommand;
import LBot.command.ListCommand;
import LBot.command.MarkCommand;
import LBot.command.ToDoCommand;

import LBot.exception.InvalidCommandException;
import LBot.exception.ParseCommandException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TAKES IN STRING CONTAINING WHOLE COMMAND
// SWTICH CASE WILL THEN EXECUTE THE RESPECTIVE COMMANDS
public class Parser {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    // CONVERT LOCAL DATETIME here tyvm
    public static Command parse(String input) throws InvalidCommandException, ParseCommandException {
        String description;
        LocalDateTime dueBy;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        int taskID;
        // Sponsored by ChatGPT
        // regex edited and tested on regex101.com
        // TODO: rename this pls
        String regex = "^(todo|td) (.+)|^(deadline|d) (.+?) /by: (.+)|^(event|e) (.+?) /from: (.+?) /to: (.+)|^(list|l)$|^(mark|m) (\\d+)|^(delete|del) (\\d+)|^(bye|bb)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        // NEED TO RUN MATCHER.FIND()
        String command = input.split(" ")[0].trim().toLowerCase();
        if (matcher.find()) {
            // includes BCD-Extension for Week 5
            switch (command) {
                case "todo":
                    // fallthrough
                case "td":
                    description = matcher.group(2);
                    if (description.isEmpty()) {
                        throw new ParseCommandException("Description is empty");
                    }
                    return new ToDoCommand(description);
                case "deadline":
                    // fallthrough
                case "d":
                    description = matcher.group(4);
                    if (description.isEmpty()) {
                        throw new ParseCommandException("Description is empty");
                    }
                    dueBy = parseDateTime(matcher.group(5));
                    return new DeadlineCommand(description, dueBy);
                case "event":
                    // fallthrough
                case "e":
                    description = matcher.group(7);
                    if (description.isEmpty()) {
                        throw new ParseCommandException("Description is empty");
                    }
                    startDateTime = parseDateTime(matcher.group(8));
                    endDateTime = parseDateTime(matcher.group(9));
                    return new EventCommand(description, startDateTime, endDateTime);
                case "list":
                    // fallthrough
                case "l":
                    return new ListCommand();
                case "mark":
                    // fallthrough
                case "m":
                    taskID = Integer.parseInt(matcher.group(12));
                    return new MarkCommand(taskID);
                case "delete":
                    // fallthrough
                case "del":
                    taskID = Integer.parseInt(matcher.group(14));
                    return new DeleteCommand(taskID);
                case "bye":
                    // fallthrough
                case "bb":
                    return new ByeCommand();
                default:
                    throw new InvalidCommandException("Invalid command: " + command);
            }
        }
        return null;
    }

    private static LocalDateTime parseDateTime(String dateTime) throws ParseCommandException {
        if (dateTime.isEmpty()) {
            throw new ParseCommandException("Invalid date: " + dateTime);
        }
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormat);
        } catch (Exception e) {
            throw new ParseCommandException(e.getMessage());
        }
    }
}
