public class ListCommand extends Command {

    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        ui.showMessage("Fanny:");
        list.printList();
        ui.showHorizontalLine();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}

