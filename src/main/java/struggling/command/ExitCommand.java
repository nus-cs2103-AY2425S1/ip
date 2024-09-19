package struggling.command;

import java.io.IOException;

import struggling.Storage;
import struggling.TaskList;
import struggling.Ui;

/**
 * AddCommand specifies the instructions to exit the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.showGoodBye();
        storage.save(tasks.getTasksState());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
