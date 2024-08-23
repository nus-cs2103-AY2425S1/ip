public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String markDone() {
        this.isDone = true;
        return "     Nice! I've marked this task as done: \n" +
                "       " + this.printTask();
    }

    public String markUndone() {
        this.isDone = false;
        return "     OK, I've marked this task as not done yet: \n" +
                "       " + this.printTask();
    }

    public String printTask() {
        String output = "";
        String status = (this.isDone ? "X" : " ");
        return "[" + status + "] " + this.description;
    }
}
