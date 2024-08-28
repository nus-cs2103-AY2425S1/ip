public abstract class Command {

    public abstract void execute() throws RasputinException;
    public abstract boolean isTerminated();
}
