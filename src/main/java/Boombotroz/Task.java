package Boombotroz;

public abstract class Task {
    boolean mark;
    String task;

    public Task(boolean mark, String task) {
        this.mark = mark;
        this.task = task;

    }

    public abstract void hasDate(Ui ui) throws BoomException;

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
