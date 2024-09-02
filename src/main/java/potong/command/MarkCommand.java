package potong.command;

import potong.exceptions.PotongException;
import potong.Storage;
import potong.TaskList;
import potong.Ui;

public class MarkCommand extends Command {

    private boolean toMark;
    private int index;
    public MarkCommand(String command, boolean toMark) {
        super(command);
        this.toMark = toMark;
        this.index = Integer.valueOf(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws PotongException {
        if (toMark) {
            return tasks.mark(this.index);
        } else {
            return tasks.unmark(this.index);
        }
    }
}
