import java.util.Objects;

public abstract class Task {
    protected static int COUNT = 0;
    protected boolean done = false;
    protected String task;

    public Task(String task) throws TheBotFatherException{
        if (Objects.equals(task, "")) throw new TheBotFatherException("No description");
        this.task = task.substring(0, task.length() - 1);
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