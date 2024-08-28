import java.text.ParseException;
import java.util.ArrayList;

public class MarkCommand extends Command{

    MarkCommand(String input) {
        this.input = input;
    }

    Integer process(String input) throws DukeException, ParseException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("mark");
        return Parser.ParseIndex(input, commands);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {

        int index = process(this.input);

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index inputted");
        }

        Task mark = tasks.get(index);
        mark.mark();
        ui.show("Nice! I've marked this task as done:" + mark);
    }
}
