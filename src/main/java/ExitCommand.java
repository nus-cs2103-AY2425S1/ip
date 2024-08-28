import java.io.IOException;
import java.text.ParseException;

public class ExitCommand extends Command{
    ExitCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException, IOException {
        Command.exit = true;
        ui.show("Bye!");
        storage.writeData(tasks);
    }
}
