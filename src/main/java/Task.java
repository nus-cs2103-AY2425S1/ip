public class Task {
    public enum TYPE { TODO, DEADLINE, EVENT };

    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public String getSimpleStatusIcon() {
        return this.isDone ? "1" : "0";
    }

    public String simpleFormat() {
        return this.getSimpleStatusIcon() + " | " + this.description;
    }
}
