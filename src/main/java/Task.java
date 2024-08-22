public class Task {
    private final String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false; // Default is not done
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String showDoneOrNot() {
        return (isDone ? "[X]" : "[ ]"); // Return X or space depending on isDone
    }

    @Override
    public String toString() {
        return showDoneOrNot() + " " + name;
    }
}
