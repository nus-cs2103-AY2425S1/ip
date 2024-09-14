package bob.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.Storage;
import bob.TaskList;
import bob.task.Task;

public class SummariseWeekCommand extends SummariseCommand {
    private LocalDateTime from;

    private LocalDateTime to;

    public SummariseWeekCommand() {
        LocalDateTime now = LocalDateTime.now();
        this.from = now.minusWeeks(1);
        this.to = now;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList completedTasks = tasks.getCompleted(from, to);
        String completionSummary = String.format(
                "This week, in the past 7 days, you completed a total of %s tasks, "
                        + "listed below:\n %s\n Great Job!",
                completedTasks.size(),
                completedTasks);
        return completionSummary;
    }
}
