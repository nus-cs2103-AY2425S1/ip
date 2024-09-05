package alice.command;

import java.util.List;
import java.util.stream.IntStream;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to list tasks. */
public class ListTask extends Command {
    public ListTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Lists current tasks.
     *
     * Input should be: list
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        List<Task> tasks = taskList.getTasks();
        if (tasks.isEmpty()) {
            return "You have no tasks.";
        } else {
            List<String> taskLines = IntStream.range(0, tasks.size())
                    .mapToObj((index) -> String.format("\t%d. %s", index + 1, tasks.get(index)))
                    .toList();
            return String.format("These are your tasks:\n%s", String.join("\n", taskLines));
        }
    }
}
