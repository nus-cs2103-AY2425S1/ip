package oyster.commands;

import oyster.LogicController;
import oyster.tasks.DeadlineTask;
import oyster.tasks.Task;
import oyster.tasks.TaskList;
import oyster.tasks.ToDoTask;

public class DeadlineCommand extends Command {
    private final Task task;

    public DeadlineCommand(String input) {
        task = DeadlineTask.fromInput(input);
    }

    /**
     * Adds a new DeadlineTask when executed
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();
        taskList.insert(task);

        setMessage(new String[] {
                "Alright, the task has been added!",
                "\t" + task.toString(),
                String.format(
                        "You now have %s %s!",
                        taskList.length(),
                        taskList.length() == 1 ? "task" : "tasks"
                )
        });
    }
}
