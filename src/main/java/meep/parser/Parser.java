package meep.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import meep.task.Deadline;
import meep.task.Event;
import meep.task.Storage;
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
    FIND,
    HELP,
    ARCHIVE
}

/**
 * The {@code Parser} class processes user input, identifies commands, and
 * executes the corresponding actions on a {@code TaskList}. It interacts with
 * the user interface and handles various commands such as adding tasks,
 * marking tasks as done or undone, and deleting tasks.
 */
public class Parser {
    private final Ui ui;
    private final Storage storage;
    private String commandType;

    /**
     * Constructs a {@code Parser} with a specified user interface.
     */
    public Parser(Storage storage) {
        this.ui = new Ui();
        this.storage = storage;
        this.commandType = "";
    }

    /**
     * Returns the appropriate suffix (st, nd, rd, th) for a given day of the month.
     *
     * @param day The day of the month.
     * @return The suffix for the day.
     */
    private String getDayOfMonthSuffix(int day) {
        assert day >= 1 && day <= 31 : "Day should be between 1 and 31";
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
    public String formatDate(String dateTime) throws DateTimeParseException {
        // Use STRICT resolver style to ensure date validation
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/uuuu HHmm")
                .withResolverStyle(ResolverStyle.STRICT);

        LocalDateTime localDate;
        try {
            localDate = LocalDateTime.parse(dateTime, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Invalid date: " + dateTime, e.getParsedString(), e.getErrorIndex());
        }

        String dayOfMonth = String.valueOf(localDate.getDayOfMonth());
        String suffix = getDayOfMonthSuffix(localDate.getDayOfMonth());

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
        if (CommandParser.checkIllegalCharacters(input)) {
            return ui.invalidCharacter();
        } else if (CommandParser.checkCommandWithoutArgument(input, Command.BYE.toString())) {
            this.commandType = Command.BYE.toString();
            return ui.bye();

        } else if (CommandParser.checkCommandWithArgument(input, Command.MARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsDone(index);
                this.commandType = Command.MARK.toString();
                return ui.doneTask(taskList.getTask(index));
            } catch (Exception e) {
                return ui.invalidMarkCommand();
            }

        } else if (CommandParser.checkCommandWithArgument(input, Command.UNMARK.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                taskList.markAsUndone(index);
                this.commandType = Command.UNMARK.toString();
                return ui.undoneTask(taskList.getTask(index));
            } catch (Exception e) {
                return ui.invalidUnmarkCommand();
            }

        } else if (CommandParser.checkCommandWithArgument(input, Command.DELETE.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                String output = ui.deleteTask(taskList.getTask(index), taskList.getSize());
                taskList.deleteItem(index);
                this.commandType = Command.DELETE.toString();
                return output;
            } catch (Exception e) {
                return ui.invalidDeleteCommand();
            }

        } else if (CommandParser.checkCommandWithArgument(input, Command.DEADLINE.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /by ")[0];
                String by = formatDate(input.split(" /by ")[1]);
                taskList.addItem(new Deadline(description, by));
                this.commandType = Command.DEADLINE.toString();
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidDeadlineCommand();
            } catch (DateTimeParseException e) {
                return ui.invalidDateFormat();
            }

        } else if (CommandParser.checkCommandWithArgument(input, Command.EVENT.toString())) {
            try {
                // GitHub Copilot suggested the following code snippet
                String description = input.split(" ", 2)[1].split(" /from ")[0];
                // from is between /from and /to
                String from = formatDate(input.split(" /from ")[1].split(" /to ")[0]);
                String to = formatDate(input.split(" /to ")[1]);
                taskList.addItem(new Event(description, from, to));
                this.commandType = Command.EVENT.toString();
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidEventCommand();
            } catch (DateTimeParseException e) {
                return ui.invalidDateFormat();
            }
        } else if (CommandParser.checkCommandWithArgument(input, Command.TODO.toString())) {
            try {
                String description = input.split(" ", 2)[1];
                taskList.addItem(new Todo(description));
                this.commandType = Command.TODO.toString();
                return ui.addTask(taskList.getLastTask(), taskList.getSize());
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidTodoCommand();
            }

        } else if (CommandParser.checkCommandWithoutArgument(input, Command.LIST.toString())) {
            this.commandType = Command.LIST.toString();
            return ui.listTasks(taskList.getList());
        } else if (CommandParser.checkCommandWithArgument(input, Command.FIND.toString())) {
            try {
                String keyword = input.split(" ", 2)[1];
                this.commandType = Command.FIND.toString();
                return ui.findTasks(taskList.findTasks(keyword));
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.invalidFindCommand();
            }
        } else if (CommandParser.checkCommandWithoutArgument(input, Command.HELP.toString())) {
            this.commandType = Command.HELP.toString();
            return ui.help();
        } else if (CommandParser.checkCommandWithArgument(input, Command.ARCHIVE.toString())) {
            try {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                String output = ui.archiveTask(taskList.getTask(index), taskList.getSize());
                taskList.archiveItem(storage, index);
                this.commandType = Command.ARCHIVE.toString();
                return output;
            } catch (Exception e) {
                return ui.invalidArchiveCommand();
            }
        }
        // Any other command will be considered invalid
        this.commandType = "invalid";
        return ui.invalidCommand();
    }

    public String getCommandType() {
        return this.commandType.toLowerCase();
    }
}
