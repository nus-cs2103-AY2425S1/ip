public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StreamsException {
        if (tasks.isEmpty()) {
            ui.showMessage("your task list is empty!!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + tasks.getTask(i));
            }
        }
    }
}
