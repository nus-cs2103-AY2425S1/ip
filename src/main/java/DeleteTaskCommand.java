public class DeleteTaskCommand extends Command{
    private final int taskNum;

    public DeleteTaskCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    @Override
    public void execute(Storage storage) throws InvalidTaskNumberException {
        Task removed = storage.removeTask(taskNum);

        System.out.println("Noted. I've removed this task:");
        System.out.println(removed);
        System.out.printf("Now you have %d tasks in the list%n", storage.getNumOfTasks());
        System.out.println(Optimus.linebreak);
    }
}
