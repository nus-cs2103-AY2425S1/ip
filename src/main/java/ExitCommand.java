public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // only save the tasks that are still in the list when exiting
        storage.saveTasksToFile(tasks);
        ui.byeBye();
    }
}
