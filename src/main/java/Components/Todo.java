package Components;

public class Todo extends Task {

    public Todo(String textString) {
        super(textString);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
