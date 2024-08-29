package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;

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
     * @throws ExecuteCommandException should not be thrown. Only present due to abstract class.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        ui.printBye();
        System.exit(0);
    }

    @Override
    public String toString() {
        return "bye command ";
    }
}
