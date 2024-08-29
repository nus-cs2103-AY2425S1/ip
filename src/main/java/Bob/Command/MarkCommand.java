package Bob.Command;

import Bob.Tasks.TaskList;
import Bob.UI;
public class MarkCommand extends Command{

    private final int index;
    public MarkCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) {
        taskList.markTaskAtIndex(index);
        UI.printMarkTask();
    }
}
