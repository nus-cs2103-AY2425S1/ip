public class DeleteCommand extends Command {

    String indexString;

    public DeleteCommand(String indexString) {
        super(false);
        this.indexString = indexString;
    }

    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) throws InvalidIndexException {
        try {
            int index = Integer.parseInt(this.indexString);
            Task task = tasks.get(index);
            tasks.delete(index);
            ui.showCompletionMessage("Aju nice! I've got rid of this task: " + task.stringUI()
                    + "\nWe have " + tasks.size() + " tasks left in the bag~");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidIndexException();
        }
    }

}
