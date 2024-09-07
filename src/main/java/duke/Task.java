package duke;

/**
 * The parent class for all Task types. This holds all the methods to be overridden or implemented by specialised child
 * classes.
 */
public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Initialises the Task object as the constructor.
     *
     * @param name The description of the Task.
     *
     */
    public Task(String name) {
        setName(name);
        setDone(false);
    }

    /**
     * The constructor for the Task class.
     * @param name The instructions the user wants to store in the Task object.
     * @param isDone Flag for whether the task has already been done.
     */
    public Task(String name, boolean isDone) {
        setName(name);
        setDone(isDone);
    }

    private void setName(String name) {
        this.name = name;
    }


    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    protected String getWriteFormat() {
        return "";
    }

    /**
     * Overrides the existing toString() method in the Task class to fit the required display requirement for
     * Task objects.
     */
    @Override
    public String toString() {
        String res = "[";
        if (isDone) {
            res = res + "X";
        } else {
            res = res + " ";
        }
        res = res + "] " + name;
        return res;
    }
}
