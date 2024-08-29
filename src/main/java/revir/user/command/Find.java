package revir.user.command;

import revir.tasks.TaskList;
import revir.user.Ui;

public class Find extends Command {
    private String keyword;

    public Find(String keyword) {
        super(false);
        this.keyword = keyword;
    }
    
    @Override
    public void execute(Ui ui, TaskList taskList) {
        String list = taskList.find(this.keyword);
        ui.showResult("Find:\n" + list);
    }
    
}
