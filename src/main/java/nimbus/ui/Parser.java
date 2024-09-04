package nimbus.ui;

import nimbus.command.*;
import nimbus.exception.WrongDateTimeFormatException;

import java.io.IOException;

/**
 * Parses user input into respective commands
 */

public class Parser {
    private TaskList taskList;

    /**
     * Creates Parser object and store taskList into parser
     *
     * @param taskList tasklist object that contained arraylist of tasks
     */

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handles the user input by identifying which prompt the user has entered
     *
     * @param userInput provided by user
     * @throws IOException if file not found
     * @throws WrongDateTimeFormatException if date time format provided is wrong
     */

    public void handleInput(String userInput) throws IOException, WrongDateTimeFormatException {

        if (userInput.equals("bye")) {
            Storage.updateFile(taskList.getTaskList());
            Ui.goodbyeMessage();
        } else if (userInput.equals("list")) {
            taskList.toString();
        } else if (userInput.startsWith("check ")) {
            new CheckCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("mark ")) {
            new MarkCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("unmark ")) {
            new UnmarkCommand(userInput, taskList).execute();
        } else if (userInput.startsWith("delete ")) {
            new DeleteCommand(userInput, taskList).execute();
        } else {
            new AddCommand(userInput, taskList).execute();
        }
    }
}
