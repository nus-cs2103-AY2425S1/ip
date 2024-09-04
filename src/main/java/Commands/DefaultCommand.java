package Commands;

public class DefaultCommand extends Command {
    String userInput;

    public DefaultCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * print default command
     *
     * @return a string
     */
    @Override
    public String execute() {

        return userInput;
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
