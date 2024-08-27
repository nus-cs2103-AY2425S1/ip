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

    @Override
    public String getStorageFormat() {
        String output = this.isDone ? "1" : "0";
        output += " todo " + description + "\n";
        return output;
    }
}
