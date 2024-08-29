class Deadline extends Task {
    String deadline;
    Deadline(String taskString, String deadline) {
        super(taskString);
        this.deadline = deadline;
    }

    @Override
    public String toFileString() {
        return "D | " + (done ? 1 : 0) +" | " + taskString + " | " + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}