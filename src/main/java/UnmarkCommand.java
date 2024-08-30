public class UnmarkCommand extends Command {
    private String userInput;
    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 6) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(6).trim());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                ui.showUnmarkMessage();
                tasks.getTask(taskNumber - 1).markAsUndone();
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
