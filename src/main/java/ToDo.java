/**
 * Represents a task that does not have a specific date/time associated with it.
 * A ToDo object corresponds to a general task in Chobo chatbot.
 */
public class ToDo extends Task{
    /**
     * Creates a new ToDo task.
     *
     * @param name The description of the task.
     * @param done The status of the task (true if done, false otherwise).
     */
    public ToDo (String name, boolean done)
    {
        super(name, done);
    }

    /**
     * Returns a string representation of the ToDo task, including its type,
     * status, and name.
     *
     * @return A string representing the ToDo task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
