public class Todo extends Task {
    Todo(String name) {
        super(name);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public String convertToTxt() {
        return String.format("%s,%s","T", super.convertToTxt());
    }
}
