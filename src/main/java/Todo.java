public class Todo extends Task {

    public String statement;
    public Todo(String description) {
        super(description);
    }

    public String write()
    {
        statement = String.format("D | %d | %s\n", this.isDone ? 1 : 0, this.description);
        return statement;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
