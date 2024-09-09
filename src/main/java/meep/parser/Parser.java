package meep.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.TaskList;
import meep.task.Todo;
import meep.ui.Ui;

/**
 * The {@code Command} enum represents the various commands that can be issued
 * by the user to interact with the task management system.
 */
enum Command {
    BYE,
    MARK,
    UNMARK,
    DELETE,
    DEADLINE,
    EVENT,
    TODO,
    LIST,
    FIND
}

/**
 * The {@code Parser} class processes user input, identifies commands, and
 * executes the corresponding actions on a {@code TaskList}. It interacts with
 * the user interface and handles various commands such as adding tasks,
 * marking tasks as done or undone, and deleting tasks.
 */
public class Parser {
    private final Ui ui;

    /**
     * Constructs a {@code Parser} with a specified user interface.
     */
    public Parser() {
        this.ui = new Ui();
    }

    /**
     * Returns the appropriate suffix (st, nd, rd, th) for a given day of the month.
     *
     * @param day The day of the month.
     * @return The suffix for the day.
     */
    private String getDayOfMonthSuffix(int day) {
        if (day >= 11 && day <= 13) {
            return "th";
        }
        return switch (day % 10) {
        case 1 -> "st";
        case 2 -> "nd";
        case 3 -> "rd";
        default -> "th";
        };
    }

    /**
     * Formats a given date and time string into a more readable format, adding
     * the appropriate suffix to the day of the month and converting the time
     * to a 12-hour format with AM/PM notation.
     *
     * @param dateTime The date and time string in the format {@code d/M/yyyy HHmm}.
     * @return The formatted date and time string.
     * @throws DateTimeParseException if the input string cannot be parsed.
     */
    public String formatDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime localDate = LocalDateTime.parse(dateTime, formatter);
        String dayOfMonth = String.valueOf(localDate.getDayOfMonth());
        String suffix = getDayOfMonthSuffix(localDate.getDayOfMonth());

        // The following code snippet is suggested by ChatGPT
        // Format the date and time
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern("MMMM uuuu, h:mm"));
        String amPm = localDate.format(DateTimeFormatter.ofPattern("a")).toLowerCase();

        // Combine everything
        return dayOfMonth + suffix + " of " + formattedDate + amPm;
    }

    /**
     * Processes the user's input, executes the corresponding command on the
     * {@code TaskList}, and returns ui responses
     *
     * @param input    The user's input string.
     * @param taskList The {@code TaskList} to perform actions on.
     * @return response from Ui class based on the user's input
     */
    public String checkCommand(String input, TaskList taskList) {
        if (CommandParser.checkEqualCommand(input, Command.BYE.toString())) {
            return ui.bye();

        } else if (CommandParser.checkCommand(input, Command.MARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                return ui.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                return ui.invalidMarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.UNMARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                return ui.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                return ui.invalidUnmarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DELETE.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                String output = ui.deleteTask(taskList.getTask(index), taskList.getSize());
                taskList.deleteItem(index);
                return output;
            } catch (Exception e) {
                return ui.invalidDeleteCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DEADLINE.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = formatDate(input.split(" /by ")[1]);
                taskList.addItem(new Deadline(description, by));
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidDeadlineCommand();
            } catch (DateTimeParseException e) {
                return ui.invalidDateFormat();
            }

        } else if (CommandParser.checkCommand(input, Command.EVENT.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /from ")[0];
                // from is between /from and /to
                String from = formatDate(input.split(" /from ")[1].split(" /to ")[0]);
                String to = formatDate(input.split(" /to ")[1]);
                taskList.addItem(new Event(description, from, to));
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidEventCommand();
            } catch (DateTimeParseException e) {
                return ui.invalidDateFormat();
            }
        } else if (CommandParser.checkCommand(input, Command.TODO.toString())) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidTodoCommand();
            }

        } else if (CommandParser.checkEqualCommand(input, Command.LIST.toString())) {
            return ui.listTasks(taskList.getList());
        } else if (CommandParser.checkCommand(input, Command.FIND.toString())) {
            try {
                String keyword = input.split(" ", 2)[1];
                return ui.findTasks(taskList.findTasks(keyword));
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidFindCommand();
            }
        }
        // Any other command will be considered invalid
        return ui.invalidCommand();
    }
}
