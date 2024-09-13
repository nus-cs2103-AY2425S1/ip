package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;

public class SortCommand extends Command {
    public SortCommand(String userInput) {
        super();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, ParseException, IOException {
        tasks.sort((o1, o2) -> Boolean.compare(o2.isCompleted(), o1.isCompleted()));
        String sort = "Sorted all tasks based on completion!";
        return ui.show(sort);
    }
}
