package Bob.Command;

import Bob.Tasks.TaskList;
import Bob.UI;

public class ListCommand extends Command{

    public ListCommand() {
        super(true);
    }

    public void execute(TaskList taskList) {
        UI.printTaskList(taskList);
    }
}
