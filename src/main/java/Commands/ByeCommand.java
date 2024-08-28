package commands;

import java.util.ArrayList;
import applemazer.Storage;
import tasks.Task;

public class ByeCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {}

    @Override
    public boolean continueProcessing() {
        return false;
    }
}
