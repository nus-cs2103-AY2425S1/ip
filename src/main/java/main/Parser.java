package main;

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
    private boolean isOver = false;
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
            allTasks.addTodo(remainder);
            storage.put(allTasks);
            return ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize());
        case DEADLINE:
            allTasks.addDeadline(remainder);
            storage.put(allTasks);
            return ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize());
        case EVENT:
            allTasks.addEvent(remainder);
            storage.put(allTasks);
            return ui.addedMessage(allTasks.getLastAdded(), allTasks.getSize());
        case DELETE:
            allTasks.delete(remainder);
            storage.put(allTasks);
            return ui.deleteMessage(this.allTasks.getLastDeleted(), this.allTasks.getSize());
        case LIST:
            return allTasks.list(remainder);
        case MARK:
            allTasks.mark(remainder);
            storage.put(allTasks);
            return ui.markedMessage(allTasks.getLastMarked());
        case UNMARK:
            allTasks.unmark(remainder);
            storage.put(allTasks);
            return ui.unmarkedMessage(allTasks.getLastUnmarked());
        case FIND:
            return ui.findMessage() + "\n" + new TaskList(allTasks.find(remainder)).list("");
        case BYE:
            this.isOver = true;
            return ui.bye();
        default:
            throw new CommandNotFoundException(command);
        }
    }
}
