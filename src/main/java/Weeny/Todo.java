package weeny;
public class Todo extends Task{
    public Todo(String description) {
        super(description, TaskType.TODO);
    }

    public String toOutput() {
        int checkMark = this.isDone ? 1 : 0;
        return "T | " + checkMark + " | " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
