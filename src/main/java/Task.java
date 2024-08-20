public class Task {
    protected String description;
    protected boolean isDone;
    protected int num;
    private static int count;

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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}