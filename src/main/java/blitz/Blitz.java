package blitz;

import commands.Command;
import commands.ExitCommand;
import exceptions.ErrorMessageHandler;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;
import io.Parser;
import io.Ui;
import storage.Storage;
import task.TaskList;

/**
 * The Blitz class serves as the chatbot object.
 * It processes the input through various commands and maintains fields such as
 * task storage and list of tasks.
 */
public class Blitz {

    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes a new instance of Blitz with a specified storage path.
     * Loads the tasks from storage and sets up the UI and task list.
     *
     * @param storagePath The file path for storing task data.
     */
    public Blitz(String storagePath) {
        try {
            this.storage = Storage.createStorage(storagePath);
            this.taskList = new TaskList(storage);
        } catch (InvalidDateException e) {
            // All dates imported from data have the correct format
        }
    }

    /**
     * Returns the current TaskList managed by Blitz.
     *
     * @return The TaskList containing the tasks managed by the application.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Processes the user input and returns an appropriate response after executing the corresponding command.
     * If the user inputs the "bye" command, the exit message is returned.
     *
     * @param userInput The user's input string that represents a command to execute.
     * @return The response message after executing the command, or an error message if the input is invalid.
     */
    public String getResponse(String userInput) {
        assert userInput.isEmpty() : "Empty userInputs should not be handled here";
        try {
            Command userCommand = Parser.inputToCommand(userInput);
            String message = userCommand.execute(taskList);

            if (userCommand instanceof ExitCommand) {
                return Ui.getExitMessage();
            }

            return message;
        } catch (InvalidTaskException e) {
            return ErrorMessageHandler.getInvalidTaskMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ErrorMessageHandler.getNoValidIndexGivenMessage();
        }
    }
}
