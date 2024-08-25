public class ToDoTask extends Task {
    public ToDoTask(String description) throws IllegalInputPotongException {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
