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


    /**
     *
     * @return a string of exit message
     */
    @Override
    public String execute() {
        return "    Bye. Hope to see ya again soon!";
    }

}
