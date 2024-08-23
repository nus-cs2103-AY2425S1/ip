package duke.commands;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

public class FilterTaskCommand extends Command {
    LocalDateTime dateTime;

    public FilterTaskCommand(LocalDateTime dateTime) {
        super();
        this.dateTime = dateTime;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> tasks = taskList.getTasksOccurring(dateTime);
        System.out.println(Ui.formatTaskListings(tasks, true));
    }
}
