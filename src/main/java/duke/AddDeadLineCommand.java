package duke;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddDeadLineCommand extends Command {

    AddDeadLineCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to build a deadLine Task
     *
     * @param input
     */
    Task process(String input) throws DukeException, ParseException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("deadline");
        commands.add("/by");

        String[] deadLineItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if (deadLineItems.length == 0 || Objects.equals(deadLineItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        if (deadLineItems.length < 3) {
            throw new DukeException("DeadLine must be specified!");
        }

        LocalDateTime time;

        try {
            time = Parser.parseDate(deadLineItems[2]);
        }catch (ParseException e) {
            throw new DukeException("time must be in [dd-MM-yyyy HHmm] format");
        }

        return new DeadLine(deadLineItems[1], time);
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
        Task task = this.process(this.input);
        tasks.add(task);
        return ui.showAddTask(task, tasks.size());
    }
}
