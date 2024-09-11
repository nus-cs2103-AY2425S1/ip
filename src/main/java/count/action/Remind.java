package count.action;

import java.time.LocalDate;
import java.util.ArrayList;

import count.TaskList;
import count.task.Deadline;
import count.task.Event;
import count.task.Task;

/**
 * The Remind class handles giving reminders
 * whenever Count is opened and there are tasks
 * stored close to the date of completion
 * @author Kieran Koh Jun Wei
 */
public class Remind extends Action {
    protected TaskList ls;
    protected int days;
    protected LocalDate currentTime;

    /**
     * Constructs Remind object
     * @param ls TaskList being searched
     * @param days Int for tasks with due dates within number of days
     */
    public Remind(TaskList ls, int days) {
        this.ls = ls;
        this.days = days;
        this.currentTime = LocalDate.now();
    }

    @Override
    public String run() {
        ArrayList<Task> list = this.ls.getTaskList();
        if (list.isEmpty()) {
            return "Croak! Looks like things are empty around here...";
        }
        String ans = "Here are the tasks in your list due within " + this.days + " days\n";
        LocalDate dueByTime = currentTime.plusDays(this.days);
        int numberOfSearchHits = 0;

        for (Task t: list) {
            if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                LocalDate deadlineEndTime = deadline.getEndTime();
                if (deadlineEndTime.isBefore(dueByTime) || deadlineEndTime.isEqual(dueByTime)) {
                    numberOfSearchHits++;
                    ans += numberOfSearchHits + ". " + deadline.toString() + "\n";
                }
            }

            if (t instanceof Event) {
                Event event = (Event) t;
                LocalDate eventEndTime = event.getEndTime();
                if (eventEndTime.isBefore(dueByTime) || eventEndTime.isEqual(dueByTime)) {
                    numberOfSearchHits++;
                    ans += numberOfSearchHits + ". " + event.toString() + "\n";
                }
            }
        }
        return ans;
    }
}
