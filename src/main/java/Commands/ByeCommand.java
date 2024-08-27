package Commands;

import java.util.ArrayList;
import Applemazer.*;
import Tasks.*;

public class ByeCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {}

    @Override
    public boolean continueProcessing() {
        return false;
    }
}
