public class AddTodoCommand extends AddCommand {

    private final String TASK_TYPE = "todo";

    public AddTodoCommand(String userInput, TaskManager taskManager, FileManager fileManager) {
        super(userInput, taskManager, fileManager);
    }

    @Override
    public void runCommand() throws TaskManagerException {
        String taskInfo;
        try {
            taskInfo = userInput.split("\\s+", 3)[2];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException("\uD83D\uDE15 Hmm, something went wrong. Did you add task correctly? " +
                    "(\uD83D\uDCA1 Tip: Use \"add {Specify Task Type e.g. todo, deadline, or event} " +
                    "/ {Input task description here}\" to add a task)",
                    TaskManagerException.ErrorType.INVALID_ADD_TASK_NUMBER);
        }
        // Check if the task already exists
        if (this.taskManager.checkDuplicateTask(taskInfo)) {
            throw new TaskManagerException("This task is already in your list! " +
                    "Maybe you can try renaming it and input again?",
                    TaskManagerException.ErrorType.DUPLICATE_TASK);
        }
        Task t = new Todo(taskInfo, this.TASK_TYPE);
        this.tasks.add(t);
        this.fileManager.writeTasksToFile(this.tasks);
        System.out.println("\uD83C\uDF89 Got it! I've added: \"" + taskInfo + "\" to your list!");
        System.out.println("\uD83C\uDFAF You now have " + this.tasks.size() + " tasks in the list. Keep going!");
    }
}
