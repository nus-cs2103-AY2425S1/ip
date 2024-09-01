public abstract class Task {

    private boolean status;
    private String description;

    public Task(boolean status, String description) {
        this.description = description;
        this.status = status;
    }

    public Task() {}

    public boolean getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract String toFileString();
    public abstract String toUIString();

}
