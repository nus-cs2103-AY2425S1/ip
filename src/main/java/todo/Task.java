package todo;

/**
 * Represents each individual task
 *
 * @author celeschai
 * @version 1.0 20 Aug 2023
 */
public class Task {
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
        return String.format("[%c] %s", this.status ? 'X' : ' ', this.name);
    }
}
