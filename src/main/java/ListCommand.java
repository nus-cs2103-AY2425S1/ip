public class ListCommand extends Command{
    // display all the tasks in the list
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks.getTasks());
    }

}
