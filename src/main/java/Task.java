public class Task {
    protected String description;
    protected boolean isDone;


    public Task(String task) {
        description = task;
        isDone = false;

    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        isDone = true;
        System.out.println("____________________________________________________________ \n"
                + "Nice! I've marked this task as done: \n"
                + this + "\n"
                + "____________________________________________________________") ;

    }

    public void unmark() {
        isDone = false;
        System.out.println("____________________________________________________________ \n"
                + "OK, I've marked this task as not done yet: \n"
                + this + "\n"
                + "____________________________________________________________") ;

    }
    public void delete() {
        System.out.println("____________________________________________________________ \n"
                + "Noted. I've removed this task:\n"
                + this + "\n");

    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description ;
    }
}
