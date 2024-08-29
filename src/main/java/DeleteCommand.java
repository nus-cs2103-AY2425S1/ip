public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String stringIndex) throws TheBotFatherException {
        try {
            this.index = Integer.parseInt(stringIndex) - 1;
        } catch (NumberFormatException e) {
            throw new TheBotFatherException("How do you not know what a number is, jeez");
        }
    }

    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        Task deletedTask = taskList.delete(index);
        storage.toFile(taskList);
        ui.printDeleted(deletedTask);
    }
}
