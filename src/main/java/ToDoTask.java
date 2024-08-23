public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    @Override
    public String printTask() {
        String output = "[T]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description;
    }
}
