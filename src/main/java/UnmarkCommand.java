public class UnmarkCommand extends Command{
    public UnmarkCommand(String commandBody) {
        super(commandBody);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBabyException {
        try {
            int index = Integer.parseInt(commandBody.substring(7).trim()) - 1;
            if (index >= 0 && index < tasks.size()) {
                tasks.taskAt(index).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n"
                        + tasks.taskAt(index).toString());
            } else {
                throw new ChatBabyException("Oh no!!! The task index is invalid.");
            }
        } catch (NumberFormatException e) {
            throw new ChatBabyException("Oh no!!! The task index is invalid.");
        }
    }
}
