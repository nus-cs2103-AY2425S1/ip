package duke;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddDeadLineCommand extends Command {

    AddDeadLineCommand(String input) {
        this.input = input;
    }

    Task process(String input) throws DukeException, ParseException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("deadline");
        commands.add("/by");

        String[] deadLineItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if(deadLineItems.length == 0 || Objects.equals(deadLineItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        if(deadLineItems.length < 3) {
            throw new DukeException("DeadLine must be specified!");
        }

        return new DeadLine(deadLineItems[1], Parser.parseDate(deadLineItems[2]));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException {
        Task task = this.process(this.input);
        tasks.add(task);
        ui.showAddTask(task, tasks.size());
    }
}
