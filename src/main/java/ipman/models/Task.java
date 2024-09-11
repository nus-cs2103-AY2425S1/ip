package ipman.models;

/**
 * Represents a task that has a name and may or may not be completed.
 */
public abstract class Task {
    private boolean isDone = false;
    private final String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Converts this task to a String that fully represents the object.
     * A corresponding <code>deserialize</code> method is expected to be
     * implemented for the class.
     *
     * @return string that fully represents this object
     */
    public abstract String serialize();

    public abstract char getTaskType();

    @Override
    public String toString() {
        return String.format(
            "[%c][%s] %s",
            getTaskType(),
            this.getIsDone() ? "X" : " ",
            this.getName()
        );
    }
}
