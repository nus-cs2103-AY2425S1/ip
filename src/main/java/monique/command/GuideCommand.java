package monique.command;

import monique.exception.MarkException;
import monique.storage.Storage;
import monique.tasklist.TaskList;
import monique.ui.Ui;


/**
 * Represents a command to display a guide or help text to the user.
 * This command provides information about available commands and their usage.
 */
public class GuideCommand extends Command {
    public static final String COMMAND_TYPE = "guide";
    /**
     * Constructs a <code>GuideCommand</code> instance.
     * This constructor initializes the command without any additional parameters.
     */
    public GuideCommand() {
        super(COMMAND_TYPE);
    }

    /**
     * Executes the guide command, which involves calling the ui to print the guide text to the console.
     * This provides the user with information on how to use various commands.
     *
     * @param tasks the <code>TaskList</code> (not used in this command)
     * @param ui the <code>Ui</code> instance (not used in this command)
     * @param storage the <code>Storage</code> instance (not used in this command)
     * @throws MarkException this command does not throw any exceptions
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MarkException {
        ui.printGuide();
    }
    /**
     * Returns whether this chatbot will be active after the command executes.
     * @return true since bot should remain active after the guide command
     */
    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Retrieves the response message from the execution of the GuideCommand.
     *
     * @param ui the user interface instance used to get the guide text
     * @return a string containing the guide information provided by the UI
     */
    @Override
    public String getResponse(Ui ui) {
        return ui.printGuide();
    }
}
