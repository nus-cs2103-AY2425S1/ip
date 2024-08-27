package friendlybot;

import friendlybot.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parser deals with making sense of the user's commands.
 */
public class Parser {
    /**
     * Takes in a full command input from the user and returns an executable Command.
     *
     * @param fullCommand The raw input from the user.
     * @return A Command that can be executed.
     */
    public static Command parse(String fullCommand) {
        if (fullCommand.equals("bye")) {
            return new ExitCommand();
        } else if (fullCommand.equals("list")) {
            return new ListCommand();
        } else if (fullCommand.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
                return new MarkCommand(true, taskNumber);
            } catch (NumberFormatException e) {
                Ui.print("Please input a valid task index!");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
                return new MarkCommand(false, taskNumber);
            } catch (NumberFormatException e) {
                Ui.print("Please input a valid task index!");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(fullCommand.split(" ")[1]);
                return new DeleteCommand(taskNumber);
            } catch (NumberFormatException e) {
                Ui.print("Please input a valid task index!");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("todo")) {
            try {
                String taskDescription = fullCommand.split("todo ", 2)[1];
                return new AddCommand("todo", taskDescription);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.print("Please follow this format: todo {task_description}");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("deadline")) {
            try {
                String input = fullCommand.split("deadline ", 2)[1];
                String[] descriptions = input.split(" /by ");
                LocalDate date;
                try {
                    date = LocalDate.parse(descriptions[1]);
                } catch (DateTimeParseException e) {
                    Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                    return new BadCommand();
                }
                String taskDescription = descriptions[0];
                return new AddCommand("deadline", taskDescription, date);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.print("Please follow this format: deadline {task_description} /by {date}");
                Ui.print("Date must be in YYYY-MM-DD format!");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("event")) {
            try {
                String input = fullCommand.split("event ", 2)[1];
                String[] descriptions = input.split(" /from | /to ");
                LocalDate from;
                LocalDate to;
                try {
                    from = LocalDate.parse(descriptions[1]);
                    to = LocalDate.parse(descriptions[2]);
                } catch (DateTimeParseException e) {
                    Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                    return new BadCommand();
                }
                String taskDescription = descriptions[0];
                return new AddCommand("event", taskDescription, from, to);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.print("Please follow this format: event {task_description} /from {date} /to {date}");
                Ui.print("Date must be in YYYY-MM-DD format!");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("date")) {
            LocalDate date;
            try {
                date = LocalDate.parse(fullCommand.split("date ", 2)[1]);
                return new DateCommand(date);
            } catch (DateTimeParseException e) {
                Ui.print("Please enter a valid date! (YYYY-MM-DD)");
                return new BadCommand();
            }
        } else if (fullCommand.startsWith("find")) {
            try {
                String keyword = fullCommand.split("find", 2)[1];
                return new FindCommand(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.print("Please enter a keyword!");
                return new BadCommand();
            }
        } else {
            Ui.print("OOPS!! I'm sorry, that's not a command :-(");
            return new BadCommand();
        }
    }
}
