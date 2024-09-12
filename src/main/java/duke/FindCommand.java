package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FindCommand extends Command {

    FindCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to build a todo Task
     *
     * @param input
     */
    public String process(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("find");

        String[] findItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if (findItems.length == 0 || Objects.equals(findItems[1], "")) {
            throw new DukeException("find query must be specified!");
        }

        return findItems[1];
    }

    /**
     * Executes the task
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String find = this.process(this.input);
        int count = 1;
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.getList()) {
            if (task.getDescription().contains(find)) {
                result.append(count).append(". ").append(task).append("\n");
                count += 1;
            }
        }
        return result.toString();
    }
}
