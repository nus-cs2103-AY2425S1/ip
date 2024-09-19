package elster.commands;

import elster.Storage;
import elster.TaskList;
import elster.Ui;

/**
 * ListCommand class to represent a command that is executed when the user creates queries for their list of tasks.
 */
public class ListCommand extends Command {
    private TaskList tasklist;
    private ListCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
        this.tasklist = tasklist;
    }

    public static ListCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new ListCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        return ui.printList(tasklist);
    }
}
