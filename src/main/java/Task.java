public class Task {
    protected String desc;
    protected boolean isDone;

    public Task() {
        this.desc = "";
        this.isDone = false;
    }
    public Task(String description) {
        this.desc = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getDesc() {
        return this.desc;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("  i've marked this task as done!: ");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("  ok, I've unmarked this task :( ");
    }
}
