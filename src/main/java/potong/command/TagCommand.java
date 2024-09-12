package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.PotongException;

/**
 * Represents the command to tag a task.
 */
public class TagCommand extends Command {
    private int index;
    private String tag;
    /**
     * Initialise a tag command
     *
     * @param command The general description of any command.
     */
    public TagCommand(String command) {
        super(command);
        String[] args = command.split(" ", 2);
        index = Integer.valueOf(args[0]);
        tag = args[1];
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            return tasks.tag(this.tag, this.index);
        } catch (PotongException e) {
            throw new RuntimeException(e);
        }
    }
}
