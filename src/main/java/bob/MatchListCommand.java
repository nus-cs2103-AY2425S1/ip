package bob;

public class MatchListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Did you mean 'list'?";
    }
}

