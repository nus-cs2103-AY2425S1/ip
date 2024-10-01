package flychat.command;

import java.util.InputMismatchException;

import flychat.core.Parser;
import flychat.core.TaskList;
import flychat.core.Ui;

public class InvalidCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Parser parser, String inputString) 
            throws InputMismatchException {
        throw new InputMismatchException("Invalid input command");
    }
}