public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public Task createTask(String input) {
        String description = input.substring(5).trim();
        return new ToDo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
