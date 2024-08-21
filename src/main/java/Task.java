public abstract class Task {
    protected static int COUNT = 0;
    protected boolean done = false;
    protected String task;

    public Task(String task) {
        this.task = task;
        COUNT++;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getStatusIcon() {
        return (done ? "X" : " "); // mark done task with X
    }

    public static String getCOUNT() {
        return "Now you have " + COUNT + " tasks in the list.";
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.task;
    }

}