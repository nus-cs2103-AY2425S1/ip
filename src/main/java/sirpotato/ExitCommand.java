package sirpotato;

import java.io.IOException;

/**
 * Saves all the data when the user types 'bye'
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeToFile(tasks.getList());
        return ui.displayByeMessage();
    }

}
