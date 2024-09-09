package alice.command;

import java.io.IOException;

import alice.storage.TaskList;
import alice.task.Deadline;
import alice.task.Event;
import alice.task.InvalidTaskException;
import alice.task.Task;
import alice.task.ToDo;

/** Command to add task. */
public class AddTask extends Command {
    public AddTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Adds a task to the taskList.
     * Can be TODO, DEADLINE or EVENT.
     *
     * Input should be: &lt;task type&gt; &lt;description&gt; &lt;flags&gt;
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        assert input.split(" ").length > 0;
        Task.TaskType taskType = Task.TaskType.valueOf(input.split(" ")[0].toUpperCase());
        Task task;
        try {
            switch (taskType) {
            case TODO:
                task = new ToDo(input);
                assert task instanceof ToDo;
                break;
            case DEADLINE:
                task = new Deadline(input);
                assert task instanceof Deadline;
                break;
            case EVENT:
                task = new Event(input);
                assert task instanceof Event;
                break;
            default:
                // not possible
                throw new InvalidTaskException();
            }
            taskList.addTask(task);
            return String.format("Got it. I've added this task:\n\t%s", task);
        } catch (InvalidTaskException exception) {
            throw new InvalidTaskException(String.format("%s Usage: <task type> <description> <flags>", exception));
        } catch (IOException exception) {
            throw new InvalidTaskException("Unable to save task.");
        }
    }
}
