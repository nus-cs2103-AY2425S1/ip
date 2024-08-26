public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.print("Here are the tasks in your list:");
        int numTasks = tasks.getNumTasks();
        for (int i = 1; i <= numTasks; i++) {
            Task task = tasks.getTask(i);
            Ui.print(i + "." + task.toString());
        }
    }
}
