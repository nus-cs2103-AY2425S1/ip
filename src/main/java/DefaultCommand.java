public class DefaultCommand extends Command{
    String userInput;

    public DefaultCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * print default command
     *
     */
    @Override
    public void execute() {
       System.out.println(userInput);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
