public class Task {
    boolean isDone;
    String taskDesc;

    public Task(String desc) {
        this.isDone = false;
        this.taskDesc = desc;
    }

    public void markAsComplete() {
        this.isDone = true;
    }

    public void markAsIncomplete() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " : "[ ] ";
    }
}
