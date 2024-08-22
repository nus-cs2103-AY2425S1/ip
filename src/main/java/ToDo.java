/** This class represents a ToDo. It is child of the Task class.
 * @author Lee Ze Hao (A0276123J)
 */
public class ToDo extends Task{
    /** Constructor for a ToDo object. Sets name upon creation.
     * @param name Name of ToDo object.
     */
    public ToDo(String name) {
        super(name);
    }

    /** Returns string representation of the ToDo.
     * @return String containing indication of ToDo class, and string representation of parent Task class.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
