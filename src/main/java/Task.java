public class Task {
    protected String description;
    protected boolean isDone;

    protected int ranking;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.ranking = 0;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(String mark, int x) {
        if (mark.equals("mark")) {
            if (!this.isDone) {
                this.isDone = true;
                Bobby.list[x - 1] = x + ". [" + this.getStatusIcon() + " ] " + this.description;
            }
        }
    }

}
