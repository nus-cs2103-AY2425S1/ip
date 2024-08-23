package duke.commands;

import java.time.LocalDateTime;
import java.util.List;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class FilterTaskCommand extends Command {
    private final LocalDateTime dateTime;

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
