public class Deadline extends Task{
    private String deadline;
    public Deadline(String desc, Boolean mark, String deadline) throws MissingDescriptionException{
        super(desc, mark);
        this.deadline = deadline;
    }
    @Override
    public String printTask() {
        return String.format("[D]%s (by: %s)", super.printTask(), deadline);
    }
}
