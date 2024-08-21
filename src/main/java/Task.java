public class Task {
    boolean done;
    String title;

    public Task(String title) {
        this.done = false;
        this.title = title;
    }

    @Override
    public String toString() {
        return (done ? " [X] " : " [ ] ") + title;
    }
}
