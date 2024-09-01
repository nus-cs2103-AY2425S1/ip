package nimbus.command;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.TaskList;

abstract public class Command {
    private String userInput;
    private TaskList taskList;

    public Command(String userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    public abstract void execute() throws WrongDateTimeFormatException;
}
