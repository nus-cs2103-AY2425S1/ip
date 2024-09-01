public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.mark(index);
        ui.mark(list.get(index));
        s.saveList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
