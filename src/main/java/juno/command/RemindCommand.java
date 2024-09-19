package juno.command;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import juno.manager.TaskManager;
import juno.task.Task;

/**
 * A class to send reminders to users about upcoming tasks that are TIME_TO_SCHEDULE hours from the scheduled deadline.
 */
public class RemindCommand extends Command {

    private static final String JUNO_REMINDER_STRING = "\uD83C\uDF1F Juno's Reminder! \uD83C\uDF1F\n"
            + "You’ve got some tasks on the horizon that need your magic touch! ✨"
            + "Don't let these slip through the skies!\nHere's your task(s) due soon:";

    private static final long TIME_TO_SCHEDULE = 12;
    private static final String NO_TASK_STRING = "No upcoming tasks for schedule! "
            + "Try creating one with the add commands.";
    private final String eventString = "event";
    private final String deadlineString = "deadline";
    private ArrayList<Task> tasks;


    /**
     * Constructs a RemindCommand instance which takes in a TaskManager instance.
     * Initialises the task list from the TaskManager by calling <code>getTasksArray()</code> method.
     *
     * @param taskManager The TaskManager instance to retrieve the tasks from.
     */
    public RemindCommand(TaskManager taskManager) {
        this.tasks = taskManager.getTasksArray();
    }

    /**
     * Executes the command to find all the tasks that are going to due soon.
     * If the task list is empty, return "", and this will be handled by the calling method.
     * Otherwise, prints each task in a formatted list and the tasks due for schedule.
     * Can be executed with the "remind" input prompt.
     */
    @Override
    public String runCommand() {
        if (this.tasks.isEmpty()) {
            return "";
        }
        LocalDateTime twelveHoursLater = LocalDateTime.now().plusHours(TIME_TO_SCHEDULE);
        ArrayList<Task> taskToRemind = this.tasks.stream()
                .filter(task -> task.getTaskType().equalsIgnoreCase(this.eventString)
                        || task.getTaskType().equalsIgnoreCase(this.deadlineString))
                .filter(task -> twelveHoursLater.isAfter(task.getEndTime()))
                .filter(task -> !task.getIsDone())
                .collect(Collectors.toCollection(ArrayList::new));
        if (taskToRemind.isEmpty()) {
            return "";
        }
        StringBuilder outString = new StringBuilder(JUNO_REMINDER_STRING);

        String taskList = IntStream.range(0, taskToRemind.size())
                .mapToObj(i -> String.format("%d. %s", (i + 1), taskToRemind.get(i).toString()))
                .collect(Collectors.joining("\n"));

        outString.append("\n")
                .append(taskList)
                .append("\n")
                .append("\uD83C\uDFAF You have ")
                .append(taskToRemind.size())
                .append(" tasks due soon. Keep going!");
        return outString.toString();
    }
    public String returningStringToUser() {
        return NO_TASK_STRING;
    }
}
