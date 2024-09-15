package nixy.parse;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nixy.Storage;
import nixy.command.AddTaskCommand;
import nixy.command.ByeCommand;
import nixy.command.Command;
import nixy.command.CommandType;
import nixy.command.DeleteCommand;
import nixy.command.FindCommand;
import nixy.command.InvalidCommand;
import nixy.command.ListCommand;
import nixy.command.MarkCommand;
import nixy.exceptions.NixyException;
import nixy.task.DeadlineTask;
import nixy.task.EventTask;
import nixy.task.Task;
import nixy.task.TaskList;
import nixy.task.TodoTask;
import nixy.ui.Ui;

/**
 * Parser responsible for method that parses the user input.
 * The parser will return the appropriate Command object based on the user input.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Parser.
     * @param storage The storage object to store tasks data.
     * @param tasks The task list object to manage tasks.
     * @param ui The user interface object to display messages.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Parses the user input and returns a Command object.
     * @param input The user input to be parsed.
     * @return The Command object to be executed.
     */
    public Command parse(String input) {
        assert input != null : "Input should not be null";
        Task task;
        input = input.trim();
        String[] tokens = input.split(" ", 2);
        tokens[0] = tokens[0].trim();
        switch (tokens[0]) {
        case "bye":
            return new ByeCommand(ui);
        case "list":
            return new ListCommand(ui, tasks);
        case "mark":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as done cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as done must be a positive integer.");
                }
                return new MarkCommand(ui, tasks, storage, parsedInt, true);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as done must be an integer.");
            }
        case "unmark":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to mark as undone cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to mark as undone must be a positive integer.");
                }
                return new MarkCommand(ui, tasks, storage, parsedInt, false);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to mark as undone must be an integer.");
            }
        case "delete":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task number to delete cannot be empty.");
            }
            try {
                int parsedInt = Integer.parseInt(tokens[1].trim());
                if (parsedInt < 1) {
                    throw new NixyException("BLAHH!!! The task number to delete must be a positive integer.");
                }
                return new DeleteCommand(ui, tasks, parsedInt, storage);
            } catch (NumberFormatException e) {
                throw new NixyException("BLAHH!!! The task number to delete must be an integer.");
            }
        case "todo":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            task = new TodoTask(tokens[1].trim());
            return new AddTaskCommand(ui, tasks, storage, CommandType.TODO, task);
        case "deadline":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: deadline
            String[] deadlineTokens = tokens[1].split(" /by ", 2);
            if (deadlineTokens.length < 2) {
                throw new NixyException("BLAHH!!! The deadline of a deadline task must be specified.");
            }
            try {
                task = new DeadlineTask(deadlineTokens[0].trim(),
                    LocalDate.parse(deadlineTokens[1].trim()));
            } catch (DateTimeParseException e) {
                throw new NixyException(
                    "BLAHH!!! The deadline of a deadline task must be a valid date. (e.g. 2024-12-31)");
            }
            return new AddTaskCommand(ui, tasks, storage, CommandType.DEADLINE, task);
        case "event":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The task description cannot be empty.");
            }
            // idx 0: task name, idx 1: event times (unparsed)
            String[] eventTokens = tokens[1].split(" /from ", 2);
            if (eventTokens.length < 2) {
                throw new NixyException("BLAHH!!! The start time of an event task must be specified.");
            }
            // idx 0: start time, idx 1: end time
            String[] eventTimeTokens = eventTokens[1].split(" /to ", 2);
            if (eventTimeTokens.length < 2) {
                throw new NixyException("BLAHH!!! The end time of an event task must be specified.");
            }
            try {
                task = new EventTask(
                    eventTokens[0].trim(),
                    LocalDate.parse(eventTimeTokens[0].trim()),
                    LocalDate.parse(eventTimeTokens[1].trim())
                );
            } catch (DateTimeParseException e) {
                throw new NixyException(
                    "BLAHH!!! The start and end times of an event task must be valid dates. (e.g. 2024-12-31)");
            }
            return new AddTaskCommand(ui, tasks, storage, CommandType.EVENT, task);
        case "find":
            if (tokens.length < 2) {
                throw new NixyException("BLAHH!!! The keyword to search for cannot be empty.");
            }
            return new FindCommand(ui, tasks, tokens[1].trim());
        default:
            return new InvalidCommand(ui);
        }
    }
}
