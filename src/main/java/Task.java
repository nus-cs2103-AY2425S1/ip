public class Task {
    protected String description;
    protected boolean status;
//  false status indicates incomplete item

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public String toString() {
        if (this.status) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }

    public void done() {
        this.status = true;
    }

    public void unDone() {
        this.status = false;
    }
}
