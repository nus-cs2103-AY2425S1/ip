package sirpotato;

import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.writeToFile(tasks.getList());
        return ui.displayByeMessage();
    }

}