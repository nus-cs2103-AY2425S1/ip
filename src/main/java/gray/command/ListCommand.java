package gray.command;

import java.util.StringJoiner;

import gray.TaskList;
import gray.Ui;

/**
 * A command that lists tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list task command.
     *
     * @param ui
     * @param tasks
     */
    @Override
    public void execute(Ui ui, TaskList tasks) {
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            joiner.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        ui.say(joiner.toString());
    }
}
