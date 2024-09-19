package elster.commands;

import elster.Elseption;
import elster.Storage;
import elster.TaskList;
import elster.Ui;
import elster.tasks.Task;

public class DeleteCommand extends Command {
    private DeleteCommand(Ui ui, TaskList tasklist, Storage storage, String input) {
        super(ui, tasklist, storage, input);
    }

    public static DeleteCommand of(Ui ui, TaskList tasklist, Storage storage, String input) {
        return new DeleteCommand(ui, tasklist, storage, input);
    }
    @Override
    public String execute() {
        try {
            int index = Integer.parseInt(input.substring(7).strip());

            Task task = tasklist.deleteTask(index);
            return ui.deleteTaskMessage(tasklist, task);
        } catch (Elseption e) {
            return ui.printErrorMessage(e.getMessage());
        }
    }
}
