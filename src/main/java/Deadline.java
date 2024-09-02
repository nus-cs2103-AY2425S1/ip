class Deadline extends Task {
    String deadline;

    Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    Deadline(int status, String task, String deadline) {
        super(status, task);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.deadline + ")";
    }

    String toSaveAsString() {
        return "D/" + super.toString() + "/" + this.deadline;
    }
}