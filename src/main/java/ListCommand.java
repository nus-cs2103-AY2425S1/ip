public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.showMessage("Here are your current tasks: ");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showMessage((i + 1) + ". " + tasks.getTask(i));
        }
    }
}