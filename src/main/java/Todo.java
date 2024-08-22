public class Todo extends Task {
    public Todo(String description) throws DukeException{
        super(description);
        if (description.isEmpty() || description.equals("todo")) {
            throw new DukeException("todo", "OOPS!!! The description of a todo shouldn't be empty!\n");
        }
    }

    public String getString() {
        return "[T]" + super.getString();
    }
}
