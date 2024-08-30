public class MarkCommand extends Command {
    private String userInput;
    public MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 4) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(4).trim());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                tasks.getTask(taskNumber - 1).markAsDone();
                ui.showMarkMessage();
                System.out.println(tasks.getTask(taskNumber - 1).toString());
                storage.saveTasks(tasks);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
