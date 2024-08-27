import java.util.ArrayList;

public class ByeCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {}

    @Override
    public boolean continueProcessing() {
        return false;
    }
}
