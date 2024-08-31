package bob.tasks;


public class ToDo extends Task{

    public ToDo(String taskName) {
        super(taskName);
    }

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
