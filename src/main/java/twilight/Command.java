package twilight;

abstract class Command {
    public void execute(TaskList tasks, Storage storage) throws InvalidInputException {}
    public boolean isExit() { return false; }
}
