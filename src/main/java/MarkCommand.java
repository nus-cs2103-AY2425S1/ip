public class MarkCommand extends Command {
    private TaskList list;
    private Storage store;
    private int index;
    private boolean isMark;

    public MarkCommand(TaskList list, Storage store, int index, boolean isMark) {
        this.list = list;
        this.store = store;
        this.index = index;
        this.isMark = isMark;
    }

    public void execute() {
        if (this.isMark) {
            list.markTaskAsDone(index);
        } else {
            list.unmarkTaskAsDone(index);
        }
        store.updateData(list);
    }

    public boolean isExit() {
        return false;
    }
}
