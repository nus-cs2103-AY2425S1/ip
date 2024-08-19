public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void mark() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void unmark(){
        this.isDone = false;
        System.out.println("Ok, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (isDone ? "X" : " "), this.description);
    }

}
