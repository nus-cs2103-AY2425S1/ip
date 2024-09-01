package gray.command;

import java.util.StringJoiner;

import gray.Tasks;

/**
 * A command that lists tasks.
 */
public class ListCommand implements Command {

    /**
     * Executes the list task command.
     *
     * @param tasks
     * @return
     */
    @Override
    public String execute(Tasks tasks) {
        StringJoiner joiner = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            joiner.add(String.format("%d. %s", i + 1, tasks.get(i)));
        }
        return joiner.toString();
    }
}
