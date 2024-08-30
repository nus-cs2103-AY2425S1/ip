/**
 * This class represents tasks without any date/time attached to it
 */
package duke.tasks;
public class Todo extends Task {

    /**
     * Constructs a `Todo` object with the specified name.
     *
     * @param name The name of the todo.
     */
    public Todo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataFormat() {
        return "T" + super.toDataFormat();
    }
}
