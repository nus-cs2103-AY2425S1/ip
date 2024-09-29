package action;

import java.time.LocalDate;

import exception.BotException;
import task.TaskList;

/**
 * Action to list the schedule for a given date.
 *
 * @author dwsc37
 */
public class ListScheduleAction extends Action {
    private final LocalDate date;

    /**
     * Constructor for a <code>ListScheduleAction</code>.
     * @param date Date for which to list the schedule.
     */
    public ListScheduleAction(LocalDate date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList taskList) throws BotException {
        return taskList.generateSchedule(date);
    }
}
