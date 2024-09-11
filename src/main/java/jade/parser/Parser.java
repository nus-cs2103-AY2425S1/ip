package jade.parser;

import static jade.ui.Ui.BOT_LINE;
import static jade.ui.Ui.INDENT;
import static jade.ui.Ui.TOP_LINE;

import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import jade.command.AddCommand;
import jade.command.Command;
import jade.command.DeleteCommand;
import jade.command.ExitCommand;
import jade.command.FindCommand;
import jade.command.ListCommand;
import jade.command.MarkCommand;
import jade.exception.JadeException;
import jade.task.Deadline;
import jade.task.Event;
import jade.task.Task;
import jade.task.TaskManager;
import jade.task.TaskType;
import jade.task.Todo;

/**
 * Handles the parsing of user commands and executes the appropriate actions.
 */
public class Parser {
    /**
     * Parses a user command from the GUI and returns the appropriate command object to be executed.
     *
     * @param command     The user input command to parse.
     * @param taskManager The task manager that handles the task operations.
     * @param parser      The parser object used for additional parsing.
     * @return The Command object corresponding to the user input.
     * @throws JadeException If the command is unrecognised or improperly formatted.
     */
    public Command parse(String command, TaskManager taskManager, Parser parser) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";
        assert taskManager != null : "TaskManager should not be null";
        assert parser != null : "Parser should not be null";

        Map<String, Function<String, Command>> commandMap = new HashMap<>();
        commandMap.put("bye", cmd -> new ExitCommand());
        commandMap.put("list", cmd -> new ListCommand(taskManager));
        commandMap.put("mark", cmd -> new MarkCommand(taskManager, cmd, true));
        commandMap.put("unmark", cmd -> new MarkCommand(taskManager, cmd, false));
        commandMap.put("delete", cmd -> new DeleteCommand(taskManager, cmd));
        commandMap.put("find", cmd -> new FindCommand(taskManager, cmd));

        String commandType = command.split(" ")[0];
        Function<String, Command> commandFunction = commandMap.get(commandType);

        if (commandFunction != null) {
            return commandFunction.apply(command);
        } else if (isTaskCommand(command)) {
            return new AddCommand(taskManager, parser, command);
        } else {
            throw new JadeException("Please specify the type of task: todo, deadline, or event.");
        }
    }

    /**
     * Parses user input commands from the text-based UI and executes the corresponding actions.
     *
     * @param sc          The Scanner object for reading user input.
     * @param taskManager The task manager that handles the task operations.
     */
    public void parse(Scanner sc, TaskManager taskManager) {
        Map<String, Function<String, String>> commandMap = new HashMap<>();
        commandMap.put("list", cmd -> new ListCommand(taskManager).run());
        commandMap.put("mark", cmd -> new MarkCommand(taskManager, cmd, true).run());
        commandMap.put("unmark", cmd -> new MarkCommand(taskManager, cmd, false).run());
        commandMap.put("delete", cmd -> new DeleteCommand(taskManager, cmd).run());
        commandMap.put("find", cmd -> new FindCommand(taskManager, cmd).run());

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            try {
                String commandType = command.split(" ")[0];
                Function<String, String> commandFunction = commandMap.get(commandType);

                if (commandFunction != null) {
                    System.out.println(commandFunction.apply(command));
                } else if (isTaskCommand(command)) {
                    System.out.println(new AddCommand(taskManager, this, command).run());
                } else {
                    throw new JadeException("Please specify the type of task: todo, deadline, or event.");
                }
            } catch (JadeException e) {
                System.out.println(TOP_LINE + INDENT + e.getMessage() + BOT_LINE);
            }
            command = sc.nextLine();
        }
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
     * Throws a JadeException if the task description of the command is missing.
     *
     * @param command The user input command to parse.
     * @return The Task object created based on the user command.
     * @throws JadeException If the command format is incorrect or required details are missing.
     */
    public Task parseTaskCommand(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        if (command.startsWith("todo")) {
            if (command.substring(4).trim().isEmpty()) {
                throw new JadeException("The todo task cannot be empty!");
            }
            return new Todo(command.substring(5));
        } else if (command.startsWith("deadline")) {
            if (command.substring(8).trim().isEmpty()) {
                throw new JadeException("The deadline task cannot be empty!");
            }
            return parseDeadline(command);
        } else if (command.startsWith("event")) {
            if (command.substring(5).trim().isEmpty()) {
                throw new JadeException("The event task cannot be empty!");
            }
            return parseEvent(command);
        }
        return null;
    }

    /**
     * Parses a deadline command to create a Deadline task object.
     *
     * @param command The user input command for the deadline task.
     * @return The Deadline task object created based on the user command.
     * @throws JadeException If the command format is incorrect or the date format is invalid.
     */
    private Task parseDeadline(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        String[] parts = command.substring(9).split(" /by ", 2);
        assert parts.length > 0 : "Command split by '/by' should produce at least one part";

        if (parts.length < 2) {
            throw new JadeException("Please provide a deadline in the format:\n"
                    + INDENT + "  deadline <task> /by <time>");
        } else {
            try {
                return new Deadline(parts[0], parts[1]);
            } catch (DateTimeParseException e) {
                throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                        + INDENT + "  eg. 2024-12-25 2130");
            }
        }
    }

    /**
     * Parses an event command to create an Event task object.
     *
     * @param command The user input command for the event task.
     * @return The Event task object created based on the user command.
     * @throws JadeException If the command format is incorrect or the date format is invalid.
     */
    private Task parseEvent(String command) throws JadeException {
        assert command != null && !command.trim().isEmpty() : "Command should not be null or empty.";

        String[] parts = command.substring(6).split(" /from ", 2);
        assert parts.length > 0 : "Command split by '/from' should produce at least one part";

        if (parts.length < 2) {
            throw new JadeException("Please provide an event in the format:\n"
                    + INDENT + "  event <task> /from <time>");
        } else {
            String[] timeParts = parts[1].split(" /to ", 2);
            assert timeParts.length > 0 : "Time parts split by '/to' should produce at least one part";

            if (timeParts.length < 2) {
                throw new JadeException("Please provide an end time in the format:\n"
                        + INDENT + "  event <task> /from <start time> /to <end time>");
            } else {
                try {
                    return new Event(parts[0], timeParts[0], timeParts[1]);
                } catch (DateTimeParseException e) {
                    throw new JadeException("Please use yyyy-MM-dd HHmm format for time.\n"
                            + INDENT + "  eg. 2024-12-25 2130");
                }
            }
        }
    }
}
