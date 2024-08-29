package sirpotato;

public class MarkCommand extends Command {

    private int itemNumber;

    public MarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.mark(itemNumber);
    }
    
}