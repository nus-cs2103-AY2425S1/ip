package juno.parser;
import juno.command.*;
import juno.manager.FileManager;
import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.ui.JunoUi;

public class CommandParser {

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
        } else {
            throw new TaskManagerException(junoUi.invalidFunctionInput(),
                    TaskManagerException.ErrorType.INVALID_FUNCTION);
        }
    }
}
