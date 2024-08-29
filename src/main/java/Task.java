/**
 * Provides a skeleton for Task objects.
 */
abstract class Task {
    private final String name;
    private boolean isDone = false;

    protected final String deli = "~!!";

    /**
     * Provides constructor for the class.
     *
     * @param name Name of the task.
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Returns task description in String to write in the file.
     */
    public String taskData() {
        String str = " " + deli;
        if (isDone) {
            str += " 1 " + deli + " " + name;
        } else {
            str += " 0 " + deli + " " + name;
        }
        return str;
    }

    /**
     * Marks the Task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the Task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns Task description in String for ChatBot interaction.
     */
    @Override
    public String toString() {
        String str = "[";
        if (isDone)
            str += "X";
        else
            str += " ";
        str += "] " + name;
        return str;
    }
}
