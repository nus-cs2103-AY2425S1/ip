package rizz.command;

import rizz.source.TaskList;

public class UndoCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        return "Alright! I've undone the previous command!\n" ;
    }
}
