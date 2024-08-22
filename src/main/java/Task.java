public class Task {
    private boolean status = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    public void markCompleted() {
        this.status = true;
    }
    public void markUnCompleted() {
        this.status = false;
    }
    @Override
    public String toString() {
        String status = this.status ? "X": " ";
        return String.format("[%s] %s ", status, this.details);
    }

    public String getDetails() {
        return this.details;
    }

}
