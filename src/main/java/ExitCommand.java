public class ExitCommand extends Command {
    public ExitCommand() {
        super(0, null);
    }

    public String execute(TaskList tasks) {
        return "\tAlright, I've saved your tasks! Goodbye!";
    }

    @Override
    public boolean isExitCommand() {
        return true;
    }
}
