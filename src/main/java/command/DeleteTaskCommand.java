package command;

import task.TaskList;

public class DeleteTaskCommand extends Command {
    private final String[] TASK_DELETED_PREFIXES = new String[] {
        "\tO-Okay, deleting task now.\n",
        "\tAlright, task removed.\n",
        "\tWhatever you say! Task deleted.\n",
        "\tCtrl-alt-del\n",
        "\tWatch me make this task disappear!\n"
    };

    public DeleteTaskCommand(int i) {
        super(i, null);
    }

    @Override
    public String execute(TaskList tasks) {
        try{
            tasks.removeTask(this.getI());
            return generateRandomPrefix(TASK_DELETED_PREFIXES) + tasks.taskListToString();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            return "\tHold on a second! You don't have those many tasks!";
        }
    }
}
