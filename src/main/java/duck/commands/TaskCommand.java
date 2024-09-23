package duck.commands;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import duck.Parser;
import duck.TaskList;
import duck.exceptions.MissingArgsException;
import duck.exceptions.UsageException;
import duck.tasks.Task;
import duck.utils.Formatter;

/**
 * Class representing commands to create tasks.
 */
public abstract class TaskCommand extends Command {
    protected Map<String, String> parts;
    private TaskList taskList;
    private Map<String, String> args;

    /**
     * Constructor for TaskCommand.
     *
     * @param taskList   List of tasks.
     * @param lineBuffer Buffer containing remaining command.
     * @param args       A map from patterns (e.g. "/by", "/after") to argument
     *                   names (e.g. "due_date", "earliest_date").
     */
    public TaskCommand(TaskList taskList, Parser lineBuffer, Map<String, String> args) {
        this.taskList = taskList;
        this.parts = lineBuffer.parseArgs();
        this.args = args;
    }

    protected void verifyArgsArePresent(UsageException usageException) throws MissingArgsException {
        List<String> missingArgs = new ArrayList<>();

        for (String pattern : args.keySet()) {
            if (!parts.containsKey(pattern)) {
                missingArgs.add(args.get(pattern));
            }
        }

        if (!parts.containsKey("")) {
            missingArgs.add("description");
        }

        if (missingArgs.size() > 0) {
            throw new MissingArgsException(missingArgs, usageException);
        }
    }

    protected String handleNewTask(Task task) {
        taskList.addTask(task);
        String response = "Got it. I've added this task:\n"
                + Formatter.indentText(task.toString(), 2) + "\n"
                + "Now you have " + taskList.getTaskCount() + " tasks in the list.";
        return response;
    }

    public abstract String createNewTask(TaskList taskList) throws MissingArgsException, DateTimeParseException;

    @Override
    public String executeCommand() {
        try {
            return createNewTask(taskList);
        } catch (MissingArgsException e) {
            return e.toString();
        } catch (DateTimeParseException e) {
            return e.toString();
        }
    }
}
