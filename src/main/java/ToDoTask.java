public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask fromInput(String name) {
        name = name.trim();

        if (name == "") {
            throw new TaskFieldException("Description");
        }

        return new ToDoTask(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
