public class DeadlineTask extends Task {

    private String deadline;

    public DeadlineTask(String input) {
        int byIndex = input.indexOf("/by");
        this.name = input.substring(9, byIndex).trim();
        this.deadline = input.substring(byIndex + 4).trim();
        this.taskTypeSymbol = "D";
    }

    @Override
    public String toString() {
        return String.format("%1$s (by: %2$s)", super.toString(), this.deadline);
    }
}
