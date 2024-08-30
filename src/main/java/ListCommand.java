public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.talk("Okie, here are your tasks!");
        tasks.showTaskList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
