public class ToDo extends Task{

    protected String taskType = "T";
    public ToDo (String description) {
        super(description);
    }

    public String toString() {
        return "[T] " + super.toString();
    }

}
