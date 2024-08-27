public class DeleteCommand extends Command {
    private TaskList list;
    private Storage store;
    private int index;

    public DeleteCommand(TaskList list, Storage store, int index) {
        this.list = list;
        this.store = store;
        this.index = index;
    }

    public void execute() {
        list.deleteFromList(index);
        store.updateData(list);
    }

    public boolean isExit() {
        return false;
    }

}
