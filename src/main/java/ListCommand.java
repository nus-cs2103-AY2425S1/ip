import java.text.ParseException;
import java.util.ArrayList;

public class ListCommand extends Command{
    ListCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
       ui.showAllTasks(tasks);
    }
}
