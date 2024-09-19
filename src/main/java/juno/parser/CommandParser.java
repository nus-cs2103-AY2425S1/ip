package juno.parser;

import juno.command.AddDeadlineCommand;
import juno.command.AddEventCommand;
import juno.command.AddTodoCommand;
import juno.command.Command;
import juno.command.DeleteCommand;
import juno.command.ExitCommand;
import juno.command.FindCommand;
import juno.command.ListCommand;
import juno.command.MarkCommand;
import juno.command.RemindCommand;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.ui.JunoUi;

/**
 * Class to parse user input to determine which command should be executed.
 * Based on the input string, it returns the appropriate command object to Juno chat bot.
 */
public class CommandParser {

    /**
     * Parses the user's input and returns the command to be executed.
     * Needs to pass in these parameters for use in creating the command.
     *
     * @param userInput The input entered by the user.
     * @param junoUi The user interface instance to interact with the user.
     * @param fileManager The FileManager instance for handling file operations.
     * @param taskManager The TaskManager instance for handling task specific operations.
     * @return The Command object corresponding to the user's input.
     * @throws TaskManagerException If the input is invalid or an error occurs while parsing.
     */
    public Command parse(
            String userInput,
            JunoUi junoUi,
            FileManager fileManager,
            TaskManager taskManager) throws TaskManagerException {

        String markTaskString = "mark";
        String unmarkTaskString = "unmark";
        String deleteTaskString = "delete";
        String addTodoTaskString = "add todo";
        String addDeadlineTaskString = "add deadline";
        String addEventTaskString = "add event";
        String listTaskString = "list";
        String exitString = "bye";
        String findString = "find";
        String remindString = "remind";

        if (userInput.equalsIgnoreCase(exitString)) {
            return new ExitCommand(junoUi);
        } else if (userInput.equalsIgnoreCase(listTaskString)) {
            return new ListCommand(taskManager);
        } else if (userInput.isEmpty()) {
            throw new TaskManagerException(junoUi.invalidUserInput(),
                    TaskManagerException.ErrorType.EMPTY_INPUT);
        } else if (userInput.startsWith(markTaskString)) {
            return new MarkCommand(userInput, taskManager, fileManager, true);
        } else if (userInput.startsWith(unmarkTaskString)) {
            return new MarkCommand(userInput, taskManager, fileManager, false);
        } else if (userInput.startsWith(deleteTaskString)) {
            return new DeleteCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(addTodoTaskString)) {
            return new AddTodoCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(addDeadlineTaskString)) {
            return new AddDeadlineCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(addEventTaskString)) {
            return new AddEventCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(findString)) {
            return new FindCommand(userInput, taskManager);
        } else if (userInput.equalsIgnoreCase(remindString)) {
            return new RemindCommand(taskManager);
        } else {
            throw new TaskManagerException(junoUi.invalidFunctionInput(),
                    TaskManagerException.ErrorType.INVALID_FUNCTION);
        }
    }
}
