package sirpotato;

public class MarkCommand extends Command {

    private int itemNumber;

    public MarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(itemNumber);
        assert (tasks.getTask(itemNumber).getCompletion()) : "Item should be marked complete";
        return ui.displayMarkedItem(itemNumber, tasks);
    }
    
}