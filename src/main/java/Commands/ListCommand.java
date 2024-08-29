package Commands;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class ListCommand extends Command {
    public ListCommand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) {
        t.printTasks();
    }
}
