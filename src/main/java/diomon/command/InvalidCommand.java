package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class InvalidCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        setResponse("Neigh, this command dont exist");
    }
}
