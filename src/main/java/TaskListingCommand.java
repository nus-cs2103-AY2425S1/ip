public class TaskListingCommand extends Command {
    public TaskListingCommand() {
        super("list", null);
    }

    @Override
    public void runCommandSpecificLogic(TaskList tasks, Storage storage) {
        Ui.printTaskList(tasks);
    }
}
