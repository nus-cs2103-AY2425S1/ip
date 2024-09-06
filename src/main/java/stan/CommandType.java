package stan;

/**
 * Enum representing the different types of commands that the Stan chatbot can handle.
 * Each command corresponds to a specific action the user can perform.
 * <ul>
 *   <li>BYE: Exits the chatbot.</li>
 *   <li>LIST: Displays all tasks in the task list.</li>
 *   <li>TODO: Adds a to-do task.</li>
 *   <li>EVENT: Adds an event task.</li>
 *   <li>DEADLINE: Adds a deadline task.</li>
 *   <li>MARK: Marks a task as done.</li>
 *   <li>UNMARK: Unmarks a task as not done.</li>
 *   <li>DELETE: Deletes a task from the task list.</li>
 *   <li>FIND: Searches for tasks that match a keyword.</li>
 * </ul>
 */
public enum CommandType {
    BYE, LIST, TODO, EVENT, DEADLINE, MARK, UNMARK, DELETE, FIND
}
