package tuesday.command;

import tuesday.task.Task;
import tuesday.util.Storage;
import tuesday.util.Ui;

/**
 * Represents a command to exit the system
 */
public class ExitCommand extends Command {
    private String responseMessage;
    /**
     * Constructor for ExitCommand
     *
     * @param command Description for the command
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Prints the bye message
     *
     * @param task The Task object
     * @param ui The UI object
     * @param storage The Storage object
     */
    @Override
    public void execute(Task task, Ui ui, Storage storage) {
        this.responseMessage = ui.showBye();
    }

    public String getString() {
        assert this.responseMessage != null : "The execute() method must be called first";
        return this.responseMessage;
    }

    /**
     * Use to exit the program
     *
     * @return true and do exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
