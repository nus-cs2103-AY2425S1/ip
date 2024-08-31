public class exitCommand implements Command {

    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        System.out.println("Friday > Type \"bye\" or \"Bye\" to exit");
        UI.printLine();
        return false;
    }
}
