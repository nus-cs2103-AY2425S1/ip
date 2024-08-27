public class MarkCommand extends Command{
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage) throws InvalidTaskNumberException {
        Task task = storage.getTask(taskNum);

        task.markAsComplete();
        storage.rewriteEntireFile();
        System.out.println("Nice! I've marked this task as complete:");
        System.out.println(task);
        System.out.println(Optimus.linebreak);
    }
}
