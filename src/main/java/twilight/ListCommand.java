package twilight;

/**
 * Facilitates the execution of listing the tasks in the tasklist.
 */
public class ListCommand extends Command {

    /**
     * Instantiates object.
     */
    public ListCommand() {};

    public String execute(TaskList tasks, Storage storage) throws InvalidInputException {
        return tasks.list();
    }
}
