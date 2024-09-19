package echoa.command;

import echoa.exception.*;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;

import java.io.IOException;

/**
 * Command is an abstract class which encapsulates Commands.
 * It has subclasses:
 * HiCommand, ByeCommand, MarkCommand, Unmarkcommand, DeleteCommand, UpdateCommand, ListCommand, FindCommand,
 * ToDoCommand, DeadlineCommand, EventCommand.
 */

public abstract class Command {
    Ui ui;
    Parser parser;
    TaskList taskList;
    Storage storage;

    public Command(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.parser = parser;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Executes the command.
     *
     * @param line input line given by user.
     * @throws EchoaException is thrown when there is an error with the input line.
     * @throws IOException is thrown when there is error with the input and output.
     */
    public abstract void execute(String line) throws EchoaException, IOException;

    public abstract String getMessage();
}
