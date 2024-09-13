package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class DeleteCommand extends Command {

    DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to return the delete index
     *
     * @param input
     */
    Integer process(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("delete");
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
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException, IOException {
        Integer index = process(this.input);
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid index inputted");
        }
        Task removed = tasks.remove(index);
        String remove = "Noted. I've removed this task:\n" + removed.toString().trim()
                + "\nNow you have " + tasks.size() + " tasks in the list.";
        return ui.show(remove);
    }
}
