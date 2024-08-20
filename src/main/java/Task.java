// TODO: Change this into an interface for later levels
public class Task {
    private String description;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }
}