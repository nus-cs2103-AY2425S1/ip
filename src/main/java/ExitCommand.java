public class ExitCommand extends Command {
    @Override
    public String execute() {
        return "Bye. Hope to see you again soon!";
    }

    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
