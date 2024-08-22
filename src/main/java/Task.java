/** This class represents a Task. It is parent to the ToDo, Deadline and Event classes.
 * @author Lee Ze Hao (A0276123J)
 */

public class Task {
    private final String name;
    private boolean isDone;

    /** Constructor for a task object. Sets task name upon creation.
     * @param name Name of task object.
     */
    public Task(String name) {
        this.name = name;
    }

    /** Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /** Marks task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /** Returns string representation of the task, containing isDone status and name of task.
     * @return String containing isDone status and name of task.
     */
    @Override
    public String toString() {
        String doneMark;
        if (isDone) {
            doneMark = "X";
        } else {
            doneMark = " ";
        }

        return "[" + doneMark + "] " + this.name;
    }
}
