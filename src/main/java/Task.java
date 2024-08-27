/** This class represents a Task. It is parent to the ToDo, Deadline and Event classes.
 * @author Lee Ze Hao (A0276123J)
 */

public class Task {
    enum TASK_TYPE {
        TODO,
        DEADLINE,
        EVENT,
        UNSET
    }

    private final String name;
    private final TASK_TYPE taskType;
    private boolean isDone;

    /**
     * Constructor for a task object. Sets task name upon creation.
     * @param name Name of task object.
     * @param taskType Type of task object.
     */
    public Task(String name, TASK_TYPE taskType) {
        this.name = name;
        this.taskType = taskType;
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the name of the task.
     * @return String The name of the task.
     */
    public String getTaskName() {
        return this.name;
    }

    /**
     * Returns whether the task is marked (done).
     * @return boolean Whether the task is marked (done).
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the type of the task.
     * @return TASK_TYPE Enum representing type of the task.
     */
    public TASK_TYPE getTaskType() {
        return this.taskType;
    }

    /**
     * Returns string representation of the task, containing isDone status and name of task.
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
