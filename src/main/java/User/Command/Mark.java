package User.Command;

import java.io.IOException;

import Tasks.TaskList;
import User.Ui;

public class Mark extends Command {
    private int taskIndex;
    private boolean state; 

    public Mark(int taskIndex, boolean state) {
        super(false);
        this.state = state;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) throws IOException {
        ui.showResult(taskList.setCompleted(taskIndex, state));
    }
    
}
