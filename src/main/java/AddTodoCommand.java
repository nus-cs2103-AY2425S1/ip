import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddTodoCommand extends Command{

    AddTodoCommand(String input) {
        this.input = input;
    }

    public Task process(String input) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("todo");

        String[] todoItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if(todoItems.length == 0 || Objects.equals(todoItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        return new ToDo(todoItems[1]);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = this.process(this.input);
        tasks.add(task);
        ui.showAddTask(task, tasks.size());
    }
}
