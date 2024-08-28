package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

public class UnmarkCommand extends Command {
    private final int markIndex;

    public UnmarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            taskList.unmark(markIndex);
            setMessage(new String[]{
                    "I have unmarked the task!",
                    "\t" + taskList.getTask(markIndex).toString()
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
