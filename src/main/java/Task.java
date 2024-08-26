public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /* getStatusIcon returns status of the Task, checking
    if its marked or unmarked
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /* markAsDone function marks the Task status from
    undone to done
     */
    public void markAsDone() {
        System.out.println("Nice! I've marked this task as done: \n" );
        if (!this.isDone) {
            this.isDone = true;
            System.out.println("[" + this.getStatusIcon() + "] " + this.description + "\n");
        }

    }

    /* markAsDone function marks the Task status from
    done to undone
     */
    public void unMark() {
        System.out.println("OK, I've marked this task as not done yet:");
        if (this.isDone) {
            this.isDone = false;
            System.out.println("[" + this.getStatusIcon() + "] " + this.description + "\n");
        } else {
            System.out.println("this tasked has not been marked \n");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" +  this.description;
    }

}
