public class Task {
    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public String getStatusIcon() {
        return (status ? "X" : " ");
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
