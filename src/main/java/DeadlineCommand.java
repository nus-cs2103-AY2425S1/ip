public class DeadlineCommand extends AddCommand {
    public DeadlineCommand(String args) {
        super(args);
    }

    @Override
    Task createTask(String args) throws MorganaException {
        String[] parts = args.split(" /by ");
        if (parts.length != 2) {
            throw new MorganaException("""
                    Invalid deadline format.
                    Usage: deadline <description> /by <yyyy-MM-dd HHmm>
                    Example: deadline return book /by 2019-10-15 1800""");
        }
        return new Deadline(parts[0], Parser.parseDateTime(parts[1]));
    }
}
