public abstract class Command {

    abstract public void execute(Storage storage, TaskList tasks, Ui ui) throws OptimusExceptions;
    public boolean shouldContinue() {
       return true;
    }
}
