public class Task {
    private boolean status;
    private String desc;

    public Task(String desc) {
        this.desc = desc;
        this.status = false; // default not completed
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
        return this.desc;
    }

    @Override
    public String toString() {
        String prepend = this.status ? "[X] " : "[] ";
        return prepend + this.desc;
    }
}
