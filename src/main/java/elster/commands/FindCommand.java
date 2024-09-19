package elster.commands;

import java.util.List;

import elster.Storage;
import elster.TaskList;
import elster.Ui;
import elster.tasks.Task;

/**
 * EventCommand class to represent a command that is executed when the user tries to find a task by description.
 */
public class FindCommand extends Command {
    private FindCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static FindCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new FindCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        List<Task> foundTasks = tasklist.findByDescription(input.substring(5).strip());
        return ui.findByDescriptionMessage(foundTasks);
    }
}
