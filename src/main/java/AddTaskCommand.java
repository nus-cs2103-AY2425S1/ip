public class AddTaskCommand extends Command{

    private final Task task;
    public AddTaskCommand(Task task){
        this.task = task;
    }
    @Override
    public void execute(Storage storage) throws InvalidTaskNumberException {
        storage.addTask(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(storage.getTask(storage.getNumOfTasks() - 1));
        System.out.printf("Now you have %d tasks in the list%n", storage.getNumOfTasks());
        System.out.println(Optimus.linebreak);
    }
}
