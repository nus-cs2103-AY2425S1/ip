public class Task {

    private final String name;

    private boolean isDone;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }

}
