package topaz.task;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

import topaz.exception.InvalidStateException;
/**
 * Represents a task with a description and a completion status.
 * A {@link Task} object has a description and a boolean flag indicating whether the task is done.
 */
public class Task {
    protected String description;
    protected Boolean isDone = false;
    protected LocalDateTime finishTime = null;

    /**
     * Constructs a {@link Task} with the specified description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Constructs a {@link Task} with the specified description and default completion status of {@code false}.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }


    /**
     * Parses and sets the completion status of the task based on the provided state.
     *
     * @param state The state to be parsed, should be "0" or "1".
     * @throws InvalidStateException If the state is invalid or cannot be parsed.
     */
    public void parseState(String state) throws InvalidStateException {
        try {
            int stateInt = Integer.parseInt(state);
            if (stateInt == 1) {
                this.setDone();
            }
        } catch (NumberFormatException e) {
            throw new InvalidStateException(state);
        }
    }

    @Override
    public String toString() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return "[" + getStatusIcon() + "]" + " " + description;
    }

    public void setDone() {
        this.isDone = true;
        this.finishTime = LocalDateTime.now();
    }

    public void setUndo() {
        this.isDone = false;
        this.finishTime = null;
    }

    /**
     * Compute if the task is finished within this week, a week is from Monday of this week to Sunday of this week.
     * @return True if the task is completed within this week, otherwise return False.
     */
    public boolean isFinishedThisWeek() {
        if (this.finishTime == null) {
            return false;
        }

        // Get the start of Monday (Monday at 00:00:00)
        LocalDateTime mondayStart = this.finishTime
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .with(LocalTime.MIN);

        // Get the end of Sunday (Sunday at 23:59:59.999999999)
        LocalDateTime sundayEnd = this.finishTime
                .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
                .with(LocalTime.MAX);

        return this.finishTime.isBefore(sundayEnd)
                && this.finishTime.isAfter(mondayStart);
    }

    public String toFileRecord() {
        return "type" + " | " + "state" + " | " + description;
    }

}
