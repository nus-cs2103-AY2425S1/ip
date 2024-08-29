abstract class Task {
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

    abstract Task markAsDone();

    abstract Task markAsUndone();

    abstract String getType();

    abstract String getTime();

    public String getStatusIcon() {
        return (status ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", this.getType(), this.getStatusIcon(), this.description);
    }

}
