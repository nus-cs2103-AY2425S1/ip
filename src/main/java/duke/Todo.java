package duke;

/**
 * The Class that stores tasks that do not have a deadline or start and end date.
 */
public class Todo extends Task {

    /**
     * Initialises the Todo object as the constructor.
     *
     * @param name The description of the Todo task.
     *
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a String of the Todo object formatted in the form required for storing in the hard disk.
     *
     */
    @Override
    protected String getWriteFormat() {
        return "T , " + (isDone ? "1" : "0") + " , " + name;
    }


    /**
     * Overrides the existing toString() method in the Task class to fit the required display requirement for Todo
     * objects.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
