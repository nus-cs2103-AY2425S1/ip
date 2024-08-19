public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        char status = this.isDone ? 'X' : ' ';
        String str = String.format("[%c] %s", status, this.name);
        return str;
    }
}
