package oyster.commands;

import oyster.LogicController;
import oyster.tasks.DeadlineTask;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

/**
 * DeadlineCommand that creates a deadline task.
 */
public class DeadlineCommand extends Command {
    private final Task task;

    /**
     * Creates a DeadlineCommand given an input String to parse.
     *
     * @param input Input to parse into DeadlineCommand.
     */
    public DeadlineCommand(String input) {
        task = DeadlineTask.createFromInput(input);
    }

    /**
     * Adds a new DeadlineTask when executed.
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
            )});
    }
}
