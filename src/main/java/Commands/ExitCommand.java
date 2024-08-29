package Commands;

public class ExitCommand extends Command {
    String userInput;

    public ExitCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
    }

}
