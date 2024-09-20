package alisa.command;

import alisa.Storage;
import alisa.task.TaskList;
import alisa.Ui;

public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     *
     * Exits the program.
     *
     * @param taskList List of all the tasks.
     * @param ui Ui that displays messages and interacts with user.
     * @param storage Storage that saves data into a file.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

    /**
     * {@inheritDoc}
     *
     * Indicates that the program should terminate.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
