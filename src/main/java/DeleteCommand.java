import java.text.ParseException;
import java.util.ArrayList;

public class DeleteCommand extends Command{

    DeleteCommand(String input) {
        this.input = input;
    }

    Integer process(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("delete");
        return Parser.ParseIndex(input, commands);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        Integer index = process(this.input);
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index inputted");
        }
        Task removed = tasks.remove(index);
        String remove = "Noted. I've removed this task:\n" + removed.toString().trim()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        ui.show(remove);
    }
}
