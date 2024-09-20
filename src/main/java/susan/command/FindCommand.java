package susan.command;

import susan.task.*;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private String[] keyword;

    public FindCommand(String[] keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SusanException {
        try {
            TaskList matchingTasks = tasks.findTasks(keyword[1]);
            return ui.showMatchingTasks(matchingTasks);
        } catch (Exception e) {
            throw new SusanException("Please enter a valid task keyword to find.");
        }
    }
}
