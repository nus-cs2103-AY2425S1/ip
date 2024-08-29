public class UnmarkCommand extends Command {

    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DarkpoolException {
        if (index < 0 || index >= taskList.getSize()) {
            throw new DarkpoolException("\tdo you know how to count? the task number is out of range");
        }
        ui.unmark(taskList.unmarkTask(index));
    }

}
