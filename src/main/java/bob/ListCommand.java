package bob;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return String.format("These are your tasks:\n%s", tasks);
    }
}
