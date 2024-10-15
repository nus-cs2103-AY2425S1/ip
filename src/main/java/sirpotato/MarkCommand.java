package sirpotato;

/**
 * Marks a task from the task list as complete
 */
public class MarkCommand extends Command {

    private static final String MARK_FAIL_ERROR_MSG = "Sorry, that is not a valid item number to mark";

    private int itemNumber;


    public MarkCommand(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.mark(itemNumber);
            assert (tasks.getTask(itemNumber).getCompletion()) : "Item should be marked complete";
            return ui.displayMarkedItem(itemNumber, tasks);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            return MARK_FAIL_ERROR_MSG;
        }

    }

}
