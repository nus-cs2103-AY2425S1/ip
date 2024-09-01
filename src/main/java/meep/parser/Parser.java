package meep.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import meep.task.TaskList;
import meep.task.Todo;
import meep.task.Deadline;
import meep.task.Event;
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
     * {@code TaskList}, and returns {@code true} if the command is "BYE", signaling
     * the end of the program.
     *
     * @param input    The user's input string.
     * @param taskList The {@code TaskList} to perform actions on.
     * @return {@code true} if the input command is "BYE", {@code false} otherwise.
     */
    public boolean checkCommand(String input, TaskList taskList) {
        if (CommandParser.checkEqualCommand(input, Command.BYE.toString())) {
            ui.bye();
            return true;

        } else if (CommandParser.checkCommand(input, Command.MARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                ui.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                ui.invalidMarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.UNMARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                ui.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                ui.invalidUnmarkCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DELETE.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                ui.deleteTask(taskList.getTask(index), taskList.getSize());
                taskList.deleteItem(index);
            } catch (Exception e) {
                ui.invalidDeleteCommand();
            }

        } else if (CommandParser.checkCommand(input, Command.DEADLINE.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = formatDate(input.split(" /by ")[1]);
                taskList.addItem(new Deadline(description, by));
                ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.invalidDeadlineCommand();
            } catch (DateTimeParseException e) {
                ui.invalidDateFormat();
            }

        } else if (CommandParser.checkCommand(input, Command.EVENT.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /from ")[0];
                // from is between /from and /to
                String from = formatDate(input.split(" /from ")[1].split(" /to ")[0]);
                String to = formatDate(input.split(" /to ")[1]);
                taskList.addItem(new Event(description, from, to));
                ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.invalidEventCommand();
            } catch (DateTimeParseException e) {
                ui.invalidDateFormat();
            }
        } else if (CommandParser.checkCommand(input, Command.TODO.toString())) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                ui.invalidTodoCommand();
            }

        } else if (CommandParser.checkEqualCommand(input, Command.LIST.toString())) {
            ui.listTasks(taskList.getList());
        } else {
            // Any other command will be considered invalid
            ui.invalidCommand();
        }
        return false;
    }
}
