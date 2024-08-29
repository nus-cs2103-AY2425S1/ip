public abstract class Command {
    public boolean isExit;
    public abstract void run(Task t);
}
