package noosy.task;

public abstract class Task {
    protected boolean status;
    protected String name;

    public Task(String desc) {
        this.name = desc;
        this.status = false; // default: task not completed
    }

    public Task(String desc, boolean status) {
        this.name = desc;
        this.status = status;
    }

    public boolean isDone() {
        return this.status;
    }

    // mark as done
    public void markDone() {
        this.status = true;
    }

    // unmark
    public void unDone() {
        this.status = false;
    }

    // get task name
    public String getDesc() {
        return this.name;
    }

    public String storeInFile() {
        return String.valueOf(this.status ? 1 : 0);
    }

    @Override
    public String toString() {
        String prepend = this.status ? "[X] " : "[ ] ";
        return prepend + this.name;
    }
}
