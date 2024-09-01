public class ListCommand extends Command{

    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.showList();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
