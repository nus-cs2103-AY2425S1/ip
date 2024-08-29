public class DeadlineTask extends Task {
    private String date;
    public DeadlineTask(String description, boolean isDone, String date) {
        super(description, isDone);
        this.date = date;
    }

    @Override
    public String printTask() {
        String output = "[D]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description + " (by: " + this.date + ")";
    }
}
