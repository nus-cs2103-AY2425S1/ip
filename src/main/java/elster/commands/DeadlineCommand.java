package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;
import elster.tasks.DeadlineTask;
import elster.tasks.EventTask;

public class DeadlineCommand extends Command {
    private DeadlineCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }
    public static DeadlineCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new DeadlineCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            DeadlineTask task = DeadlineTask.of(input);
            tasklist.addToList(task);
            storage.writeToFile(tasklist);
            return ui.addTaskMessage(tasklist, task);
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
