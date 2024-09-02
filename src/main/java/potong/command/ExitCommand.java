package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        tasks.writeToStorage(storage);
        ui.sayGoodbye();
        return "";
    }

}
