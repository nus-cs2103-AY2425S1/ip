public class Task {
    private boolean isComplete = false;
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public void complete() {
        isComplete = true;
    }

    public String toString() {
        return title;
    }
}
