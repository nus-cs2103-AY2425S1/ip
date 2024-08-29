public class ExitCommand extends Command {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        storage.writeFile(list);
        ui.printGoodBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
