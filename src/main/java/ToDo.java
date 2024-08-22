public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("todo")) {
            throw new InputException("To add a ToDo task, use the following format: todo <task description>");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new InputException("You need to describe your Todo!");
        }
        return new ToDo(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
