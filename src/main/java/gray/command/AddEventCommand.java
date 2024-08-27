package gray.command;

import gray.Ui;
import gray.task.EventTask;
import gray.task.TaskList;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {

    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AddEventCommand(String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) {
        EventTask task = new EventTask(description, startDate, endDate);
        tasks.add(task);
        ui.say(String.format("""
                Got it. I've added this task:
                    %s
                Now you have %d tasks in the list.""",
                task, tasks.size()));
    }
}
