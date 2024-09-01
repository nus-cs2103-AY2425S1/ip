import java.io.IOException;

public class ExitCommand extends Command {

    public ExitCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        tasks.writeToStorage(storage);
        ui.sayGoodbye();
    }

}
