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

    private static final String MARK_TASK_STRING = "mark";
    private static final String UNMARK_TASK_STRING = "unmark";
    private static final String DELETE_TASK_STRING = "delete";
    private static final String ADD_TODO_TASK_STRING = "add todo";
    private static final String ADD_DEADLINE_TASK_STRING = "add deadline";
    private static final String ADD_EVENT_TASK_STRING = "add event";
    private static final String LIST_TASK_STRING = "list";
    private static final String EXIT_STRING = "bye";
    private static final String FIND_TASK_STRING = "find";
    private static final String REMIND_STRING = "remind";
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
    public Command parse(String userInput, JunoUi junoUi, FileManager fileManager, TaskManager taskManager)
            throws TaskManagerException {

        if (userInput.equalsIgnoreCase(EXIT_STRING)) {
            return new ExitCommand(junoUi);
        } else if (userInput.equalsIgnoreCase(LIST_TASK_STRING)) {
            return new ListCommand(taskManager);
        } else if (userInput.isEmpty()) {
            throw new TaskManagerException(junoUi.invalidUserInput(),
                    TaskManagerException.ErrorType.EMPTY_INPUT);
        } else if (userInput.startsWith(MARK_TASK_STRING)) {
            return new MarkCommand(userInput, taskManager, fileManager, true);
        } else if (userInput.startsWith(UNMARK_TASK_STRING)) {
            return new MarkCommand(userInput, taskManager, fileManager, false);
        } else if (userInput.startsWith(DELETE_TASK_STRING)) {
            return new DeleteCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(ADD_TODO_TASK_STRING)) {
            return new AddTodoCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(ADD_DEADLINE_TASK_STRING)) {
            return new AddDeadlineCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(ADD_EVENT_TASK_STRING)) {
            return new AddEventCommand(userInput, taskManager, fileManager);
        } else if (userInput.startsWith(FIND_TASK_STRING)) {
            return new FindCommand(userInput, taskManager);
        } else if (userInput.equalsIgnoreCase(REMIND_STRING)) {
            return new RemindCommand(taskManager);
        } else {
            throw new TaskManagerException(junoUi.invalidFunctionInput(),
                    TaskManagerException.ErrorType.INVALID_FUNCTION);
        }
    }
}
