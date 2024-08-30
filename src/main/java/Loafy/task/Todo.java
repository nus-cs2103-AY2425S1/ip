package loafy.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public Todo(boolean isDone, String name) {
        super(isDone, name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String convertToTxt() {
        return String.format("%s,%s","T", super.convertToTxt());
    }
}
