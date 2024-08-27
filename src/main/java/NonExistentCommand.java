import java.util.ArrayList;

public class NonExistentCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Storage storage) {}

    @Override
    public boolean continueProcessing() {
        return true;
    }
}
