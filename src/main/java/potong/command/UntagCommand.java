package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.PotongException;

/**
 * Represents the command to tag a task.
 */
public class UntagCommand extends Command {
    private int index;
    /**
     * Initialise a tag command
     *
     * @param command The general description of any command.
     */
    public UntagCommand(String command) {
        super(command);
        index = Integer.valueOf(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            return tasks.untag(this.index);
        } catch (PotongException e) {
            throw new RuntimeException(e);
        }
    }
}
