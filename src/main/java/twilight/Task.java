package twilight;

public class Task {
    protected String description;
    protected boolean status;
//  false status indicates incomplete item

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public Task(String description, boolean status) {
        this.description = description;
        this.status = status;
    }

    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public String toStorage() {
        if (status) {
            return "1," + this.description;
        } else {
            return "0," + this.description;
        }
    }

    public void done() {
        this.status = true;
    }

    public void unDone() {
        this.status = false;
    }
}
