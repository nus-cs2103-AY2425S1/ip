package bob.tasks;

public class ToDo extends Task{

    /**
     * Constructor for To Do
     *
     * @param taskName The name of the task
     */
    public ToDo(String taskName) {
        super(taskName);
    }

    /**
     * Exports the To Do object to string to be saved in a text file
     *
     * @return String format of the To Do object to be exported
     */
    @Override
    public String export() {
        return String.format("todo %s", super.export());
    }

    /**
     * Converts the To Do object to a string to be printed
     *
     * @return String format of the To Do object for printing
     */
    @Override
    public String toString() {
        return String.format("[%s]%s","T", super.toString());
    }
}
