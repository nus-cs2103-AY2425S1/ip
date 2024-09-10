package jade.parser;

import static jade.ui.Ui.BOT_LINE;
import static jade.ui.Ui.INDENT;
import static jade.ui.Ui.TOP_LINE;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

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
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand(taskManager);
        } else if (command.startsWith("mark")) {
            return new MarkCommand(taskManager, command, true);
        } else if (command.startsWith("unmark")) {
            return new MarkCommand(taskManager, command, false);
        } else if (isTaskCommand(command)) {
            return new AddCommand(taskManager, parser, command);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(taskManager, command);
        } else if (command.startsWith("find")) {
            return new FindCommand(taskManager, command);
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
        String command = sc.nextLine();
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
        String[] parts = command.substring(9).split(" /by ", 2);
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
        String[] parts = command.substring(6).split(" /from ", 2);
        if (parts.length < 2) {
            throw new JadeException("Please provide an event in the format:\n"
                    + INDENT + "  event <task> /from <time>");
        } else {
            String[] timeParts = parts[1].split(" /to ", 2);
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
