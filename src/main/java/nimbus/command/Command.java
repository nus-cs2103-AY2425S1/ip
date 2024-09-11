package nimbus.command;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.ui.TaskList;

/**
 * An abstract class for different commands to inherit from
 */
public abstract class Command {
    private String userInput;
    private TaskList taskList;

    /**
     * Creates Command Object to store userInput
     * Passes in taskList
     *
     * @param userInput
     * @param taskList
     */
    public Command(String userInput, TaskList taskList) {
        this.userInput = userInput;
        this.taskList = taskList;
    }

    public abstract String execute() throws WrongDateTimeFormatException;
}
