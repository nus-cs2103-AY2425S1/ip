package duke;

import java.text.ParseException;
import java.util.ArrayList;

public class UnMarkCommand extends Command {
    UnMarkCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to return the unmark index
     *
     * @param input
     */
    Integer process(String input) throws DukeException, ParseException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("mark");
        return Parser.ParseIndex(input, commands);
    }

    /**
     * Executes the task
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {

        int index = process(this.input);

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index inputted");
        }

        Task unmark = tasks.get(index);
        unmark.unMark();
        return ui.show("Ok I've unmarked this task as done:" + unmark);
    }
}
