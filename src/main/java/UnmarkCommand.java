public class UnmarkCommand extends Command {
    private String[] parsed;

    public UnmarkCommand(String[] parsed) {
        this.parsed = parsed;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws TalkerException {
        list.unmarkTaskComplete(parsed, ui);
    }
}
