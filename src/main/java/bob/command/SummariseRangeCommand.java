package bob.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.Storage;
import bob.TaskList;

/**
 * Represents a command that summarises the number of tasks completed within the specified
 * date range.
 */
public class SummariseRangeCommand extends SummariseCommand {
    private LocalDateTime from;

    private LocalDateTime to;

    public SummariseRangeCommand(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        TaskList completedTasks = tasks.getCompleted(from, to);
        String completionSummary = String.format(
                "From %s to %s, you completed a total of %s tasks, listed below:\n%s\n Great Job!",
                from.format(formatter),
                to.format(formatter),
                completedTasks.size(),
                completedTasks);
        return completionSummary;
    }


}
