public class ListCommand  extends Command{

    public ListCommand() {

    }
    @Override
    public void execute(Storage storage) throws InvalidTaskNumberException {
        for (int i = 0; i < storage.getNumOfTasks(); i++) {
            System.out.printf("%d. %s%n", i + 1, storage.getTask(i));
        }
        System.out.println(Optimus.linebreak);
    }
}
