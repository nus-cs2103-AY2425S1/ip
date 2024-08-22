public class Task {
    private boolean status = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return this.details;
    }
}
