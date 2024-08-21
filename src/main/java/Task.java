public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isDeleted;
    protected int num;
    private static int count;

    public Task() {
        this.description = null;
        this.isDone = false;
        count += 1;
        this.num = count;
    }

    public Task(String description) {
        this.description = description.toLowerCase();
        this.isDone = false;
        count += 1;
        this.num = count;
    }

    public void changeStatus() {
        this.isDone = !isDone;

        if (isDone) {
            System.out.println("Yay! You finally did something today");
            System.out.println(this);
        } else {
            System.out.println("Skill issue...");
            System.out.println(this);
        }
    }

    public void delete() {
        this.isDeleted = true;
        System.out.println("Deleting this task: " + this);
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}