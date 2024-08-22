class Task {
    String taskString;
    boolean done;
    Task(String taskString, boolean done) {
        this.taskString = taskString;
        this.done = done;
    }

    String getString() {
        return this.taskString;
    }

    boolean getStatus() {
        return this.done;
    }
}
