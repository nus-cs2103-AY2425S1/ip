package tecna.command;

import tecna.exception.WrongFormatException;
import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

public abstract class Command {
    protected String message;
    private boolean isSuccessful;

    public Command(String message) {
        this.message = message;
        this.isSuccessful = false;
    }

    /**
     * Executes the command.
     * Executes if it is valid
     * and alerts if it is invalid.
     *
     * @return A response to the user.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        assert taskList != null;
        assert storage != null;
        assert ui != null;
        return "";
    };

    protected void setIsSuccessful(boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public abstract Object checkCommand() throws WrongFormatException;

}
