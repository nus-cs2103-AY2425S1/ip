package friday.command;

import friday.task.TaskList;
import friday.util.Storage;
import friday.util.Ui;

public class FindCommand extends Command {
    private final String find;

    public FindCommand(String[] find) {
        this.find = find[1];
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasks(find);
    }
}
