public class Todo extends Task {

    public Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[T] " + task;
    }
    @Override
    public String toFileString() {
        return "D | " + this.getFileStatus() + " | " + this.getTask() + "\n";
    }
}
