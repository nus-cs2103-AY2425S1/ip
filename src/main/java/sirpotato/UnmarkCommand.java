package sirpotato;

public class UnmarkCommand extends Command {
    
    private int itemNumber;

    public UnmarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(itemNumber);
    }
    
}