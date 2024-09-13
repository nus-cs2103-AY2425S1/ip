package main.java.angel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles parsing of user commands.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the input command and its parameters.
     *
     * @param input The full user input.
     * @return A Command object containing the command type and its parameters.
     * @throws InvalidCommandException If the command is not valid.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        String[] parts = input.split(" ", 2);
        String command = parts[0];

        switch (command) {
        case "bye":
            return new Command(command, null);
        case "list":
            return new Command(command, null);
        case "find":
            return parseFindCommand(parts);
        case "todo":
            return parseTodoCommand(parts);
        case "deadline":
            return parseDeadlineCommand(parts);
        case "event":
            return parseEventCommand(parts);
        case "mark":
        case "unmark":
        case "delete":
            return parseIndexCommand(parts, command);
        default:
            throw new InvalidCommandException("Unknown command: " + command
                    + ". Please enter a valid command (e.g., list, todo, deadline, event, mark, unmark, delete, bye, find).");
        }
    }

    /**
     * Parses the command for searching tasks by a keyword.
     *
     * @param parts The parts of the user input split by space.
     * @return A Command object for the find task.
     * @throws InvalidCommandException If the find keyword is missing.
     */
    private static Command parseFindCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("The find command requires a keyword. "
                    + "Please specify a keyword after 'find'. Example: 'find book'.");
        }
        return new Command("find", parts[1].trim());
    }

    /**
     * Parses the command for creating a todo task.
     *
     * @param parts The parts of the user input split by space.
     * @return A Command object for the todo task.
     * @throws InvalidCommandException If the todo description is empty.
     */
    private static Command parseTodoCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("The description of a todo cannot be empty. Please specify a task after 'todo'.");
        }
        return new Command("todo", parts[1].trim());
    }

    /**
     * Parses the command for creating a deadline task.
     *
     * @param parts The parts of the user input split by space.
     * @return A Command object for the deadline task.
     * @throws InvalidCommandException If the deadline description or date/time is missing or incorrectly formatted.
     */
    private static Command parseDeadlineCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new InvalidCommandException(
                    "The description of a deadline task must include a '/by' followed by the due date/time. "
                    + "Example: 'deadline return book /by 2019-10-10 1800'.");
        }
        String[] deadlineDetails = parts[1].split(" /by ");
        LocalDateTime deadlineDate = parseDate(deadlineDetails[1]);
        return new Command("deadline", new DeadlineDetail(deadlineDetails[0], deadlineDate));
    }

    /**
     * Parses the command for creating an event task.
     *
     * @param parts The parts of the user input split by space.
     * @return A Command object for the event task.
     * @throws InvalidCommandException If the event description or date/time is missing or incorrectly formatted.
     */
    private static Command parseEventCommand(String[] parts) throws InvalidCommandException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new InvalidCommandException(
                    "The description of an event must include '/from' and '/to' followed by the start and end times. "
                    + "Example: 'event project meeting /from 2019-10-10 1800 /to 2019-10-10 2000'.");
        }
        String[] eventDetails = parts[1].split(" /from ");
        String[] times = eventDetails[1].split(" /to ");
        LocalDateTime fromDate = parseDate(times[0]);
        LocalDateTime toDate = parseDate(times[1]);
        return new Command("event", new EventDetail(eventDetails[0], fromDate, toDate));
    }

    /**
     * Parses the command for operations requiring a task index (e.g., mark, unmark, delete).
     *
     * @param parts The parts of the user input split by space.
     * @param command The command type (e.g., mark, unmark, delete).
     * @return A Command object containing the command type and task index.
     * @throws InvalidCommandException If the task index is missing or not a valid number.
     */
    private static Command parseIndexCommand(String[] parts, String command) throws InvalidCommandException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new InvalidCommandException("Please specify the task number to " + command + ".");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            return new Command(command, taskIndex);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task number for '"
                    + command + "'. Please enter a valid number after '" + command + "'.");
        }
    }

    /**
     * Parses a date/time string into a LocalDateTime object.
     *
     * @param dateTimeString The date/time string in 'yyyy-MM-dd HHmm' format.
     * @return A LocalDateTime object representing the parsed date/time.
     * @throws InvalidCommandException If the date/time format is invalid.
     */
    private static LocalDateTime parseDate(String dateTimeString) throws InvalidCommandException {
        try {
            return LocalDateTime.parse(dateTimeString, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new InvalidCommandException("Invalid date/time format. Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Represents a command parsed from user input.
     */
    public static class Command {
        private String type;
        private Object details;

        /**
         * Constructs a Command object.
         *
         * @param type The type of the command.
         * @param details The details associated with the command.
         */
        public Command(String type, Object details) {
            this.type = type;
            this.details = details;
        }

        /**
         * Gets the type of the command.
         *
         * @return The command type.
         */
        public String getType() {
            return type;
        }

        /**
         * Gets the details of the command.
         *
         * @return The command details.
         */
        public Object getDetails() {
            return details;
        }
    }

    /**
     * Represents the details of a deadline task.
     */
    public static class DeadlineDetail {
        private String description;
        private LocalDateTime deadline;

        /**
         * Constructs a DeadlineDetail object.
         *
         * @param description The description of the deadline task.
         * @param deadline The due date/time of the deadline task.
         */
        public DeadlineDetail(String description, LocalDateTime deadline) {
            this.description = description;
            this.deadline = deadline;
        }

        /**
         * Gets the description of the deadline task.
         *
         * @return The task description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the due date/time of the deadline task.
         *
         * @return The due date/time.
         */
        public LocalDateTime getDeadline() {
            return deadline;
        }
    }

    /**
     * Represents the details of an event task.
     */
    public static class EventDetail {
        private String description;
        private LocalDateTime from;
        private LocalDateTime to;

        /**
         * Constructs an EventDetail object.
         *
         * @param description The description of the event task.
         * @param from The start date/time of the event task.
         * @param to The end date/time of the event task.
         */
        public EventDetail(String description, LocalDateTime from, LocalDateTime to) {
            this.description = description;
            this.from = from;
            this.to = to;
        }

        /**
         * Gets the description of the event task.
         *
         * @return The task description.
         */
        public String getDescription() {
            return description;
        }

        /**
         * Gets the start date/time of the event task.
         *
         * @return The start date/time.
         */
        public LocalDateTime getFrom() {
            return from;
        }

        /**
         * Gets the end date/time of the event task.
         *
         * @return The end date/time.
         */
        public LocalDateTime getTo() {
            return to;
        }
    }
}
