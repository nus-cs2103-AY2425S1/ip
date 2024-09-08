package pacman;

public class Task {
    private final String taskName;
    private boolean markDone = false;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setMarkDone(boolean marked) {
        this.markDone = marked;
    }

    public String toFile() {
        String mark = this.markDone ? "1" : "0";
        return mark + "/" + taskName;
    }

    public boolean isMatched(String matcher) {
        return this.taskName.contains(matcher);
    }

    @Override
    public String toString() {
        String mark = this.markDone ? "X" : " ";
        return "[" + mark + "] " + this.taskName;
    }
}
