package Commands;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class BreakCommand extends Command {
    public BreakCommand() {
        super("bye");
    }
    public void execute(TaskList t, Storage s, UI ui) {
        // do nothing
    }
    @Override
    public boolean exitBot() {
        return true;
    }
}
