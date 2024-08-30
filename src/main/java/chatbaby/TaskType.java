package chatbaby;

/**
 * Represents the type of tasks that can be created.
 * The available task types are:
 * <ul>
 *   <li>TODO - A simple task without any date or time attached.</li>
 *   <li>DEADLINE - A task that needs to be completed by a certain date and time.</li>
 *   <li>EVENT - A task that spans a specific time period (start and end date/time).</li>
 * </ul>
 */
public enum TaskType {
    TODO, DEADLINE, EVENT;
}
