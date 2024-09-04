package choaticbot.actions;

import choaticbot.tasks.TaskList;

public class Find extends Action {
    public String word;

    public Find(TaskList taskList, String word) {
        super(taskList);
        this.word = word;
    }

    @Override
    public void execute() {
        this.taskList.filterByWord(word);
    }
}
