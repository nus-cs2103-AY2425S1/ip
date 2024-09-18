package sirpotato;

public class UnmarkCommand extends Command {
    
    private int itemNumber;
    private final String UNMARK_FAIL_ERROR_MSG = "Sorry, that is not a valid item number to unmark";

    public UnmarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(itemNumber);
            assert !(tasks.getTask(itemNumber).getCompletion()) : "The task should be unmarked";
            return ui.displayUnmarkedItem(itemNumber, tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return UNMARK_FAIL_ERROR_MSG;
        }
    }
    
}