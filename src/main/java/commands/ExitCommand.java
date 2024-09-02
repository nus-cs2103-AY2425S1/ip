package commands;

public class ExitCommand implements Command {
    @Override
    public void execute(Context context) {
        throw new ExitException();
    }
}
