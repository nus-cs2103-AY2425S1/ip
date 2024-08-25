public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String fullCommand) throws PikappiException {
        try {
            String[] splitCommand = fullCommand.split(" /by ");
            this.description = splitCommand[0];
            this.by = splitCommand[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PikappiException("Pika..? When is the deadline?");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PikappiException {
        tasks.addTask(new DeadlineTask(description, by));
        storage.save(tasks);
    }
}
