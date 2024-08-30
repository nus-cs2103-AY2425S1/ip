package Command;

import Tools.Storage;
import Tools.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(TaskList tasks, Storage storage, String command) {
        super(tasks, storage, command);
    }

    public void execute() {
        tasks.deleteTask(Integer.parseInt(command.substring(7)) - 1);
    }

}
