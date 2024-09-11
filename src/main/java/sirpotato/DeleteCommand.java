package sirpotato;

public class DeleteCommand extends Command {

    private int itemNumber;
    private final String DELETION_ERROR_MSG = "That is not a valid item number to delete";

    public DeleteCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int initialLength = tasks.getSizeOfList();
            Task t = tasks.getTask(itemNumber);
            tasks.delete(itemNumber);
            int afterDeletionLength = tasks.getSizeOfList();
            assert (afterDeletionLength == initialLength - 1) : "one item should have been deleted";
            return ui.displayDeletionMessage(t, tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e){
            return DELETION_ERROR_MSG;
        }
    }
    
}