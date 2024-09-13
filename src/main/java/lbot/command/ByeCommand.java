package lbot.command;

import lbot.exception.ExecuteCommandException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;

/**
 * This class ends the chatbot when executed.
 */
public class ByeCommand extends Command {
    /**
     * Empty constructor for ByeCommand.
     */
    public ByeCommand() {
    }

    /**
     * Exits the program.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @return ui method containing response.
     * @throws ExecuteCommandException should not be thrown. Only present due to abstract class.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        return ui.sayBye();
    }

    @Override
    public String toString() {
        return "bye command ";
    }
}
