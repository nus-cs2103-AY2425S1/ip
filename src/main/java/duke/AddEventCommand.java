package duke;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class AddEventCommand extends Command {
    AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Processes input given by the user to build an event Task
     *
     * @param input
     */
    Task process(String input) throws DukeException, ParseException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("event");
        commands.add("/from");
        commands.add("/to");

        String[] eventItems = Arrays.stream(Parser.ParseString(input, commands)).map(String::trim).toArray(String[]::new);

        if (eventItems.length == 0 || Objects.equals(eventItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        if (eventItems.length == 2) {
            throw new DukeException("from must be specified!");
        }

        if (eventItems.length == 3) {
            throw new DukeException("to must be specified!");
        }

        LocalDateTime from;
        LocalDateTime to;

        try {
            from = Parser.parseDate(eventItems[2]);
            to = Parser.parseDate(eventItems[3]);
        }catch (ParseException e) {
            throw new DukeException("time must be in [dd-MM-yyyy HHmm] format");
        }

        if (to.isBefore(from)) {
            throw new DukeException("[to] date is before [from] date!");
        }

        return new Event(eventItems[1], from, to);
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
