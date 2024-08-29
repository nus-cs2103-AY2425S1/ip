package actions;

import tasks.TaskList;

public class Bye extends Action {
    public Bye(TaskList tasklist) {
        super(tasklist);
    }

    @Override
    //Do nothing
    public void execute() {
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
