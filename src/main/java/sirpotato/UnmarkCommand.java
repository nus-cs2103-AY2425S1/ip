package sirpotato;

public class UnmarkCommand extends Command {
    
    private int itemNumber;

    public UnmarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.unmark(itemNumber);
        assert !(tasks.getTask(itemNumber).getCompletion()) : "The task should be unmarked";
        return ui.displayUnmarkedItem(itemNumber, tasks);
    }
    
}