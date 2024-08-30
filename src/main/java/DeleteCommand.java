public class DeleteCommand extends Command {
    private String userInput;
    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 6) {
            throw new EmptyTaskDescriptionException();
        } else {
            int taskNumber = Integer.parseInt(userInput.substring(6).trim());
            int totalNumberOfTasks = tasks.getSize();
            System.out.println("taskNumber: " + taskNumber);
            System.out.println("total number of tasks: " + tasks.getSize());
            if (taskNumber >= 1 && taskNumber <= tasks.getSize()) {
                ui.showDeleteMessage();
                System.out.println(tasks.getTask(taskNumber - 1).toString());
                tasks.deleteTask(taskNumber - 1);
                totalNumberOfTasks--;
                System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
                storage.saveTasks(tasks);
            }
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
