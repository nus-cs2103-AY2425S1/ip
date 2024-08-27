public class UnmarkCommand extends Command{
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage) throws InvalidTaskNumberException {
        Task task = storage.getTask(taskNum);

        task.markAsIncomplete();
        storage.rewriteEntireFile();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println(Optimus.linebreak);
    }
}
