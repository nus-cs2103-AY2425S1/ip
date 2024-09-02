class Task {
    String task;
    boolean isDone;

    Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    void mark() {
        this.isDone = true;
    }

    void unmark() {
        this.isDone = false;
    }

    public String toString() {
        if (this.isDone) {
            return "[X] " + this.task;
        } else {
            return "[ ] " + this.task;
        }
    }

    String toSaveAsString() {
        if (this.isDone) {
            return "1/" + this.task;
        } else {
            return "0/" + this.task;
        }
    }
}