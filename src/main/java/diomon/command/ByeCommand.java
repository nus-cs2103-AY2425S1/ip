package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class ByeCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        setResponse("Zzzzz...");
        storage.save(tasks.toStorageString());
        canExit = true;
        System.exit(0);
    }
}
