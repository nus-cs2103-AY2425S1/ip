package main;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.ByeCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.SortCommand;
import command.UnmarkCommand;
import exception.CommandFoundButInvalidException;
import exception.CommandNotFoundException;
import exception.EmptyStringException;

/**
 * Parses user input commands and performs corresponding actions on tasks
 * Handles command recognition, validation, and execution, interacting with
 * {@code TaskList}, {@code Storage}, and {@code Ui} components
 */
public class Parser {
    private String command;
    private String remainder;
    private TaskList allTasks;
    private String description;
    private Storage storage;
    private Ui ui;
    /**
     * Constructs a {@code Parser} instance which processes a given input String and performs
     * an action depending on the first word (command)
     *
     * @param input the user input string containing the command and remaining arguments
     * @param allTasks the {@code TaskList} instance where Tasks are stored and managed
     * @param s the {@code Storage} instance used for saving that state of tasks
     * @param ui the {@code Ui} instance for user interface
     * @throws EmptyStringException if the input String is empty
     * @throws CommandNotFoundException if the command is not recognized
     * @throws CommandFoundButInvalidException if the command is recognized but the remaining string
     *         are of invalid syntax
     */
    public Parser(String input, TaskList allTasks, Storage s, Ui ui) throws EmptyStringException {
        this.allTasks = allTasks;
        this.ui = ui;
        this.storage = s;
        if (input.isEmpty()) {
            throw new EmptyStringException();
        }
        if (input.split(" ", 2).length == 1) {
            this.command = input.split(" ", 2)[0].trim();
            this.remainder = "";
        }
        if (input.split(" ", 2).length == 2) {
            this.command = input.split(" ", 2)[0].trim();
            this.remainder = input.split(" ", 2)[1].trim();
        }
    }

    /**
     * Returns a string of the command performed. Otherwise, it throws an exception.
     *
     * @return a String that corresponds to the success message of the command executed
     * @throws CommandNotFoundException if the Command is not recognized
     * @throws CommandFoundButInvalidException if the Command is recognized but syntax is invalid
     */
    public String run() throws CommandNotFoundException, CommandFoundButInvalidException {
        Commands cmd = Commands.fromString(command);
        switch(cmd) {
        case TODO:
            return new AddTodoCommand(remainder).execute(allTasks, ui, storage);
        case DEADLINE:
            return new AddDeadlineCommand(remainder).execute(allTasks, ui, storage);
        case EVENT:
            return new AddEventCommand(remainder).execute(allTasks, ui, storage);
        case DELETE:
            return new DeleteCommand(remainder).execute(allTasks, ui, storage);
        case LIST:
            return new ListCommand(remainder).execute(allTasks, ui, storage);
        case MARK:
            return new MarkCommand(remainder).execute(allTasks, ui, storage);
        case UNMARK:
            return new UnmarkCommand(remainder).execute(allTasks, ui, storage);
        case FIND:
            return new FindCommand(remainder).execute(allTasks, ui, storage);
        case BYE:
            return new ByeCommand().execute(allTasks, ui, storage);
        case SORT:
            return new SortCommand(remainder).execute(allTasks, ui, storage);
        default:
            throw new CommandNotFoundException(command);
        }
    }
}
