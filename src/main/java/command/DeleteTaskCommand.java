package command;

import task.TaskList;

public class DeleteTaskCommand extends Command {
    private static final String[] TASK_DELETED_PREFIXES = new String[] {
        "O-Okay, deleting task now.\n",
        "Alright, task removed.\n",
        "Whatever you say! Task deleted.\n",
        "Ctrl-alt-del\n",
        "Watch me make this task disappear!\n"
    };

    public DeleteTaskCommand(int i) {
        super(i, null);
    }

    @Override
    public String execute(TaskList tasks) {
        try {
            tasks.removeTask(this.getTaskIndex());
            return generateRandomPrefix(TASK_DELETED_PREFIXES) + tasks.taskListToString();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "Hold on a second! You don't have those many tasks!";
        }
    }
}
