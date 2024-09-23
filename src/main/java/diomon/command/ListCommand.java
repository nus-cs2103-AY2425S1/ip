package diomon.command;

import diomon.Storage;
import diomon.task.TaskList;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Storage storage) {
        setResponse("Here ya go!\n" + tasks);
    };
}
