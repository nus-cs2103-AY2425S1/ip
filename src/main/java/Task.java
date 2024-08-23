package src.main.java;

public class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(String.format("%s has been successfully marked as done!", description));
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println(String.format("%s has been successfully marked as not done!", description));
    }

    @Override
    public String toString() {
        return isDone ? String.format("✅: %s", this.description)
                : String.format("❌: %s", this.description);
    }
}
