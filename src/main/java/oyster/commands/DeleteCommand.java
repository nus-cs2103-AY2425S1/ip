package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

public class DeleteCommand extends Command {
    private final int markIndex;

    public DeleteCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        try {
            String deletedTask = taskList.pop(markIndex).toString();
            setMessage(new String[]{
                    "I have deleted the task!",
                    "\t" + deletedTask,
                    String.format("You now have %s %s!", taskList.length(), taskList.length() == 1 ? "task" : "tasks")
            });
        } catch (Exception e) {
            setMessage("Task number does not exist!");
        }
    }
}
