public class DeleteAllCommand extends Command{

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        if(tasklist.deleteAll()) {
            ui.printText("Deleting all tasks");
        } else {
            ui.printText("No tasks to delete");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
