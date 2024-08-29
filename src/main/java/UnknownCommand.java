public class UnknownCommand extends Command{

    private final String commandMessage;

    public UnknownCommand(String commandMessage) {
        this.commandMessage = commandMessage;
    }
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Sorry to say that I don't know what does \"" + commandMessage + "\" means. " +
                "Anyway, have a good day :)");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
