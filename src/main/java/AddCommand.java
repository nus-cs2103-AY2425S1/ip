public class AddCommand extends Command {
    private TaskList list;
    private Storage store;
    private Task task;

    public AddCommand(TaskList list, Storage store, Task task) {
        this.list = list;
        this.store = store;
        this.task = task;
    }

    public void execute() {
        list.addToList(task);
        store.updateData(list);
    }

    
    public boolean isExit() {
        return false;
    }

}