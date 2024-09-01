public class AddCommand extends Command{
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.add(task);
        ui.add(task, list);
        s.saveList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
