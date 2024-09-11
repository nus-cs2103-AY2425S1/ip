package lbot.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lbot.command.ByeCommand;
import lbot.command.Command;
import lbot.command.DeadlineCommand;
import lbot.command.DeleteCommand;
import lbot.command.EventCommand;
import lbot.command.FindCommand;
import lbot.command.ListCommand;
import lbot.command.MarkCommand;
import lbot.command.ToDoCommand;
import lbot.exception.InvalidCommandException;
import lbot.exception.ParseCommandException;

/**
 * This class parses the given user input and returns the respective command object.
 */
public class Parser {
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses given user input.
     * DateTime parsing is carried out here too.
     *
     * @param input user input for their task/command.
     * @return Command to be executed.
     * @throws InvalidCommandException thrown when given command keyword is not supported.
     * @throws ParseCommandException   thrown when user input is not of the correct format.
     */
    public static Command parse(String input) throws InvalidCommandException, ParseCommandException {
        String description;
        LocalDateTime dueBy;
        LocalDateTime startDateTime;
        LocalDateTime endDateTime;
        int taskID;
        // Adapted from ChatGPT
        // regex edited and tested on regex101.com
        String regex = "^(todo|td) (.+)|^(deadline|d) (.+?) /by: (.+)|^(event|e) (.+?) "
                + "/from: (.+?) /to: (.+)|^(list|l)$|^(mark|m) (\\d+)|^(delete|del) (\\d+)|^(bye|bb)|^(find) (.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String command = input.split(" ")[0].trim().toLowerCase();
        if (matcher.find()) {
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
                System.out.println(dueBy);
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
            case "find":
                String term = matcher.group(17);
                if (term.isEmpty()) {
                    throw new ParseCommandException("Search term is empty");
                }
                return new FindCommand(term);
            case "bye":
                // fallthrough
            case "bb":
                return new ByeCommand();
            default:
                throw new InvalidCommandException("Invalid command: " + command);
            }
        }
        throw new InvalidCommandException("Invalid command: " + input);
    }

    private static LocalDateTime parseDateTime(String dateTime) throws ParseCommandException {
        if (dateTime.isEmpty()) {
            throw new ParseCommandException("Looks like you didn't add a date! How did you get here?!");
        }
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormat);
        } catch (Exception e) {
            throw new ParseCommandException("Please check your date input: " + dateTime);
        }
    }
}
