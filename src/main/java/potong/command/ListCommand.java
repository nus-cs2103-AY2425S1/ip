package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        tasks.print();
    }
}
