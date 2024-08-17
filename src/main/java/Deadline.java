public class Deadline extends IndividualTask{

    private String returnBy;
    public Deadline(String taskDescription, String returnBy) {
        super(taskDescription);
        this.returnBy = returnBy;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.returnBy + ")";
    }
}
