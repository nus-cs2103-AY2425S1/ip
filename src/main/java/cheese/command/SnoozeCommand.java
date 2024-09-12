package cheese.command;

import java.time.LocalDate;

import cheese.Storage;
import cheese.TaskList;
import cheese.Ui;
import cheese.exception.CheeseException;
import cheese.task.Deadline;

/**
 * Command to reschedule tasks
 */
public class SnoozeCommand extends UpdateCommand {
    private int idx;
    private LocalDate date;
    private long daysDelayed;

    /**
     * Creates SnoozeCommand by taking in idx and date to delay to
     * @param idx index of task in TaskList
     * @param date date to reschedule to
     */
    public SnoozeCommand(int idx, LocalDate date) {
        super(idx, false);
        this.idx = idx;
        this.date = date;
        this.daysDelayed = -1;
    }

    /**
     * Creates SnoozeCommand by taking in idx and days to delay task
     * @param idx index of tasks in TaskList
     * @param daysDelayed no. of days to delay
     */
    public SnoozeCommand(int idx, long daysDelayed) {
        super(idx, false);
        this.idx = idx;
        this.daysDelayed = daysDelayed;
    }

    /**
     * Reschedule date for tasks from given index, returns response from Ui after task updated
     * @param tasks list of tasks
     * @param ui format response
     * @param storage store data
     * @return String response from Ui
     * @throws CheeseException if update Storage goes wrong
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        Deadline d = (Deadline) tasks.get(idx);
        if (daysDelayed == -1) {
            d.reschedule(date);
        } else {
            d.reschedule(daysDelayed);
        }
        return super.execute(tasks, ui, storage);
    }
}
