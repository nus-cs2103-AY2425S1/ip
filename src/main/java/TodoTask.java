public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    public TodoTask fromInput(String name) {
        name = name.trim();

        if (name == "") {
            throw new TaskFieldException("Description");
        }

        return new TodoTask(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
