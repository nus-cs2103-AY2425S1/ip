package Commands;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
public class MarkTaskCommand extends Command{
    public MarkTaskCommand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) {
        t.markTaskAsDone(Integer.parseInt(String.valueOf(input.charAt(5))));
        s.writeToHardDisk(t.getTasks());
        UI.markingTask(true, t.getTask(Integer.parseInt(String.valueOf(input.charAt(5)))));
    }

}
