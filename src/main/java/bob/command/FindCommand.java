package bob.command;

import java.util.ArrayList;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.Task;
import bob.task.TaskList;
import bob.ui.Ui;


/**
 * ListCommand class executes list command.
 */
public class FindCommand extends Command {

    /**
     * Constructor to initalise FindCommand
     *
     * @param input
     */
    public FindCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = this.getInput();
        String[] inputArray = input.split("\s+");
        String toPrint = "Here are the matching tasks in your list:\n";
        try {
            if (inputArray.length > 2) {
                throw new InvalidTaskException("find command can only be used to find 1 keyword.");
            }
            if (inputArray.length <= 1) {
                throw new InvalidTaskException("Please enter the exact keyword you want to find.");
            }
            String target = inputArray[1];
            ArrayList<Task> matchingRecords = new ArrayList<>();
            for (Task x : taskList.getAllRecords()) {
                if (x.isTargetInDescription(target) == true) {
                    matchingRecords.add(x);
                }
            }
            int counter = 1;
            for (Task task: matchingRecords) {
                toPrint += "\t" + counter + "." + task.getTaskListItem() + "\n";
            }
            ui.showFindResults(toPrint);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
        return toPrint;
    }
}
