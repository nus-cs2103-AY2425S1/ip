abstract class Task {
    protected String taskString;
    protected boolean done;

    Task(String taskString) {
        this.taskString = taskString;
        this.done = false;
    }

    String getDone() {
        return (this.done ? "[X]" : "[ ]");
    }

    void markAsDone() {
        this.done = true;
    }

    void markAsNotDone() {
        this.done = false;
    }

    abstract String toFileString();

    public String toString() {
        return this.getDone() + " " + this.taskString;
    }
}
