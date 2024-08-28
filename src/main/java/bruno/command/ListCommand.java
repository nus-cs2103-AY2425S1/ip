package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;

public class ListCommand extends Command {
    public ListCommand(TaskList tasks) {
        super(tasks);
    }
    @Override
    public void execute() {
        Ui.printList(getTasks().getTasks());
    }
}
