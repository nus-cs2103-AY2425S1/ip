package hoshi.utils;

import hoshi.command.AddCommand;
import hoshi.command.Command;
import hoshi.command.DeleteCommand;
import hoshi.command.FindCommand;
import hoshi.command.MarkCommand;
import hoshi.command.UnmarkCommand;
import hoshi.task.TaskList;
import hoshi.ui.Ui;

/**
 * Parser class for making sense of user command and calling the relevant function
 */
public class Parser {

    private static final String INPUT_ERROR_MESSAGE = "Hoshi doesn't understand, try a different input?";

    private final Storage storage = new Storage("./data/Hoshi.txt");

    /**
     * Parses all user commands into their respective methods as well as display the bye message once
     * program terminates
     *
     * @param input    the input that the user entered which is used to check which command to execute
     * @param taskList the TaskList that stores 3 types of tasks
     * @param ui       Ui that handles all user interaction
     * @return response corresponding to the function that was called depending on user input
     */
    public String parseCommand(String input, TaskList taskList, Ui ui) {

        // check if input is empty
        if (input.isEmpty()) {
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }
        String[] splitInput = input.split(" ");
        String commandInput = splitInput[0].toLowerCase();
        Command command;
        // switch case for parsing commands
        switch (commandInput) {
        case "initialize":
            return ui.initialize();
        case "bye":
            return ui.displayBye();
        case "list":
            return ui.displayTasks(taskList);
        case "mark":
            if (input.trim().length() < 5) {
                return ui.displayTaskToMark();
            }
            int markIndex = Integer.parseInt(splitInput[1]) - 1;

            // assert retrieved index is not out of bounds
            assert markIndex < taskList.size() && markIndex >= 0 : "Index is out of bounds for tasks";

            command = new MarkCommand(markIndex);
            break;
        case "unmark":
            if (input.trim().length() < 7) {
                return ui.displayTaskToMark();
            }
            int unmarkIndex = Integer.parseInt(splitInput[1]) - 1;

            // assert retrieved index is not out of bounds
            assert unmarkIndex < taskList.size() && unmarkIndex >= 0 : "Index is out of bounds for tasks";

            command = new UnmarkCommand(unmarkIndex);
            break;
        case "delete":
            if (input.trim().length() < 7) {
                return ui.displayError(INPUT_ERROR_MESSAGE);
            }
            int deleteIndex = Integer.parseInt(splitInput[1]) - 1;
            command = new DeleteCommand(deleteIndex);
            break;
        case "add":
            command = new AddCommand(splitInput);
            break;
        case "find":
            String keyword = splitInput[1];
            command = new FindCommand(keyword);
            break;
        default:
            return ui.displayError(INPUT_ERROR_MESSAGE);
        }
        return command.execute(taskList, ui, storage);
    }


}
