package friendlybot;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import friendlybot.command.AddCommand;
import friendlybot.command.BadCommand;
import friendlybot.command.Command;
import friendlybot.command.DateCommand;
import friendlybot.command.DeleteCommand;
import friendlybot.command.ExitCommand;
import friendlybot.command.FindCommand;
import friendlybot.command.HelpCommand;
import friendlybot.command.ListCommand;
import friendlybot.command.MarkCommand;

/**
 * Parser deals with making sense of the user's commands.
 */
public class Parser {
    /**
     * Takes in a full command input from the user and returns an executable
     * Command.
     *
     * @param fullCommand The raw input from the user.
     * @return A Command that can be executed.
     */
    public static Command parse(String fullCommand) {
        String[] parts = fullCommand.trim().split(" ", 2);
        String command = parts[0];
        String arguments = parts.length > 1 ? parts[1] : "";

        return switch (command) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> parseMarkCommand(arguments, true);
        case "unmark" -> parseMarkCommand(arguments, false);
        case "delete" -> parseDeleteCommand(arguments);
        case "todo" -> parseTodoCommand(arguments);
        case "deadline" -> parseDeadlineCommand(arguments);
        case "event" -> parseEventCommand(arguments);
        case "date" -> parseDateCommand(arguments);
        case "find" -> parseFindCommand(arguments);
        case "help" -> parseHelpCommand(arguments);
        default -> {
            Ui.print("OOPS!! I'm sorry, that's not a command :-(");
            yield new BadCommand();
        }
        };
    }

    private static Command parseMarkCommand(String arguments, boolean isMark) {
        try {
            int taskNumber = Integer.parseInt(arguments);
            return new MarkCommand(isMark, taskNumber);
        } catch (NumberFormatException e) {
            Ui.print("Please input a valid task index!");
            return new BadCommand("Please input a valid task index!");
        }
    }

    private static Command parseDeleteCommand(String arguments) {
        try {
            int taskNumber = Integer.parseInt(arguments);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            Ui.print("Please input a valid task index!");
            return new BadCommand("Please input a valid task index!");
        }
    }

    private static Command parseTodoCommand(String arguments) {
        if (arguments.isEmpty()) {
            Ui.print("Please follow this format: todo {task_description}");
            return new BadCommand("Please follow this format:\ntodo {task_description}");
        }
        return new AddCommand("todo", arguments);
    }

    private static Command parseDeadlineCommand(String arguments) {
        // Check for exactly one "/by"
        if (arguments.indexOf("/by") != arguments.lastIndexOf("/by")) {
            Ui.print("Please follow this format: deadline {task_description} /by {date}");
            Ui.print("There should be exactly one '/by' in the input.");
            return new BadCommand("""
                    Please follow this format:
                    deadline {task_description} /by {date}
                    There should be exactly one '/by' in the input.""");
        }
        try {
            String[] descriptions = arguments.split(" /by ");
            LocalDate date;
            try {
                date = LocalDate.parse(descriptions[1]);
            } catch (DateTimeParseException e) {
                Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                return new BadCommand("Please enter a valid date! (YYYY-MM-DD)");
            }
            String taskDescription = descriptions[0];
            return new AddCommand("deadline", taskDescription, date);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.print("Please follow this format: deadline {task_description} /by {date}");
            Ui.print("Date must be in YYYY-MM-DD format!");
            return new BadCommand("""
                    Please follow this format:
                    deadline {task_description} /by {date}
                    Date must be in YYYY-MM-DD format!""");
        }
    }

    private static Command parseEventCommand(String arguments) {
        // Check for exactly one "/from" and one "/to"
        if (arguments.indexOf("/from") != arguments.lastIndexOf("/from")
                || arguments.indexOf("/to") != arguments.lastIndexOf("/to")) {
            Ui.print("Please follow this format: event {task_description} /from {date} /to {date}");
            Ui.print("There should be exactly one '/from' and one '/to' in the input.");
            return new BadCommand("""
                    Please follow this format:
                    event {task_description} /from {date} /to {date}
                    There should be exactly one '/from' and one '/to' in the input.""");
        }

        // Regular expression to match the format "description /from date /to date"
        String regex = "^(.*) /from (\\d{4}-\\d{2}-\\d{2}) /to (\\d{4}-\\d{2}-\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(arguments);

        if (!matcher.matches()) {
            Ui.print("Please follow this format: event {task_description} /from {date} /to {date}");
            Ui.print("Date must be in YYYY-MM-DD format!");
            return new BadCommand("""
                    Please follow this format:
                    event {task_description} /from {date} /to {date}
                    Date must be in YYYY-MM-DD format!""");
        }

        try {
            String taskDescription = matcher.group(1);
            LocalDate from = LocalDate.parse(matcher.group(2));
            LocalDate to = LocalDate.parse(matcher.group(3));
            return new AddCommand("event", taskDescription, from, to);
        } catch (DateTimeParseException e) {
            Ui.print("Please enter a valid date! (YYYY-MM-DD)");
            return new BadCommand("Please enter a valid date! (YYYY-MM-DD)");
        }
    }

    private static Command parseDateCommand(String arguments) {
        try {
            LocalDate date = LocalDate.parse(arguments);
            return new DateCommand(date);
        } catch (DateTimeParseException e) {
            Ui.print("Please enter a valid date! (YYYY-MM-DD)");
            return new BadCommand("Please enter a valid date! (YYYY-MM-DD)");
        }
    }

    private static Command parseFindCommand(String arguments) {
        if (arguments.isEmpty()) {
            Ui.print("Please enter a keyword!");
            return new BadCommand("Please enter a keyword!");
        }
        return new FindCommand(arguments);
    }

    private static Command parseHelpCommand(String arguments) {
        if (arguments.isEmpty()) {
            return new HelpCommand();
        }
        return new HelpCommand(arguments);
    }
}
