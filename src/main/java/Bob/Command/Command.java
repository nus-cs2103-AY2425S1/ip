package Bob.Command;

import Bob.Tasks.TaskList;

public abstract class Command {
    private final boolean isRunning;

    public Command(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public abstract void execute(TaskList taskList);
}