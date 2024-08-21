public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markAs(boolean isDone) {
        this.isDone = isDone;
    }
    public boolean checkIsDone() {
        return isDone;
    }

}
