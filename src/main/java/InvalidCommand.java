public class InvalidCommand extends Command {

    public InvalidCommand() {
        super(false);
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        System.out.println("Invalid Command.");
    }

}
