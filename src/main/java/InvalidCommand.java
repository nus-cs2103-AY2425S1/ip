public class InvalidCommand extends Command {
    private final String message;

    public InvalidCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(message);
    }
}