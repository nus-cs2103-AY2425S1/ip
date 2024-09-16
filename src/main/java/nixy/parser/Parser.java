package nixy.parse;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import nixy.Storage;
import nixy.command.AddTaskCommand;
import nixy.command.ByeCommand;
import nixy.command.Command;
import nixy.command.CommandHistory;
import nixy.command.CommandType;
import nixy.command.DeleteCommand;
import nixy.command.FindCommand;
import nixy.command.InvalidCommand;
import nixy.command.ListCommand;
import nixy.command.MarkCommand;
import nixy.command.UndoCommand;
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
    private CommandHistory commandHistory;
    private Ui ui;

    /**
     * Constructor for Parser.
     *
     * @param storage The storage object to store tasks data.
     * @param tasks The task list object to manage tasks.
     * @param ui The user interface object to display messages.
     * @param commandHistory The command history object to manage command history.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui, CommandHistory commandHistory) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.commandHistory = commandHistory;
    }

    /**
     * Parses the user input and returns a Command object.
     *
     * @param input The user input to be parsed.
     * @return The Command object to be executed.
     */
    public Command parse(String input) {
        assert input != null : "Input should not be null";
        Task task;
        input = input.trim();
        String[] tokens = input.split(" ", 2);
        // first token is the command
        String commandString = tokens[0].trim();
        String args = tokens.length > 1 ? tokens[1].trim() : "";
        switch (tokens[0]) {
        case "bye":
            return new ByeCommand(ui);
        case "list":
            return new ListCommand(ui, tasks);
        case "mark":
            return parseMarkCommad(args, true);
        case "unmark":
            return parseMarkCommad(args, false);
        case "delete":
            return parseDeleteCommand(args);
        case "todo":
            return parseAddTodoCommand(args);
        case "deadline":
            return parseAddDeadlineCommand(args);
        case "event":
            return parseAddEventCommand(args);
        case "find":
            return parseFindCommand(args);
        case "undo":
            return new UndoCommand(ui, commandHistory.pop());
        default:
            return new InvalidCommand(ui);
        }
    }

    private MarkCommand parseMarkCommad(String args, boolean isDone) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException(
                String.format("BLAHH!!! The task number to mark as %s cannot be empty.",
                    isDone ? "done" : "undone"));
        }
        try {
            int parsedInt = Integer.parseInt(cleanedArgs);
            if (parsedInt < 1) {
                throw new NixyException(
                    String.format("BLAHH!!! The task number to mark as %s must be a positive integer.",
                        isDone ? "done" : "undone"));
            }
            return new MarkCommand(ui, tasks, storage, parsedInt, isDone);
        } catch (NumberFormatException e) {
            throw new NixyException(
                String.format("BLAHH!!! The task number to mark as %s must be an integer.",
                    isDone ? "done" : "undone"));
        }
    }

    private DeleteCommand parseDeleteCommand(String args) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException("BLAHH!!! The task number to delete cannot be empty.");
        }
        try {
            int parsedInt = Integer.parseInt(cleanedArgs);
            if (parsedInt < 1) {
                throw new NixyException("BLAHH!!! The task number to delete must be a positive integer.");
            }
            return new DeleteCommand(ui, tasks, parsedInt, storage);
        } catch (NumberFormatException e) {
            throw new NixyException("BLAHH!!! The task number to delete must be an integer.");
        }
    }

    private AddTaskCommand parseAddTodoCommand(String args) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException("BLAHH!!! The task description cannot be empty.");
        }
        Task task = new TodoTask(cleanedArgs);
        return new AddTaskCommand(ui, tasks, storage, CommandType.TODO, task);
    }

    private AddTaskCommand parseAddDeadlineCommand(String args) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException("BLAHH!!! The task description cannot be empty.");
        }
        // idx 0: task name, idx 1: deadline
        String[] deadlineTokens = cleanedArgs.split(" /by ", 2);
        if (deadlineTokens.length < 2) {
            throw new NixyException("BLAHH!!! The deadline of a deadline task must be specified.");
        }
        Task task;
        try {
            task = new DeadlineTask(deadlineTokens[0].trim(),
                LocalDate.parse(deadlineTokens[1].trim()));
        } catch (DateTimeParseException e) {
            throw new NixyException(
                "BLAHH!!! The deadline of a deadline task must be a valid date. (e.g. 2024-12-31)");
        }
        assert task != null : "Task should not be null";
        return new AddTaskCommand(ui, tasks, storage, CommandType.DEADLINE, task);
    }

    private AddTaskCommand parseAddEventCommand(String args) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException("BLAHH!!! The task description cannot be empty.");
        }
        // idx 0: task name, idx 1: event times (unparsed)
        String[] eventTokens = cleanedArgs.split(" /from ", 2);
        if (eventTokens.length < 2) {
            throw new NixyException("BLAHH!!! The start time of an event task must be specified.");
        }
        // idx 0: start time, idx 1: end time
        String[] eventTimeTokens = eventTokens[1].split(" /to ", 2);
        if (eventTimeTokens.length < 2) {
            throw new NixyException("BLAHH!!! The end time of an event task must be specified.");
        }
        Task task;
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
        assert task != null : "Task should not be null";
        return new AddTaskCommand(ui, tasks, storage, CommandType.EVENT, task);
    }

    private FindCommand parseFindCommand(String args) {
        String cleanedArgs = args.trim();
        if (cleanedArgs.isEmpty()) {
            throw new NixyException("BLAHH!!! The keyword to search for cannot be empty.");
        }
        return new FindCommand(ui, tasks, cleanedArgs);
    }
}
