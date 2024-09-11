package oyster.commands;

import oyster.LogicController;
import oyster.tasks.EventTask;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

/**
 * EventCommand that creates an Event Task.
 */
public class EventCommand extends Command {
    private final Task task;

    /**
     * Creates an EventCommand given an input String to parse.
     *
     * @param input The input String to parse.
     */
    public EventCommand(String input) {
        task = EventTask.createFromInput(input);
    }

    /**
     * Adds a new EventTask when executed.
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
