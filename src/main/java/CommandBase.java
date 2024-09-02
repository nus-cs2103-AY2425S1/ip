public abstract class CommandBase implements Command {
    protected boolean isExit = false;

    @Override
    public boolean isExit() {
        return isExit;
    }
}