package Stobberi.Command;

public class ExitCommand extends Command {
    public ExitCommand() {
        super();
    }

    @Override
    public void execute() {
        setExitTrue();
    }
}