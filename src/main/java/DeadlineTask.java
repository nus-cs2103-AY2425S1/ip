public class DeadlineTask extends Task{
    String deadline;
    private DeadlineTask(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static DeadlineTask of(String input) {
        return new DeadlineTask(
                input.substring(8, input.indexOf("/by")).strip(),
                input.substring(input.indexOf("/by") + 4).strip()
        );
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[D][X] " + this.description + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + deadline + ")";
        }
    }
}
