public class LeaveCommand extends Command{

    public LeaveCommand() {

    }
    @Override
    public void execute(Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Optimus.linebreak);
    }

    @Override
    public boolean shouldContinue() {
        return false;
    }
}
