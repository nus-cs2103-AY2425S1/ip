package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;
import elster.tasks.EventTask;

public class EventCommand extends Command {
    private EventCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }
    public static EventCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new EventCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            EventTask task = EventTask.of(input);
            tasklist.addToList(task);
            storage.writeToFile(tasklist);
            return ui.addTaskMessage(tasklist, task);
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
