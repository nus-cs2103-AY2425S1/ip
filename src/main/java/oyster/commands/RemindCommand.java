package oyster.commands;

import java.util.ArrayList;

import oyster.LogicController;
import oyster.tasks.DatedTask;
import oyster.tasks.DeadlineTask;
import oyster.tasks.EventTask;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

/**
 * RemindCommand that displays items due soon or overdue.
 */
public class RemindCommand extends Command {
    /**
     * Displays items due soon or overdue.
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        StringBuilder builder = new StringBuilder();

        // Find ongoing events first
        ArrayList<Task> ongoingEvents = taskList.filter(
            task -> task instanceof EventTask castedTask
                && !task.isMarked()
                && castedTask.hasStarted()
                && !castedTask.isDue());

        if (ongoingEvents.isEmpty()) {
            builder.append("You have no ongoing events.").append("\n");
        } else {
            builder.append(ongoingEvents.size()).append(" ongoing events:").append("\n");
        }
        for (Task task : ongoingEvents) {
            builder.append("• ").append(task).append("\n");
        }

        builder.append("\n");

        // Find overdue tasks next
        ArrayList<Task> overdueTasks = taskList.filter(
            task -> {
                if (task instanceof DeadlineTask || task instanceof EventTask) {
                    if (!task.isMarked()) {
                        DatedTask castedTask = (DatedTask) task;
                        return castedTask.isDue();
                    }
                }
                return false;
            });

        if (overdueTasks.isEmpty()) {
            builder.append("You have none overdue, well done!.").append("\n");
        } else {
            builder.append(overdueTasks.size()).append(" overdue items:").append("\n");
        }
        for (Task task : overdueTasks) {
            builder.append("• ").append(task).append("\n");
        }

        builder.append("\n");

        // Find soon due tasks next
        ArrayList<Task> dueSoonTasks = taskList.filter(
            task -> {
                if (task instanceof DeadlineTask castedTask) {
                    return !task.isMarked() && !castedTask.isDue();
                }

                if (task instanceof EventTask castedTask) {
                    return !task.isMarked() && !castedTask.hasStarted();
                }
                return false;
            });

        if (dueSoonTasks.isEmpty()) {
            builder.append("You have none due soon, well done!").append("\n");
        } else {
            builder.append(dueSoonTasks.size()).append(" tasks due soon:").append("\n");
        }
        for (Task task : dueSoonTasks) {
            builder.append("• ").append(task).append("\n");
        }

        // Display message
        setMessage(builder.toString().trim());
    }
}
