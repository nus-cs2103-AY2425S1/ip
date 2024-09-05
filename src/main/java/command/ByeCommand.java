package command;

import main.Storage;
import main.TaskList;

public class ByeCommand extends Command {
    public ByeCommand(){};
    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {

    }
}
