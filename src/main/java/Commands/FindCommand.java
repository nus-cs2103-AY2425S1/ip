package Commands;

import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * The FindCommand class extends Command to handle commands related to filtering the tasks for a specific keyword.
 * It parses a command to extract the search keyword, searches for tasks containing
 * that keyword, and then displays the matching tasks in a list format.
 */
public class FindCommand extends Command {

    public FindCommand(String c) {
        super(c);
    }

    /**
     * Executes the command to search for the particular keyword.
     * If keyword is not specified or the tasks do not contain the particular word, an error message is displayed.
     * Otherwise, a list in string format is returned containing the matching tasks.
     * @return String format of the list containing the matching tasks
     */
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
        if(outputList.isEmpty()) {
            return "You have no current tasks containing " + "'" + wordToSearch + "'.\n" +
                    "Please try again with another word.";
        }
        return Ui.diffListDisplay("Here are the matching tasks in your list:", outputList);
    }
}
