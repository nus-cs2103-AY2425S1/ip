package sirpotato;

public class DeleteCommand extends Command {

    private int itemNumber;

    public DeleteCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(itemNumber, tasks.getList());
        return ui.displayDeletionMessage(tasks.getList().get(itemNumber), tasks.getList());
    }
    
}