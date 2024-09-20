package tecna.command;

import tecna.collection.TaskList;
import tecna.storage.Storage;
import tecna.ui.Ui;

/**
 * Represents the Command of type ByeCommand (exit the app).
 *
 * @author Solution below inspired by https://github.com/Feng1231/ip.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a ByeCommand instance.
     *
     * @param message The whole command input in String.
     */
    public ByeCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        try {
            storage.save(taskList);
        } catch (java.io.IOException ioException) {
            ui.printError("I cannot access the data file " + storage.getFilePath());
        }

        return ui.printGoodbyeMsg();
    }
}
