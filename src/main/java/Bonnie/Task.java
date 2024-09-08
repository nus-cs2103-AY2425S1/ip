package Bonnie;

public abstract class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println(String.format("%s has been successfully marked as done!\n", description));
    }

    public void unmarkAsDone() {
        this.isDone = false;
        System.out.println(String.format("%s has been successfully marked as not done!\n", description));
    }

    @Override
    public String toString() {
        return isDone ? String.format("[X]: [%s]", this.description)
                : String.format("[ ]: [%s]", this.description);
    }
}
