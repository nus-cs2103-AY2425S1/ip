package task;

/**
 * A class representing each individual task
 * Declared as abstract to prevent instantiation.
 *
 * @author celeschai
 */
public abstract class Task {
    private String name = "new task";
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println(this.toString());
        assert(this.toString().contains("[X]"));
    }

    /**
     * Marks task as incomplete.
     */
    public void markAsIncomplete() {
        this.isDone = false;
        assert(this.toString().contains("[ ]"));
    }

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.isDone
                        ? 'X'
                        : ' ',
                this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task task)) {
            return false;
        }
        return this.name.equals(task.name);
    }
}
