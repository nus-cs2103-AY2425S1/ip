package FRIDAY;

public class Task {
    private boolean isComplete;
    private String description;

    public Task(String description, int type) {
        this.isComplete = type > 0;
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

    public String storageDisplay() {
        String type = isComplete ? "1" : "0";
        return " | " + type + " | " + this.description;
    }

    public boolean getType() {
        return this.isComplete;
    }
}
