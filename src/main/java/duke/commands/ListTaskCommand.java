package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListTaskCommand extends Command {
    public ListTaskCommand() {
        super();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println(Ui.formatTaskListings(taskList.getTasks(), false));
    }
}
