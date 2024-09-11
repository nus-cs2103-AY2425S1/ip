package sirpotato;

public class MarkCommand extends Command {

    private int itemNumber;

    public MarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(itemNumber);
        return ui.displayMarkedItem(itemNumber, tasks);
    }
    
}