package Commands;
import Exceptions.InvalidListItemException;
import Storage.Storage;
import TaskList.TaskList;
import UI.UI;
import Tasks.Task;

public class DeleteTaskCommand extends Command {
    public DeleteTaskCommand(String s) {
        super(s);
    }
    public void execute (TaskList t, Storage s, UI ui) throws InvalidListItemException {
        Task task = t.removeTask(Integer.parseInt(String.valueOf(input.charAt(7))));
        s.writeToHardDisk(t.getTasks());
        int remainingTasks = t.getTasks().size();
        UI.removingTask(task, remainingTasks);
    }
}
