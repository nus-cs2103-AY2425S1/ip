package Tasks;
public class Task {

    private String description;
    private boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    private String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    private String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("    Nice! I've marked this task as done:");
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("    OK, I've marked this task as not done yet:");
    }

    public void delete() {
        System.out.println("    Noted. I've removed this task:");
    }

    public String toFileFormat() {
        return this.getStatusNum() + " .. " + this.getDescription();
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
