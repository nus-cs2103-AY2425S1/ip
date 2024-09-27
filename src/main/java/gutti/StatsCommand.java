package gutti;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Duration;

/**
 * Represents a command to show statistics of tasks completed in the past week.
 */
public class StatsCommand extends Command{

    /**
     * Executes the stats command, calculating the number of tasks completed in the last week.
     *
     * @param tasks   The list of tasks to retrieve statistics from.
     * @param ui      The user interface that displays the results of the command.
     * @param storage The storage handler that manages loading and saving tasks.
     * @return A string representing the number of tasks completed in the past week.
     * @throws GuttiException If there is an error during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        ArrayList<Task> taskList = tasks.getTasks();
        int totalTasksCompleted = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task specificTask = taskList.get(i);
            if (specificTask.isDone) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                Duration duration = Duration.between(currentDateTime, specificTask.completionDate);
                if (specificTask.isDone && duration.toDays() <= 7) {
                    totalTasksCompleted++;
                }
            }
        }
        return "You have completed " + totalTasksCompleted + " tasks in the past week!";

    }

    /**
     * Returns boolean on whether this command should terminate the application.
     *
     * @return true if the command is an exit command, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
