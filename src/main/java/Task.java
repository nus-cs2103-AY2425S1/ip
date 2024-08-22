public class Task {

    protected String typeIcon;
    protected String description;
    protected boolean isDone;

    public Task(String typeIcon, String description) {
        this.typeIcon = typeIcon;
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String stringUI() {
        return "[" + this.typeIcon + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public String stringStorage(){
        return this.typeIcon + " | " + this.getStatusIcon() + " | " + this.description;
    }

}
