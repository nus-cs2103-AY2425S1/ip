public class Task {
    protected boolean status;
    protected String name;

    public Task(String desc) {
        this.name = desc;
        this.status = false; // default: task not completed
    }

    // mark as donw
    public void isDone() {
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

    @Override
    public String toString() {
        String prepend = this.status ? "[X] " : "[ ] ";
        return prepend + this.name;
    }
}
