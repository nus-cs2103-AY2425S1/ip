package Bob.Command;

import Bob.Tasks.TaskList;
import Bob.UI;

public class ByeCommand extends Command{

    public ByeCommand() {
        super(false);
    }

    public void execute(TaskList taskList) {
        UI.printExit();
    }
}
