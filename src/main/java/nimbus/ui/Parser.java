package nimbus.ui;

import java.io.IOException;

import nimbus.command.AddCommand;
import nimbus.command.CheckCommand;
import nimbus.command.DeleteCommand;
import nimbus.command.FindCommand;
import nimbus.command.MarkCommand;
import nimbus.command.UnmarkCommand;
import nimbus.exception.NimbusException;
import nimbus.exception.WrongDateTimeFormatException;

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
    public String handleInput(String userInput) throws IOException, WrongDateTimeFormatException {
        try {
            if (userInput.equals("bye")) {
                Storage.updateFile(taskList.getTaskList());
                return Ui.goodbyeMessage();
            } else if (userInput.equals("list")) {
                return taskList.toString();
            } else if (userInput.startsWith("check ")) {
                return new CheckCommand(userInput, taskList).execute();
            } else if (userInput.startsWith("mark ")) {
                return new MarkCommand(userInput, taskList).execute();
            } else if (userInput.startsWith("unmark ")) {
                return new UnmarkCommand(userInput, taskList).execute();
            } else if (userInput.startsWith("delete ")) {
                return new DeleteCommand(userInput, taskList).execute();
            } else if (userInput.startsWith("find ")) {
                return new FindCommand(userInput, taskList).execute();
            } else {
                return new AddCommand(userInput, taskList).execute();
            }
        } catch (NimbusException e) {
            return e.getMessage();
        }
    }
}
