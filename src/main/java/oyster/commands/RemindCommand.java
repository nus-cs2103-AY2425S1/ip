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
        StringBuilder builder = new StringBuilder();

        // Find ongoing events first
        buildTasksIntoStrings(getTasksOverdue(), builder, "You have no ongoing events.", "ongoing events:");

        builder.append("\n");

        // Find overdue tasks next
        buildTasksIntoStrings(getTasksOverdue(), builder, "You have none overdue, well done!.", "overdue items:");

        builder.append("\n");

        // Find soon due tasks next
        buildTasksIntoStrings(getTasksDueSoon(), builder, "You have none due soon, well done!", "tasks due soon:");

        // Display message
        setMessage(builder.toString().trim());
    }

    private void buildTasksIntoStrings(
        ArrayList<Task> tasks, StringBuilder builder, String emptyMessage, String notEmptyMessage) {
        if (tasks.isEmpty()) {
            builder.append(emptyMessage).append("\n");
        } else {
            builder.append(tasks.size()).append(" ").append(notEmptyMessage).append("\n");
        }
        for (Task task : tasks) {
            builder.append("• ").append(task).append("\n");
        }
    }

    private ArrayList<Task> getOngoingEvents() {
        TaskList taskList = LogicController.getTaskList();

        return taskList.filter(
            task -> task instanceof EventTask castedTask
                && !task.isMarked()
                && castedTask.hasStarted()
                && !castedTask.isDue());
    }

    private ArrayList<Task> getTasksOverdue() {
        TaskList taskList = LogicController.getTaskList();

        return taskList.filter(
            task -> {
                if (task instanceof DeadlineTask || task instanceof EventTask) {
                    if (!task.isMarked()) {
                        DatedTask castedTask = (DatedTask) task;
                        return castedTask.isDue();
                    }
                }
                return false;
            });
    }

    private ArrayList<Task> getTasksDueSoon() {
        TaskList taskList = LogicController.getTaskList();

        return taskList.filter(
            task -> {
                if (task instanceof DeadlineTask castedTask) {
                    return !task.isMarked() && !castedTask.isDue();
                }

                if (task instanceof EventTask castedTask) {
                    return !task.isMarked() && !castedTask.hasStarted();
                }
                return false;
            });
    }
}
