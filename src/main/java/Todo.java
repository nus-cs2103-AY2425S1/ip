public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    Todo(boolean isDone, String name) {
        super(isDone, name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String convertToTxt() {
        return String.format("%s,%s","T", super.convertToTxt());
    }
}
