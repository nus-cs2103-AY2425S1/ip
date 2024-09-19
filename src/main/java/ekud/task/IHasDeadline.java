package ekud.task;

import java.time.LocalDateTime;

/**
 * Represents that the {@link Task} is time-sensitive (deadline).
 *
 * @author uniqly
 */
public interface IHasDeadline {
    /**
     * Returns the date in which the task must be completed by.
     *
     * @return The deadline.
     */
    public LocalDateTime getDeadline();
}
