package chatbot.task;

public abstract class Task {
    private String name;
    private boolean isDone;

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
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

    public String encode() {
        return (this.isDone ? "1" : "0") + "|" + this.name;
    }
}
