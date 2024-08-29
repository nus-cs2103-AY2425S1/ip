package bobby;

public abstract class Task {
    private boolean completed;
    private String description;

    public Task(String description) {
        this.completed = false;
        this.description = description;
    }

    @Override
    public String toString() {
        if (this.completed) {
            return String.format("[X] %s", this.description);
        } else {
            return String.format("[ ] %s", this.description);
        }
    }

    public String getDescription() {
        return this.description;
    }

    // Indicate task as completed
    public void indComplete() {
        this.completed = true;
    }

    // Indicate task as incomplete
    public void indIncomplete() {
        this.completed = false;
    }


    public String getStatusIcon() {
        if(this.completed) {
            return "X";
        }
        return "";
    }

    public abstract String getTaskType();

    public abstract String taskInFile();
}
