package twilight;

/**
 * Facilitates the execution of listing the tasks in the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Instantiates object.
     */
    public ListCommand() {};

    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        tasks.list();
    }
}
