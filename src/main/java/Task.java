class Task {
    String taskString;
    boolean done;
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

    public String toString() {
        return this.getDone() + " " + this.taskString;
    }
}
