public class ListCommand extends Command {

    public ListCommand() {};

    public void execute(TaskList tasks, Storage storage) {
        tasks.list();
    }
}
