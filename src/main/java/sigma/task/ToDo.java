package sigma.task;

/**
 * Class for a To-do Task
 *
 * @author Qiao Yi
 */
public class ToDo extends Task {

    /**
     * Constructor for a To-do Task
     * @param name The name of the to-do
     * @param status The completion status of the to-do
     */
    public ToDo(String name, boolean status) {
        super(name, status);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
