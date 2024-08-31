package command;

import exceptions.TaskDoesNotExistException;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list) {
        try {
            String task = list.delete(index);
            him.Ui.sayDeleted(task);
        } catch (TaskDoesNotExistException e) {
            him.Ui.say(e.getMessage());
        }
    }
}
