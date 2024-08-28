package oyster.commands;

import oyster.LogicController;
import oyster.tasks.*;

public class EventCommand extends Command {
    private final Task task;

    public EventCommand(String input) {
        task = EventTask.fromInput(input);
    }

    /**
     * Adds a new EventTask when executed
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
