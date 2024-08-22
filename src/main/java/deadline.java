public class deadline extends Task{
    protected String dueDate;
    public deadline(String description, String dueDate){
        super("D", description);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        String message = "[" + this.type + "]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + dueDate + ")";
        return message;
    }
}
