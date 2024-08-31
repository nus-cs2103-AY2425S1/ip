package command;

import exceptions.AlreadyCompletedException;
import exceptions.TaskDoesNotExistException;
import task.TaskList;

public class MarkCommand extends Command {
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList list) {
        try {
            list.complete(this.index);
            him.Ui.sayCompleted(list.taskAt(this.index));
        } catch (AlreadyCompletedException | TaskDoesNotExistException e) {
            him.Ui.say(e.getMessage());
        }
    }
}
