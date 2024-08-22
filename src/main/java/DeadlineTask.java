public class DeadlineTask extends Task{
    private final String deadline;

    public DeadlineTask(String content, String deadline) {
            super(content);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + this.deadline + ")";
        }
}
