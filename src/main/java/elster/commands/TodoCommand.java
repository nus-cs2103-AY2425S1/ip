package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;
import elster.tasks.ToDoTask;

/**
 * EventCommand class to represent a command that is executed when the user creates an todo task.
 */
public class TodoCommand extends Command {
    private TodoCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }
    public static TodoCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new TodoCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            ToDoTask task = ToDoTask.of(input);
            assert task != null : "Bug creating todo task";
            tasklist.addToList(task);
            storage.writeToFile(tasklist);
            return ui.addTaskMessage(tasklist, task);
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
