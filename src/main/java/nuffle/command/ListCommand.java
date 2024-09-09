package nuffle.command;

import nuffle.storage.Storage;
import nuffle.task.TaskList;
import nuffle.ui.Ui;

public class ListCommand extends Command {
    private final String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        System.out.println("Here are the tasks in your list: ");
        StringBuilder output = new StringBuilder();

        // If there is no user input in the list
        if (tasks.getInputList().isEmpty()) {
            output.append("List is empty. No input added.");
        } else {
            // Iterate over the list to construct the output string with all user inputs
            for (int index = 0; index < tasks.getSize(); index++) {
                output.append(index + 1).append(". ").append(tasks.getTask(index)).append("\n");
            }
        }
        return output.toString();
    }
}
