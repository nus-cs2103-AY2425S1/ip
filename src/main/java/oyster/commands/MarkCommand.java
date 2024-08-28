package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

public class MarkCommand extends Command {
    private final int markIndex;

    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    /**
     * Marks a Task when executed
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            taskList.mark(markIndex);
            setMessage(new String[]{
                    "Well done on completing the task!",
                    "\t" + taskList.getTask(markIndex).toString()
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
