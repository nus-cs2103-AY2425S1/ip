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

    public Task(String description, boolean isDone) throws IllegalInputPotongException {
        if (description.isEmpty()) {
            throw new IllegalInputPotongException();
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
        System.out.println(String.format("Nice! I've marked this task as done:\n %s", this));
    }

    public void unmark() {
        this.isDone = false;
        System.out.println(String.format("OK, I've marked this task as not done yet:\n %s", this));
    }

    public String getType() {
        return "";
    }

    public String getDescription() {
        return this.description;
    }

    public String getTime() {
        return "";
    }

    public String getStatus() {
        if (this.isDone) {
            return "1";
        } else {
            return "0";
        }
    }
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
