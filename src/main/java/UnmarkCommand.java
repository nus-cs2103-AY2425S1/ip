import java.util.ArrayList;

public class UnmarkCommand extends Command{
    private int itemIndex;

    public UnmarkCommand(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        try {
            ArrayList<Task> list = tasks.getList();
            Task unmarkTask = list.get(this.itemIndex);
            unmarkTask.setStatus(false);
            ui.showUnmarkTask(unmarkTask);
        } catch (IndexOutOfBoundsException e) {
            throw new DrBrownException("You got the count wrong! That’s not how you calculate time travel – you're off by a few gigawatts!");
        }
    }

    public boolean isExit() {
        return false;
    }

}
