public class TodoCommand extends Command {
    private String userInput;
    public TodoCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EmptyTaskDescriptionException {
        if (userInput.trim().length() == 4) {
            throw new EmptyTaskDescriptionException();
        }

        String task = userInput.substring(5);
        tasks.addTask(new Todo(task, false));
        int totalNumberOfTasks = tasks.getSize();
        ui.showAddTaskMessage();
        System.out.println(tasks.getTask(totalNumberOfTasks - 1).toString());
        System.out.println("Now you have " + totalNumberOfTasks + " tasks in the list.");
        storage.saveTasks(tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
