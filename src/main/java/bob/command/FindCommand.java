package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindCommand extends Command {
    public static final String COMMAND = "find";

    public FindCommand(Map<String, String> arguments) {
        super(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Check if keyword is provided
        String keyword = arguments.get("");
        if (keyword == null || keyword.isBlank()) {
            throw new MissingArgumentException("the keyword to search by");
        }

        List<Task> taskList = tasks.getTasks(); // Create a new List<Integer>
        List<Integer> foundIndices = getIndices(taskList, keyword); // Get the list of indices of matching tasks

        String listString = foundIndices.stream()
                .map(index -> index.toString() + "."
                        + taskList.get(index).toString())
                .reduce("", (a, b) -> a + "\n" + b);

        ui.printWithFormat("Here are the matching tasks in your list:" + listString);
    }

    private static List<Integer> getIndices(List<Task> taskList, String keyword) {
        List<Integer> indices = new ArrayList<>();

        // Iterate through the tasks
        for (int i = 0; i < taskList.size(); i++) {
            // If the current task contains the keyword, add its index to foundTaskIndices
            boolean containsKeyword = taskList.get(i)
                    .getDescription()
                    .toLowerCase()
                    .contains(keyword.toLowerCase());
            if (containsKeyword) {
                indices.add(i);
            }
        }

        return indices;
    }
}
