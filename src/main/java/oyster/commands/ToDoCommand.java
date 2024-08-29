package oyster.commands;

import oyster.LogicController;
import oyster.tasks.DeadlineTask;
import oyster.tasks.Task;
import oyster.tasks.TaskList;
import oyster.tasks.ToDoTask;

public class ToDoCommand extends Command {
    private final Task task;

    /**
     * Creates a ToDoCommand based on the input String to parse.
     *
     * @param input The input String to parse.
     */
    public ToDoCommand(String input) {
        task = ToDoTask.fromInput(input);
    }

    /**
     * Adds a new TodoTask when executed.
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
