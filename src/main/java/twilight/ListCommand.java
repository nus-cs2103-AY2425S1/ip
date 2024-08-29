package twilight;

public class ListCommand extends Command {

    public ListCommand() {};

    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {
        tasks.list();
    }
}
