package duke;

import java.text.ParseException;

public class ListCommand extends Command {
    ListCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the task
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        return ui.showAllTasks(tasks);
    }
}
