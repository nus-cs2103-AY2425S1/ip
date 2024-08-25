public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws IllegalInputPotongException {
        if (description.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String mark() {
        this.isDone = true;
        return String.format("Nice! I've marked this task as done:\n %s", this.toString());
    }

    public String unmark() {
        this.isDone = false;
        return String.format("OK, I've marked this task as not done yet:\n %s", this.toString());
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
