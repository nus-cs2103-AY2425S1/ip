package derek.command;

import derek.Storage;
import derek.Ui;
import derek.task.Task;
import derek.task.TaskList;

import java.time.LocalDateTime;

/**
 * Represents a command that finds tasks completed in the past week and returns them.
 */
public class TasksCompletedCommand extends Command {
    LocalDateTime date;

    /**
     * Creates a new TasksCompletedCommand with the given parameters.
     *
     * @param command The command string.
     * @param storage The storage object for handling saved tasks.
     * @param ui The UI object for interacting with the user.
     * @param date The date from which tasks will be checked.
     */
    public TasksCompletedCommand(String command, Storage storage,
                                 Ui ui, LocalDateTime date) {
        super(command, storage, ui);
        this.date = date;
    }

    /**
     * Executes the command to find tasks completed in the past week.
     *
     * @return A string containing the list of tasks completed in the past week.
     */
    @Override
    public String execute() {
        TaskList tasksCompleted = this.findCompletedTasks();
        Ui ui = this.getUi();
        return ui.returnListOfPastWeek(tasksCompleted);
    }

    /**
     * Finds tasks completed in the past week.
     *
     * @return A TaskList containing tasks completed within the past week.
     */
    public TaskList findCompletedTasks() {
        TaskList taskList = this.getTaskList();
        TaskList tasksCompleted = new TaskList();
        int sizeOfTaskList = this.getSizeOfTaskList();
        LocalDateTime dateOneWeekBefore = getDateOneWeekBefore();
        for (int i = 0; i < sizeOfTaskList; i++) {
            Task task = taskList.get(i);
            LocalDateTime dateOfCompletion = task.getCompletionTime();
            if (dateOfCompletion != null &&
                    isWithinLastWeek(dateOfCompletion, dateOneWeekBefore)) {
                tasksCompleted.add(task);
            }
        }
        return tasksCompleted;
    }

    /**
     * Gets the date one week before the given date.
     *
     * @return A LocalDateTime representing the date one week before the given date.
     */
    public LocalDateTime getDateOneWeekBefore() {
        return this.date.minusDays(7);
    }

    /**
     * Checks if a given task completion date is within the last week.
     *
     * @param dateOfCompletion The completion date of the task.
     * @param dateOneWeekBefore The date one week before the current date.
     * @return true if the task was completed within the past week, false otherwise.
     */
    public boolean isWithinLastWeek(LocalDateTime dateOfCompletion,
                                    LocalDateTime dateOneWeekBefore) {
        return (dateOfCompletion.isAfter(dateOneWeekBefore) ||
                dateOfCompletion.isEqual(dateOneWeekBefore))
                &&
                (dateOfCompletion.isBefore(this.date) ||
                        dateOfCompletion.isEqual(this.date));
    }
}
