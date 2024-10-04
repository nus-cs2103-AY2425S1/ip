package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.MissingArgumentException;
import bob.task.Task;
import bob.util.FormattedString;

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
        assert taskList != null : "TaskList.getTasks() should not return null";
        List<Integer> foundIndices = getIndices(taskList, keyword); // Get the list of indices of matching tasks
        String listString = getTasksAtIndices(taskList, foundIndices);
        FormattedString formattedList = new FormattedString(listString)
                .color(FormattedString.COLOR.BG_YELLOW, keyword, true);


        ui.printWithFormat(new FormattedString("Here are the matching tasks in your list:").append(formattedList));
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

    private static String getTasksAtIndices(List<Task> tasks, List<Integer> indices) {
        return indices.stream()
                .map(index -> (index + 1) + "."
                        + tasks.get(index).toString())
                .reduce("", (a, b) -> a + "\n" + b);
    }
}
