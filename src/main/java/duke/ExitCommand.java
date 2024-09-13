package duke;

import java.io.IOException;
import java.text.ParseException;

public class ExitCommand extends Command {
    ExitCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the task
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException, IOException {
        Command.exit = true;
        storage.writeData(tasks);
        return ui.show("Bye!");
    }
}
