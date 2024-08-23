package duke.tasks;

/**
 * Represents the types of tasks available in the DailyTasks application.
 * These include:
 * <ul>
 *     <li>TODOS - Tasks without specific dates or deadlines.</li>
 *     <li>DEADLINE - Tasks with a specific deadline.</li>
 *     <li>EVENT - Tasks that occur during a specific time period.</li>
 * </ul>
 */
public enum TaskEnum {
    TODOS,
    DEADLINE,
    EVENT
}
