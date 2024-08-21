package todo;

/**
 * A class representing each individual task
 * Declared as abstract to prevent instantiation
 *
 * @author celeschai
 */
public abstract class Task {
    private String name = "new task";
    private boolean status = false;

    public Task(String name) {
        this.name = name;
    }

    public void markAsDone() {
        this.status = true;
    }

    public void markAsIncomplete() {
        this.status = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s",
                this.status ? 'X' : ' ',
                this.name);
    }
}
