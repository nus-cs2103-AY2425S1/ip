package Bob.Command;

import Bob.Tasks.TaskList;
import Bob.UI;

public class UnmarkCommand extends Command{

    private final int index;
    public UnmarkCommand(int index) {
        super(true);
        this.index = index;
    }

    public void execute(TaskList taskList) {
        taskList.unmarkTaskAtIndex(index);
        UI.printUnmarkTask();
    }
}
