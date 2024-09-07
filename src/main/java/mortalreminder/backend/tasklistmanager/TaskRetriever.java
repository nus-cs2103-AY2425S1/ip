package mortalreminder.backend.tasklistmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

import mortalreminder.errorhandling.MortalReminderException;
import mortalreminder.io.FormattedPrinting;
import mortalreminder.tasks.Task;
import mortalreminder.tasks.TimedTask;

/**
 * Retrieves a list of tasks based on different criteria.
 */
public class TaskRetriever {

    /**
     * Lists all upcoming tasks that have a due date in the future.
     * <p>
     * This method filters the tasks in the task list to find those that are either deadlines or events,
     * have a due date in the future, and are not yet marked as done. The upcoming tasks are then printed.
     *
     * @param taskList the {@link TaskList} containing the tasks to check for upcoming due dates.
     * @return a string message containing list of upcoming tasks that have not been marked yet.
     */
    public static String getUpcomingTasks(TaskList taskList) throws MortalReminderException {
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);

            /* stops the current iteration if the task is not a TimedTask or already done. */
            if (Objects.equals(task.getType(), "T") || task.getIsDone()) {
                continue;
            }
            assert task.getType().equals("D") || task.getType().equals("E");

            /* Add the task only if the task deadline or duration falls before the given deadline. */
            TimedTask timedTask = (TimedTask) task;
            if (LocalDateTime.now().isBefore(timedTask.getDueDate())) {
                tasks.add(task);
            }
        }
        return FormattedPrinting.printUpcomingDeadlinesEvents(tasks);
    }

    /**
     * Finds and returns the task based on descriptions matching the descriptions passed in.
     *
     * @param descriptions string argument(s) we are looking for in all matching tasks.
     * @return String representation of {@link TaskList} of matching tasks.
     */
    public static String findTasks(TaskList taskList, String... descriptions) throws MortalReminderException {
        TaskList similarTasks = new TaskList();
        for (Task task : taskList.getTaskList()) {
            for (String description : descriptions) {
                if (similarTasks.getTaskList().contains(task)) {
                    continue;
                }
                if (!task.getDescription().contains(description.trim())) {
                    continue;
                }
                similarTasks.loadTask(task);
            }
        }
        return FormattedPrinting.printSimilarTasks(similarTasks);
    }
}
