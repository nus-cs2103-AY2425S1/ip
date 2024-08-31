public class listCommand implements Command {

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        if (master.getSize() <= 0) {
            System.out.println("Friday > No tasks in here! Try adding something!");;
        } else {
            System.out.println("Friday > Here's everything!\n" + master);
        }
        UI.printLine();
        return false;
    }
}
