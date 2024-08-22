public class Task {
    private boolean isComplete;
    private String description;

    public Task(String description) {
        this.isComplete = false;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
    public void check() {
        this.isComplete = true;
    }

    public void uncheck() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        String str = "";
        str = String.format((isComplete ? "[X]" : "[ ]") + " " + this.description);
        return str;
    }
}
