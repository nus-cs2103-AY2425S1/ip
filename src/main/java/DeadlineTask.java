public class DeadlineTask extends Task {
    private String deadline;
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        int by = deadline.indexOf(" ");
        String date = String.format("by: " + deadline.substring(by + 1));
        return String.format("[D]" + super.toString() + " (" + date + ")");
    }

}
