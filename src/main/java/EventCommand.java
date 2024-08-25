public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String fullCommand) throws PikappiException {
        try {
            String[] splitCommand = fullCommand.split(" /from ");
            this.description = splitCommand[0];
            String[] splitFrom = splitCommand[1].split(" /to ");
            this.from = splitFrom[0];
            this.to = splitFrom[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pika..? What is the event timings?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        tasks.addTask(new EventTask(description, from, to));
        storage.save(tasks);
    }
}
