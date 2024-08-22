public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public Task createTask(String input) throws InputException{
        if (input.equalsIgnoreCase("deadline")) {
            throw new InputException("To add a Deadline task, use the following format: deadline <task description> /by <date/time>");
        }
        String[] details = input.substring(9).split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            String by = details[1].trim();
            if (description.isEmpty()) {
                throw new InputException("You need to describe your Deadline!");
            }
            return new Deadline(description, by);
        } else {
            throw new InputException("Invalid format. Use: deadline <description> /by <date>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
