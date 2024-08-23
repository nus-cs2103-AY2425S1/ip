public class DeleteTaskCommand extends Command{
    private String[] inputWords;
    public DeleteTaskCommand(String[] inputWords) {
        this.inputWords = inputWords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputWords.length != 2) {
            throw new DukeException("Please enter the task number of the task you want to delete.");
        }

        int taskNum = 0;
        try {
            taskNum = Integer.parseInt(inputWords[1]) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter the task number of the task you want to delete.");
        }

        if (taskNum < 0 || taskNum >= tasks.getSize()) {
            throw new DukeException("This task does not exist!");
        }

        System.out.println("Alright, I have removed this task for you:");
        Task taskToRemove = tasks.getTask(taskNum);
        tasks.deleteTask(taskNum);
        tasks.printTask(taskToRemove);
        storage.saveTasks(tasks.getTasks());
    }
}
