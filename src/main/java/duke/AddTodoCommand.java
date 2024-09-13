package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddTodoCommand extends Command {

    AddTodoCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to build a todo Task
     *
     * @param input
     */
    public Task process(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("todo");

        String[] todoItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if (todoItems.length == 0 || Objects.equals(todoItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        return new ToDo(todoItems[1]);
    }

    /**
     * Executes the task
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        Task task = this.process(this.input);
        tasks.add(task);
        return ui.showAddTask(task, tasks.size());
    }
}
