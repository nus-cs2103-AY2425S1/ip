package rasputin.command;

import rasputin.gui.Ui;
import rasputin.task.RasputinException;

/**
 * Represents an invalid command due to invalid parameters or input.
 */
public class InvalidCommand extends Command {

    private String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     *
     * @return Error message as a String for the UI.
     */
    @Override
    public String execute() {
        return Ui.printError(message);
    }


    /**
     * Always returns false.
     *
     * @return False.
     */
    @Override
    public boolean isTerminated() {
        return false;
    }
}
