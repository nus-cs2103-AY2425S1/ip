public class Task {
    private String taskContent;
    private boolean isDone;

    public Task(String taskContent) {
        this.taskContent = taskContent;
    }

    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        String header = "";
        if (isDone) {
            header = "[X] ";
        } else {
            header = "[ ] ";
        }
        return header + this.taskContent;
    }

    public String toFileFormat() {
        return (isDone ? "1" : "0") + " | " + taskContent;
    }
}
