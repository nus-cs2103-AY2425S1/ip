public class Task {
    boolean mark;
    String task;

    public Task(boolean mark, String task) {
        this.mark = mark;
        this.task = task;
    }

    public void mark_task() {
        this.mark = true;
    }

    public void unmark_task() {
        this.mark = false;
    }

    @Override
    public String toString() {
        String s;
        if (this.mark == true) {
            s = String.format("[X] %s", this.task);
        } else {
            s = String.format("[ ] %s", this.task);
        }
        return s;

    }
}
