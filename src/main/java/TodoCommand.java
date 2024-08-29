public class TodoCommand extends AddCommand {
    public TodoCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String description) throws MorganaException {
        if (description.isEmpty()) {
            throw new MorganaException("Please specify a description for your todo.");
        }
        return new Todo(description);
    }
}
