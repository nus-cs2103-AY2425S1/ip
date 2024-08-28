public class InvalidCommand extends Command {
    private String error;
    private String feedback;

    public InvalidCommand(String error, String feedback) {
        this.error = error;
        this.feedback = feedback;
    }
    @Override
    public CommandResult execute(TaskList taskList) {
        String returnMessage = String.format("error\nfeedback");
        return new CommandResult(returnMessage);
    }
}
