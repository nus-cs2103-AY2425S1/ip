package friday.command;

import friday.task.Task;
import friday.task.TaskList;
import friday.util.FridayException;
import friday.util.Storage;
import friday.util.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(String[] inputs) {
        if (!inputs[1].chars().allMatch(Character::isDigit)) {
            throw new FridayException("\tInvalid input. Where would you like to " + inputs[0] + "?");
        }
        this.index = Integer.parseInt(inputs[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        if (index > tasks.getSize() || index <= 0) {
            throw new FridayException("\tInvalid input. It appears you are attempting to" +
                    " access something that does not exist yet.");
        }
        Task task = tasks.getTasks().get(index - 1);
        tasks.deleteTask(index - 1);
        ui.showTaskDeleted(task, tasks.getSize());
        storage.saveTasks(tasks.getTasks());
    }
}
