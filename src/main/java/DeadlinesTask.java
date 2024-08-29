public class DeadlinesTask extends Task{
    protected String deadline;
    public DeadlinesTask(String description, String time) {
        super(description);
        this.deadline = time;
    }

    @Override
    public String printTask() {
        return "[D]" + super.printTask() + " (by: " + this.deadline + ")";
    } // this is for human readable string

    @Override
    public String printFileFormat() {
        return "D | " + (isDone ? 1 : 0) + " | " + this.description + " | " + this.deadline;
    }

}



