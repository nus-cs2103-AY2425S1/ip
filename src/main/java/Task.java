public class Task {
    public Task(String content, String isDone) {
        this.content = content;
        this.isDone = isDone;
    }
    private final String content;
    private String isDone;

    public void markDone() {
        this.isDone = "[X]";
    }

    public void markUndone() {
        this.isDone = "[ ]";
    }

    public String getContent() {
        return content;
    }
    public String getIsDone() {
        return isDone;
    }
}
