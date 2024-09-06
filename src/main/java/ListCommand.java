public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showTaskList(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
