public abstract class Command {

    abstract public void execute(Storage storage) throws Exception;
    public boolean shouldContinue() {
       return true;
    };
}
