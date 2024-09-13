package duke;

import java.text.ParseException;
import java.util.ArrayList;

public class MarkCommand extends Command {

    MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to return the mark index
     *
     * @param input
     */
    Integer process(String input) throws DukeException {
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

        Task mark = tasks.get(index);
        mark.mark();
        assert mark.isCompleted() : "Task has not been marked";
        return ui.show("Nice! I've marked this task as done:" + mark);
    }
}
