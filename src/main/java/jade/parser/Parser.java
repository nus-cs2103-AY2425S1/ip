package jade.parser;

import static jade.ui.Ui.INDENT;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

import jade.command.AddCommand;
import jade.command.Command;
import jade.command.DeleteCommand;
import jade.command.ExitCommand;
import jade.command.FindCommand;
import jade.command.GreetCommand;
import jade.command.ListCommand;
import jade.command.MarkCommand;
import jade.exception.JadeException;
import jade.task.Deadline;
import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;
import jade.task.TaskType;
import jade.task.Todo;
import jade.ui.Ui;

/**
 * Handles the parsing of user commands and executes the appropriate actions.
 */
public class Parser {
    private static final String INVALID_TASK_MESSAGE = "Please specify the type of task: todo, deadline, or event.";
    private static final String TIME_FORMAT_MESSAGE = "Please use yyyy-MM-dd HHmm format for time.\n"
            + INDENT + "  eg. 2024-12-25 2130";

    /**
     * Parses a user command from the GUI and returns the appropriate command object to be executed.
     *
     * @param command     The user input command to parse.
     * @param taskManager The task manager that handles the task operations.
     * @return The Command object corresponding to the user input.
     * @throws JadeException If the command is unrecognised or improperly formatted.
     */
    public Command parse(String command, TaskManager taskManager) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";
        assert taskManager != null : "TaskManager should not be null";

        String normalisedCommand = command.trim().toLowerCase();
        if (normalisedCommand.equals("bye")) {
            return new ExitCommand();
        } else if (normalisedCommand.equals("list")) {
            return new ListCommand(taskManager);
        } else if (normalisedCommand.startsWith("mark")) {
            return new MarkCommand(taskManager, normalisedCommand, true);
        } else if (normalisedCommand.startsWith("unmark")) {
            return new MarkCommand(taskManager, normalisedCommand, false);
        } else if (isTaskCommand(normalisedCommand)) {
            return new AddCommand(taskManager, this, normalisedCommand);
        } else if (normalisedCommand.startsWith("delete")) {
            return new DeleteCommand(taskManager, normalisedCommand);
        } else if (normalisedCommand.startsWith("find")) {
            return new FindCommand(taskManager, normalisedCommand);
        } else {
            throw new JadeException(INVALID_TASK_MESSAGE);
        }
    }

    /**
     * Parses user input commands from the text-based UI and executes the corresponding actions.
     *
     * @param sc          The Scanner object for reading user input.
     * @param taskManager The task manager that handles the task operations.
     */
    public void parse(Scanner sc, TaskManager taskManager) {
        assert taskManager != null : "TaskManager should not be null";

        System.out.println(new GreetCommand().run());
        String command = sc.nextLine().trim().toLowerCase();
        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    System.out.println(new ListCommand(taskManager).run());
                } else if (command.startsWith("mark")) {
                    System.out.println(new MarkCommand(taskManager, command, true).run());
                } else if (command.startsWith("unmark")) {
                    System.out.println(new MarkCommand(taskManager, command, false).run());
                } else if (isTaskCommand(command)) {
                    System.out.println(new AddCommand(taskManager, this, command).run());
                } else if (command.startsWith("delete")) {
                    System.out.println(new DeleteCommand(taskManager, command).run());
                } else if (command.startsWith("find")) {
                    System.out.println(new FindCommand(taskManager, command).run());
                } else {
                    throw new JadeException(INVALID_TASK_MESSAGE);
                }
            } catch (JadeException e) {
                System.out.println(Ui.formatTextUiMessage(e.getMessage()));
            }
            command = sc.nextLine().trim().toLowerCase();
        }
        System.out.println(new ExitCommand().run());
    }

    /**
     * Checks if the given command is a valid task command (todo, deadline, or event).
     *
     * @param command The user input command to check.
     * @return True if the command is a valid task command, false otherwise.
     */
    private boolean isTaskCommand(String command) {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        try {
            TaskType.valueOf(command.split(" ")[0].toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Parses a user command to create a task object (Todo, Deadline, or Event).
     *
     * @param command The user input command to parse.
     * @return The Task object created based on the user command.
     * @throws JadeException If the command format is incorrect or required details are missing.
     */
    public Task parseTaskCommand(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        if (command.startsWith("todo")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command);
        }
        return null;
    }

    /**
     * Parses a todo command to create a Todo task object.
     *
     * @param command The user input command for the todo task.
     * @return The Todo task object created based on the user command.
     * @throws JadeException If the command format is incorrect or the date format is invalid.
     */
    private Task parseTodoCommand(String command) throws JadeException {
        String commandBody = command.substring(4).trim();
        if (commandBody.isEmpty()) {
            throw new JadeException("The todo task cannot be empty!");
        }
        return new Todo(commandBody);
    }

    /**
     * Parses a deadline command to create a Deadline task object.
     *
     * @param command The user input command for the deadline task.
     * @return The Deadline task object created based on the user command.
     * @throws JadeException If the command format is incorrect or the date format is invalid.
     */
    private Task parseDeadlineCommand(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        String commandBody = command.substring(8).trim();
        if (commandBody.isEmpty()) {
            throw new JadeException("The deadline task cannot be empty!");
        }

        String[] parts = commandBody.split(" /by ", 2);
        assert parts.length > 0 : "Command split by '/by' should produce at least one part";

        if (parts.length < 2) {
            throw new JadeException("Please provide a deadline in the format:\n"
                    + INDENT + "  deadline <task> /by <time>");
        }

        try {
            String description = parts[0];
            String by = parts[1];
            return new Deadline(description, by);
        } catch (DateTimeParseException e) {
            throw new JadeException(TIME_FORMAT_MESSAGE);
        }
    }

    /**
     * Parses an event command to create an Event task object.
     *
     * @param command The user input command for the event task.
     * @return The Event task object created based on the user command.
     * @throws JadeException If the command format is incorrect or the date format is invalid.
     */
    private Task parseEventCommand(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        String commandBody = command.substring(5).trim();
        if (commandBody.isEmpty()) {
            throw new JadeException("The event task cannot be empty!");
        }

        String[] parts = commandBody.split(" /from ", 2);
        assert parts.length > 0 : "Command split by '/from' should produce at least one part";

        if (parts.length < 2) {
            throw new JadeException("Please provide an event in the format:\n"
                    + INDENT + "  event <task> /from <time>");
        }

        String[] timeParts = parts[1].split(" /to ", 2);
        assert timeParts.length > 0 : "Time parts split by '/to' should produce at least one part";

        if (timeParts.length < 2) {
            throw new JadeException("Please provide an end time in the format:\n"
                    + INDENT + "  event <task> /from <start time> /to <end time>");
        }

        try {
            String description = parts[0];
            String from = timeParts[0];
            String to = timeParts[1];
            return new Event(description, from, to);
        } catch (DateTimeParseException e) {
            throw new JadeException(TIME_FORMAT_MESSAGE);
        }
    }
}
