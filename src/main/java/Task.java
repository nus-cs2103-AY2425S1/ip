public class Task {
    private boolean isCompleted = false;

    private String name;

    public Task(String name) {
        this.name = name;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        String completeIcon = isCompleted ? "X" : " ";
        return "[" + completeIcon + "] " + this.name;
    }
}
