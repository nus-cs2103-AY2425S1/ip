package Commands;

import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        String[] searchEngine = this.cmd.split(" ", 2);
        assert searchEngine.length == 2 : "Object to search must be specified after the 'find' command";

        if (searchEngine.length < 2) {
            return "Please specify a keyword to search after 'find'.";
        }

        String wordToSearch = searchEngine[1];

        ArrayList<Task> taskList = TaskList.getList();
        ArrayList<Task> outputList = new ArrayList<Task>();
        int length = taskList.size();
        for (int i = 0; i < length; i++) {
            Task task = taskList.get(i);
            String taskDesc = task.getDescription();
            if (taskDesc.contains(wordToSearch)) {
                outputList.add(task);
            }
        }
        return Ui.diffListDisplay("Here are the matching tasks in your list:", outputList);
    }
}
