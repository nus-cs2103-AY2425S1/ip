package Commands;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
public class UnmarkTaskCommand extends Command{
    public UnmarkTaskCommand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) {
        t.markTaskAsUndone(Integer.parseInt(String.valueOf(input.charAt(7))));
        s.writeToHardDisk(t.getTasks());
        UI.markingTask(false, t.getTask(Integer.parseInt(String.valueOf(input.charAt(7)))));
    }

}
