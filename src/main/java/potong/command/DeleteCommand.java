package potong.command;

import potong.exceptions.PotongException;
import potong.Storage;
import potong.TaskList;
import potong.Ui;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String command) {
        super(command);
        this.index = Integer.valueOf(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws PotongException {
        return tasks.delete(this.index);
    }
}
