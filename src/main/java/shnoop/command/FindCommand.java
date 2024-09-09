package shnoop.command;

import shnoop.exceptions.ShnoopException;
import shnoop.storage.Storage;
import shnoop.tasks.TaskList;
import shnoop.ui.Ui;

/**
 * Encapsulates all the relevant actions to be taken when a List Command is issued.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the Command Line input and extracts the keyword to be found.
     *
     * @param keyword String to be searched for in TaskList.
     */
    public FindCommand(String keyword) throws ShnoopException {
        this.keyword = keyword.substring(5, keyword.length());
        if (this.keyword.isEmpty() || this.keyword.isBlank() || this.keyword.trim().length() <= 0) {
            throw new ShnoopException("✿ Shnoop ✿: You're playing with me, g.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.find(tasks.find(keyword));
    }
}
