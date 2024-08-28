import java.util.ArrayList;

abstract class Command {
    private boolean isExit;
    private TaskList tasks;

    public Command(TaskList tasks) {
        this.tasks = tasks;
        this.isExit = false;
    }

    public TaskList getTasks() {
        return this.tasks;
    }
    public void exit() {
        this.isExit = true;
    }
    public boolean isExit() {
        return this.isExit;
    }
    abstract void execute();
}
