package gutti;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Duration;

public class StatsCommand extends Command{
    
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        ArrayList<Task> taskList = tasks.getTasks();
        int totalTasksCompleted = 0;
        for (int i = 0; i < taskList.size(); i++) {
            Task specificTask = taskList.get(i);
            LocalDateTime currentDateTime = LocalDateTime.now();
            Duration duration = Duration.between(currentDateTime, specificTask.completionDate);
            if (specificTask.isDone && duration.toDays() <= 7) {
                totalTasksCompleted ++;
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
