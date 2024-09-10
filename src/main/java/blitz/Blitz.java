package blitz;
import commands.Command;
import commands.ExitCommand;
import exceptions.ErrorMessageHandler;
import exceptions.InvalidTaskException;
import io.Parser;
import io.Ui;
import exceptions.InvalidDateException;
import storage.Storage;
import task.TaskList;


public class Blitz {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Blitz(String storagePath) {
        try {
            this.storage = Storage.createStorage(storagePath);
            this.taskList = new TaskList(storage);
            ui = new Ui(taskList);
        } catch (InvalidDateException e) {

        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public String getResponse(String userInput) {
        try {
            Command userCommand = Parser.inputToCommand(userInput);
            String message = userCommand.execute(taskList);
            if (userCommand instanceof ExitCommand) {
                return ErrorMessageHandler.getExitMessage();
            }
            return message;
        } catch (InvalidTaskException e) {
            return ErrorMessageHandler.getInvalidTaskMessage();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ErrorMessageHandler.getNoValidIndexGivenMessage();
        }
    }
}
