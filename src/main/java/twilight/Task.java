package twilight;

public class Task {
    protected String description;
    protected boolean isDone;
//  false status indicates incomplete item

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public String toStorage() {
        if (isDone) {
            return "1," + this.description;
        } else {
            return "0," + this.description;
        }
    }

    public void setDone() {
        this.isDone = true;
    }

    public void SetUndone() {
        this.isDone = false;
    }
}
