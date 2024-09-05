package alice.command;

import java.util.List;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to find task. */
public class FindTask extends Command {
    public FindTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Finds tasks which contain a given keyword.
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        String[] tokens = input.split(" ", 2);
        if (tokens.length != 2) {
            throw new InvalidTaskException("Missing keyword. Usage: find <keyword>");
        }

        String keyword = tokens[1];
        List<Task> matches = taskList.findTasks(keyword);
        if (matches.isEmpty()) {
            return String.format("No tasks contains the keyword \"%s\".", keyword);
        } else {
            List<String> taskLines = matches.stream().map((task) -> String.format("\t%s", task)).toList();
            return String.format("Here are the matching tasks in your list:\n%s", String.join("\n", taskLines));
        }
    }
}
