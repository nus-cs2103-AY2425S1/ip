public class MarkCommand extends Command {

    String indexString;

    public MarkCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            task.mark();
            ui.showCompletionMessage("YAY!! One down!!\n" + task.stringUI());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
