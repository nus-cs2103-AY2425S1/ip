public class Task {
    private boolean status = false;
    private String details;

    public Task(String details) {
        this.details = details;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    @Override
    public String toString() {
        String status = this.status ? "X": "";
        return String.format("[%s] %s ", status, this.details);
    }

    public String getDetails() {
        return this.details;
    }

}
