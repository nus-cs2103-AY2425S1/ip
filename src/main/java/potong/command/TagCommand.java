package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;
import potong.exceptions.IllegalInputPotongException;

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

    /**
     * Tags the task in the list of tasks based on the given tag.
     *
     * @param tasks List of tasks at hand.
     * @param storage Storage class for loading and saving.
     * @param ui Ui class for printing output.
     * @return String representation of tag.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            return tasks.tagTask(this.tag, this.index);
        } catch (IllegalInputPotongException e) {
            return e.getMessage();
        }
    }
}
