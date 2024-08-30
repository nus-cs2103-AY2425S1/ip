public class ToDoTask extends Task {
    public ToDoTask(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String printTask() {
        String output = "[T]";
        String status = (super.isDone ? "X" : " ");
        return output + "[" + status + "] " + super.description;
    }

    @Override
    public String writeTask() {
        String output = "T | ";
        String status = (super.isDone ? "1" : "0");
        output += status + " | ";
        return output + description + "\n";
    }
}
