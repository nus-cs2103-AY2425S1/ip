package pikappi;

import pikappi.command.Command;
import pikappi.command.DeadlineCommand;
import pikappi.command.DeleteCommand;
import pikappi.command.EventCommand;
import pikappi.command.ExitCommand;
import pikappi.command.FindCommand;
import pikappi.command.ListCommand;
import pikappi.command.MarkCommand;
import pikappi.command.TodoCommand;
import pikappi.command.UnmarkCommand;
import pikappi.exception.PikappiException;

/**
 * Represents a parser that handles user input.
 */
public class Parser {
    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;
    protected boolean isExit;

    /**
     * Returns a Parser object that handles user input.
     *
     * @param storage Storage object to save and load tasks
     * @param tasks TaskList object that stores tasks
     * @param ui Ui object to interact with user
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
        this.isExit = false;
    }

    /**
     * Returns a boolean value that indicates if the user wants to exit the program.
     *
     * @return boolean value that indicates if the user wants to exit the program
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Returns a Command object that corresponds to the user input.
     *
     * @param command User input from command line
     * @return Command object that corresponds to the user input
     * @throws PikappiException If the user input is invalid
     */
    public Command parse(String command) throws PikappiException {
        switch (command.split(" ")[0].toUpperCase()) {
        case "BYE":
            isExit = true;
            return new ExitCommand();
        case "LIST":
            return new ListCommand();
        case "MARK":
            return new MarkCommand(Integer.parseInt(command.split(" ")[1]));
        case "UNMARK":
            return new UnmarkCommand(Integer.parseInt(command.split(" ")[1]));
        case "TODO":
            try {
                return new TodoCommand(command.split("todo ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "DEADLINE":
            try {
                return new DeadlineCommand(command.split("deadline ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "EVENT":
            try {
                return new EventCommand(command.split("event ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What is the task?");
            }
        case "DELETE":
            try {
                return new DeleteCommand(Integer.parseInt(command.split("delete ")[1]));
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? Which task do you want to delete?");
            }
        case "FIND":
            try {
                return new FindCommand(command.split("find ")[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new PikappiException("Pika..? What do you want to find?");
            }
        default:
            throw new PikappiException("Pika..?? I don't understand..");
        }
    }
}
