public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        if (isDone) {
            return "X"; 
        } else {
            return " "; 
        }
    }

    public void Done() {
        this.isDone = true;
    }

    public void Undone() {
        this.isDone = false;
    }

    public String toFileString() {
        return (this instanceof Todo ? "T" :
                this instanceof Deadline ? "D" :
                this instanceof Event ? "E" : " ") + " | " +
                (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
