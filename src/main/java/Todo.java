public class Todo extends Task {

    public Todo(String description) throws SpongebobException {
        super(description, TaskType.TODO);
        if (description.equals(" ")) {
            throw new SpongebobException("Barnacles! You can't enter an empty todo!");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return super.save();
    }
}
