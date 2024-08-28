package duke;

import java.text.ParseException;
import java.util.ArrayList;

public class UnMarkCommand extends Command{
    UnMarkCommand(String input) {
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

        Task unmark = tasks.get(index);
        unmark.unMark();
        ui.show("Ok I've unmarked this task as done:" + unmark);
    }
}
