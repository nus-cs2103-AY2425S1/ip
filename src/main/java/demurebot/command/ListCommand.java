package demurebot.command;

import demurebot.task.Task;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        for (int i = 0; i < list.getSize(); i++) {
            Task task = list.getTask(i);
            System.out.println((i + 1) + "." + task);
        }
    }
}
